package com.intellij.stats.collector.experiment.server;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static spark.Spark.get;
import static spark.Spark.port;


class ExperimentInfoRoute {
    
    private String salt = getSalt();
    private int experimentVersion = 1;

    private Map<String, DatedExperimentInfo> userExperimentInfo = new ConcurrentHashMap<>();

    private static String getSalt() {
        String uuid = UUID.randomUUID().toString();
        return uuid.split("-")[0];
    }

    public ExperimentInfo getExperimentInfo(Request request, Response response) {
        String uuid = request.params("uuid");
        int hash = (uuid + salt).hashCode();
        boolean performExperiment = hash % 2 == 0;
        ExperimentInfo info = new ExperimentInfo(experimentVersion, performExperiment);
        userExperimentInfo.put(uuid, new DatedExperimentInfo(info));
        return info;
    }
    
    public String getUsers(Request request, Response response) {
        StringBuilder builder = new StringBuilder();
        userExperimentInfo.entrySet().forEach((e) -> {
            String uid = e.getKey();
            DatedExperimentInfo info = e.getValue();
            String line = uid.split("-")[0] + ": " + info;
            builder.append(line).append('\n');
        });
        return builder.toString();
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