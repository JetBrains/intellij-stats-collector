/*
 * Copyright 2000-2017 JetBrains s.r.o.
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

package com.intellij.mocks

import com.intellij.stats.experiment.ExperimentDecision
import com.intellij.stats.network.service.RequestService
import com.intellij.stats.sender.StatisticSender
import com.intellij.stats.storage.UniqueFilesProvider
import com.nhaarman.mockito_kotlin.mock
import java.io.File


internal class TestExperimentDecision: ExperimentDecision {
    companion object {
        var isPerformExperiment = true
    }
    override fun isPerformExperiment(salt: String) = isPerformExperiment
}


internal class TestRequestService : RequestService() {

    companion object {
        var mock: RequestService = mock()
    }

    override fun post(url: String, params: Map<String, String>) = mock.post(url, params)
    override fun post(url: String, file: File) = mock.post(url, file)
    override fun postZipped(url: String, file: File) = mock.postZipped(url, file)
    override fun get(url: String) = mock.get(url)

}


internal class TestStatisticSender : StatisticSender {
    override fun sendStatsData(url: String) {
    }
}


class TestFilePathProvider: UniqueFilesProvider("chunk", ".", "logs-data")
