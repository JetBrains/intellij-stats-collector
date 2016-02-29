package com.intellij.stats.completion.experiment;

import com.google.gson.Gson;

import java.util.Date;

import static spark.Spark.get;
import static spark.Spark.port;


public class ExperimentServer {

    public static void main(String[] args) {
        port(8090);
        
        Gson gson = new Gson();
        ExperimentInfoRoute route = new ExperimentInfoRoute();
        
        get("/experiment/info/:uuid", route::getExperimentInfo, gson::toJson);
        get("/experiment/nextExperiment", route::nextExperiment);
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