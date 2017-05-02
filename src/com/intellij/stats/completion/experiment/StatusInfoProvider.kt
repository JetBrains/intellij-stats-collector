package com.intellij.stats.completion.experiment

import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.application.PermanentInstallationID
import com.intellij.openapi.components.ServiceManager
import com.intellij.stats.completion.RequestService
import com.intellij.stats.completion.assertNotEDT

interface ExperimentDecision {
    fun isPerformExperiment(salt: String): Boolean
}

class PermanentInstallationIDBasedDecision : ExperimentDecision {
    override fun isPerformExperiment(salt: String): Boolean {
        val uid = PermanentInstallationID.get()
        val hash = (uid + salt).hashCode()
        return hash % 2 == 0
    }
}


class StatusInfoProvider(private val requestSender: RequestService,
                         private val experimentDecision: ExperimentDecision) {

    companion object {
        private val GSON = Gson()
        private val STATUS_URL = "https://www.jetbrains.com/config/features-service-status.json"

        private val SALT = "completion.stats.experiment.salt"
        private val EXPERIMENT_VERSION_KEY = "completion.stats.experiment.version"
        private val PERFORM_EXPERIMENT_KEY = "completion.ml.perform.experiment"

        fun getInstance(): StatusInfoProvider = ServiceManager.getService(StatusInfoProvider::class.java)
    }

    @Volatile private var info: ExperimentInfo = loadInfo()
    @Volatile private var serverStatus = ""
    @Volatile private var dataServerUrl = ""

    fun getExperimentVersion() = info.experimentVersion
    
    fun getDataServerUrl(): String = dataServerUrl
    
    fun isServerOk(): Boolean = serverStatus.equals("ok", ignoreCase = true)
    
    fun isPerformExperiment(): Boolean {
        if (!info.performExperiment) {
            return false
        }
        return experimentDecision.isPerformExperiment(info.salt)
    }
    
    fun updateStatus() {
        serverStatus = ""
        dataServerUrl = ""
        
        assertNotEDT()
        val response = requestSender.get(STATUS_URL)
        if (response != null && response.isOK()) {
            val map = GSON.fromJson(response.text, LinkedTreeMap::class.java)
            
            val salt = map["salt"]?.toString()
            val experimentVersion = map["experimentVersion"]?.toString()
            val performExperiment = map["performExperiment"]?.toString() ?: "false"

            if (salt != null && experimentVersion != null) {
                //should be Int always
                val floatVersion = experimentVersion.toFloat()
                info = ExperimentInfo(floatVersion.toInt(), salt, performExperiment.toBoolean())
                saveInfo(info)
            }
            
            serverStatus = map["status"]?.toString() ?: ""
            dataServerUrl = map["urlForZipBase64Content"]?.toString() ?: ""
        }
    }

    private fun loadInfo(): ExperimentInfo {
        return with (PropertiesComponent.getInstance()) {
            val salt = getValue(SALT, "")
            val experimentVersion = getInt(EXPERIMENT_VERSION_KEY, 0)
            val performExperiment = getBoolean(PERFORM_EXPERIMENT_KEY, false)
            ExperimentInfo(experimentVersion, salt, performExperiment)
        }
    }

    private fun saveInfo(info: ExperimentInfo) {
        with (PropertiesComponent.getInstance()) {
            setValue(SALT, info.salt)
            setValue(EXPERIMENT_VERSION_KEY, info.experimentVersion.toString())
            setValue(PERFORM_EXPERIMENT_KEY, info.performExperiment)
        }
    }

}

data class ExperimentInfo(var experimentVersion: Int, var salt: String, var performExperiment: Boolean) {
    constructor() : this(0, "", false)
}