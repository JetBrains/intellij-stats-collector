package com.intellij.stats.completion;

import static spark.Spark.*;

public class DataCollector {

    public static void main(String[] args) throws Exception {
        port(8080);

        StatisticSaver saver = new StatisticSaver();
        UploaderResource uploader = new UploaderResource(saver);

        post("/stats/upload", uploader::receiveContent);
        get("stats/pluginUsers", uploader::pluginUserInfos);
    }
    
}

