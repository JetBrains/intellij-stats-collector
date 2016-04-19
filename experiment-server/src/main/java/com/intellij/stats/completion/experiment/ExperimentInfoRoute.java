package com.intellij.stats.completion.experiment;

import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import spark.Request;
import spark.Response;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


class Config {
    public String salt = newSalt();
    public int experimentVersion = 0;

    public Config(String salt, int experimentVersion) {
        this.salt = salt;
        this.experimentVersion = experimentVersion;
    }

    public Config(int experimentVersion) {
        this.salt = newSalt();
        this.experimentVersion = experimentVersion;
    }
    
    private String newSalt() {
        String uuid = UUID.randomUUID().toString();
        return uuid.split("-")[0];
    }
}


class ConfigManager {
    public static final String CONFIG_FILE = "config.json";
    
    private Gson gson = new Gson();

    private static File getConfigFile() {
        return new File(CONFIG_FILE);
    }

    public Config readConfig() {
        try {
            String config = Files.toString(getConfigFile(), Charset.defaultCharset());
            if (!config.isEmpty()) {
                return gson.fromJson(config, Config.class);
            }
        } catch (IOException | JsonSyntaxException ignore) {
        }

        return null;
    }

    public boolean writeConfig(Config config) {
        String value = gson.toJson(config);
        try {
            Files.write(value, getConfigFile(), Charset.defaultCharset());
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}

class ExperimentInfoRoute {
    
    private Map<String, DatedExperimentInfo> userExperimentInfo = new ConcurrentHashMap<>();
    private final ConfigManager configManager;
    private Config config;

    public ExperimentInfoRoute(Config config, ConfigManager manager) {
        this.config = config;
        this.configManager = manager;
    }

    public ExperimentInfo getExperimentInfo(Request request, Response response) {
        String uuid = request.params("uuid");
        int hash = (uuid + config.salt).hashCode();
        boolean performExperiment = hash % 2 == 0;
        ExperimentInfo info = new ExperimentInfo(config.experimentVersion, performExperiment);
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

    public Object reloadConfig(Request request, Response response) {
        Config newConfig = configManager.readConfig();
        if (newConfig != null) {
            config = newConfig;
            return "ok";
        }
        return "Can't read config file";
    }
}