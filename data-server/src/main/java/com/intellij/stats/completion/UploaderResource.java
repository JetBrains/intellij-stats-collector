package com.intellij.stats.completion;

import spark.Request;
import spark.Response;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

public class UploaderResource {
    private StatisticSaver mySaver;

    public UploaderResource(StatisticSaver saver) {
        mySaver = saver;
    }

    public String receiveContent(Request request, Response response) {
        String uid = request.params("uid");
        String value = request.params("content");
        
        mySaver.dataReceived(uid, value.length());

        File dir = getDataDirectory();

        File file = new File(dir, uid);
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                response.status(500);
                return "Failed to create file"; 
            }
        }
        
        try {
            BufferedWriter writer = Files.newBufferedWriter(file.toPath(), StandardOpenOption.APPEND);
            writer.write(value);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            response.status(500);
            return "Failed to write data";
        }
        
        response.status(200);
        return "OK";
    }

    private File getDataDirectory() {
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdir();
        }
        return dir;
    }
    
    
    public String pluginUserInfos(Request request, Response response) {
        Collection<ContentInfo> allData = mySaver.getAllData();
        StringBuilder builder = new StringBuilder();

        int allUsers = allData.size();

        builder.append("Total received data from ")
                .append(allUsers)
                .append(" users");

        builder.append('\n');
        builder.append('\n');

        Set<String> ids = mySaver.getAllUserIds();
        for (String id : ids) {
            ContentInfo contentInfo = mySaver.getInfoFor(id);
            Date firstSentDate = new Date(contentInfo.firstSentTimestamp);
            Date lastSentDate = new Date(contentInfo.lastSentTimestamp);
            builder.append("User: ").append(id)
                    .append(" Total size (Kb): ").append(contentInfo.receivedDataKb)
                    .append(" First sent: ").append(firstSentDate)
                    .append(" Last sent: ").append(lastSentDate);
            builder.append('\n');
        }
        
        return builder.toString();
    }
    
    
    
}