package com.intellij.stats.completion;

import org.apache.commons.codec.Charsets;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import spark.Request;
import spark.Response;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

class UploadData {
    public final String uid;
    public final String content;

    public UploadData(String uid, String content) {
        this.uid = uid;
        this.content = content;
    }
    
    public boolean isOK() {
        return uid != null && content != null;
    }
}

public class UploaderResource {
    private StatisticSaver mySaver;

    public UploaderResource(StatisticSaver saver) {
        mySaver = saver;
    }

    public String receiveFileContent(Request request, Response response) {
        String uid = request.params(":uid");
        String body = request.body();
        UploadData uploadData = new UploadData(uid, body);
        return processUploadData(response, uploadData, "json_");
    }
    
    public String receiveContent(Request request, Response response) {
        UploadData uploadData = getUploadData(request);
        return processUploadData(response, uploadData);
    }

    private String processUploadData(Response response, UploadData uploadData) {
        return processUploadData(response, uploadData, "");
    }
    
    private String processUploadData(Response response, UploadData uploadData, String filePrefix) {
        if (!uploadData.isOK()) {
            response.status(500);
            return "Upload data is invalid";
        }

        String uid = uploadData.uid;
        String content = uploadData.content;
        
        mySaver.dataReceived(uid, content.length());

        File dir = getDataDirectory();

        File file = new File(dir, filePrefix + uid);
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
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            response.status(500);
            return "Failed to write data";
        }
        
        response.status(200);
        return "OK";
    }

    private UploadData getUploadData(Request request) {
        String uid = null;
        String content = null;
        
        List<NameValuePair> params = URLEncodedUtils.parse(request.body(), Charsets.UTF_8);
        for (NameValuePair param : params) {
            if ("uid".equals(param.getName())) {
                uid = param.getValue();
            } 
            else if ("content".equals(param.getName())) {
                content = param.getValue();
            }
        }

        return new UploadData(uid, content);
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
        builder.append("<ul>");
        for (String id : ids) {
            builder.append("<li>");
            ContentInfo contentInfo = mySaver.getInfoFor(id);
            Date firstSentDate = new Date(contentInfo.firstSentTimestamp);
            Date lastSentDate = new Date(contentInfo.lastSentTimestamp);
            builder.append("<b>User:</b> ").append(id)
                    .append(" <b>Total size (Kb):</b> ").append(contentInfo.receivedDataKb)
                    .append(" <b>First sent:</b> ").append(firstSentDate)
                    .append(" <b>Last sent:</b> ").append(lastSentDate);
            builder.append("</li>");
            builder.append('\n');
        }
        builder.append("</ul>");
        
        return builder.toString();
    }
    
    
    
}