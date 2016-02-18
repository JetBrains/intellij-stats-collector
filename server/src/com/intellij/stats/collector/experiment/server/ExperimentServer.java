package com.intellij.stats.collector.experiment.server;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.util.UUID;

import static spark.Spark.get;
import static spark.Spark.port;


class ExperimentInfoRoute {
    
    private String salt = getSalt();
    private int experimentVersion = 1;

    private static String getSalt() {
        String uuid = UUID.randomUUID().toString();
        return uuid.split("-")[0];
    }

    public ExperimentInfo getExperimentInfo(Request request, Response response) {
        String uuid = request.params("uuid");
        int hash = (uuid + salt).hashCode();
        boolean performExperiment = hash % 2 == 0;
        return new ExperimentInfo(experimentVersion, performExperiment);
    }
    
    public Object nextExperiment(Request request, Response response) {
        salt = getSalt();
        experimentVersion++;
        return "ok";  
    }
}


public class ExperimentServer {

    public static void main(String[] args) {
        port(8090);
        
        Gson gson = new Gson();
        ExperimentInfoRoute route = new ExperimentInfoRoute();
        
        get("/experiment/info/:uuid", route::getExperimentInfo, gson::toJson);
        get("/experiment/nextExperiment", route::nextExperiment);
    }
    
}

class ExperimentInfo {
    private int experimentVersion = 0;
    private boolean performExperiment = false;

    ExperimentInfo(int experimentVersion, boolean performExperiment) {
        this.experimentVersion = experimentVersion;
        this.performExperiment = performExperiment;
    }
}