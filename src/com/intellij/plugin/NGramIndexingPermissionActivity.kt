/*
 * Copyright 2000-2018 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.intellij.plugin

import com.intellij.ide.util.PropertiesComponent
import com.intellij.notification.Notification
import com.intellij.notification.NotificationAction
import com.intellij.notification.NotificationType
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity
import com.intellij.openapi.ui.MessageDialogBuilder
import com.intellij.openapi.ui.Messages
import com.intellij.stats.ngram.NGramFileBasedIndex

/**
 * @author Vitaliy.Bibaev
 */
class NGramIndexingPermissionActivity : StartupActivity {

  private companion object {
    const val DO_NOT_ASK_AGAIN_KEY = "com.intellij.plugin.completion.ngram.indexing.not.ask"
    const val ALLOW_FOR_NEW_PROJECTS = "com.intellij.plugin.completion.ngram.indexing.allow.for.new.projects"
    const val ANSWERED = "com.intellij.plugin.completion.ngram.indexing.answered"
    const val PLUGIN_NAME = "Completion Stats Collector"
    private const val MESSAGE_TEXT = """We’re going to use ngram frequencies to rank completion items better. This
        can increase the indexing time by ~20-50% on large projects (especially on MS Windows). Will you allow us
        to perform this experiment on your project?
    """
  }

  override fun runActivity(project: Project) {
    if (alreadyAnswered(project)) return
    if (!askForNewProject()) {
      if (allowedByDefault()) {
        allowIndexing(project)
      } else {
        denyIndexing(project)
      }
    } else {
      notify(project)
    }
  }

  private fun notify(project: Project) {
    Notification(PLUGIN_NAME, PLUGIN_NAME, MESSAGE_TEXT, NotificationType.INFORMATION)
        .addAction(object : NotificationAction("Allow") {
          override fun actionPerformed(event: AnActionEvent, notification: Notification) {
            allowIndexing(project, notification)
          }
        })
        .addAction(object : NotificationAction("Deny") {
          override fun actionPerformed(event: AnActionEvent, notification: Notification) {
            denyIndexing(project, notification)
          }
        })
            .addAction(object : NotificationAction("Do not ask again...") {
          override fun actionPerformed(event: AnActionEvent, notification: Notification) {
            val result = MessageDialogBuilder.yesNoCancel("Do not ask again about the ngrams indexing permission?",
                    "Select an appropriate default option")
                    .yesText("Allow for all new projects")
                    .noText("Deny for all new projects")
                    .show()
            if (result != Messages.CANCEL) {
              PropertiesComponent.getInstance().setValue(DO_NOT_ASK_AGAIN_KEY, true)
              val yes = result == Messages.YES
              PropertiesComponent.getInstance().setValue(ALLOW_FOR_NEW_PROJECTS, yes)
              if (yes) allowIndexing(project, notification) else denyIndexing(project, notification)
            }
          }
        })
        .notify(project)
  }

  private fun answered(project: Project, notification: Notification) {
    PropertiesComponent.getInstance(project).setValue(ANSWERED, true)
    notification.expire()
  }

  private fun allowIndexing(project: Project) {
    val oldValue = NGramIndexingProperty.isEnabled(project)
    NGramIndexingProperty.setEnabled(project, true)
    if (!oldValue) {
      NGramFileBasedIndex.requestRebuild()
    }
  }

  private fun allowIndexing(project: Project, notification: Notification) {
    allowIndexing(project)
    answered(project, notification)
  }

  private fun denyIndexing(project: Project) {
    NGramIndexingProperty.setEnabled(project, false)
  }

  private fun denyIndexing(project: Project, notification: Notification) {
    denyIndexing(project)
    answered(project, notification)
  }

  private fun alreadyAnswered(project: Project): Boolean {
    return PropertiesComponent.getInstance(project).getBoolean(ANSWERED, false)
  }

  private fun askForNewProject(): Boolean {
    return !PropertiesComponent.getInstance().getBoolean(DO_NOT_ASK_AGAIN_KEY, false)
  }

  private fun allowedByDefault(): Boolean {
    return PropertiesComponent.getInstance().getBoolean(ALLOW_FOR_NEW_PROJECTS, false)
  }
}