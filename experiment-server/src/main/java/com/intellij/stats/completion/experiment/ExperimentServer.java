package com.intellij.stats.completion.experiment;

import com.google.gson.Gson;

import java.util.Date;

import static spark.Spark.get;
import static spark.Spark.port;


public class ExperimentServer {

    public static void main(String[] args) {
        port(8090);
        
        Gson gson = new Gson();

        ConfigManager manager = new ConfigManager();
        Config config = manager.readConfig();
        if (config == null) {
            config = new Config(1);
            manager.writeConfig(config);
        }
        
        ExperimentInfoRoute route = new ExperimentInfoRoute(config, manager);
        
        get("/experiment/info/:uuid", route::getExperimentInfo, gson::toJson);
        get("/experiment/reloadConfig", route::reloadConfig);
        get("/experiment/users", route::getUsers);
    }
    
}

class DatedExperimentInfo {
    public final ExperimentInfo experimentInfo;
    public final Date date;

    public DatedExperimentInfo(ExperimentInfo info) {
        experimentInfo = info;
        date = new Date();
    }

    @Override
    public String toString() {
        return experimentInfo + ", " + date;
    }
}

class ExperimentInfo {
    public int experimentVersion = 0;
    public boolean performExperiment = false;

    ExperimentInfo(int experimentVersion, boolean performExperiment) {
        this.experimentVersion = experimentVersion;
        this.performExperiment = performExperiment;
    }

    @Override
    public String toString() {
        return "Experiment=" + performExperiment + ", Version=" + experimentVersion;  
    }
}