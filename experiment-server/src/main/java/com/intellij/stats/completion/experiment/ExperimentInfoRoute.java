package com.intellij.stats.completion.experiment;

import spark.Request;
import spark.Response;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

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
            String line = "<b>" + uid.split("-")[0] + ":</b> " + info;
            builder.append(line).append("<br>");
        });
        return builder.toString();
    }

    public Object nextExperiment(Request request, Response response) {
        salt = getSalt();
        experimentVersion++;
        return "ok";  
    }
}