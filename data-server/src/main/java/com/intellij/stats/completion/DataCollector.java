package com.intellij.stats.completion;

import static spark.Spark.*;

public class DataCollector {

    public static void main(String[] args) throws Exception {
        port(8080);

        StatisticSaver saver = new StatisticSaver();
        UploaderResource uploader = new UploaderResource(saver);

        //deprecated
        post("/stats/upload", uploader::receiveContent);
        
        post("/stats/upload/:uid", uploader::receiveFileContent);
        get("stats/pluginUsers", uploader::pluginUserInfos);
    }
    
}

