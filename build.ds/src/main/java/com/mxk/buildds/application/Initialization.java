package com.mxk.buildds.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mxk.buildds.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class Initialization {

    @Value("${dashboardConfig:config/config.json}")
    private String dashboardConfig;

    public Config getConfiguration() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File configFile = loadConfig();
        return mapper.readValue(configFile, Config.class);
    }

    private File loadConfig() {
        File configFile = new File(dashboardConfig);
        if (!configFile.exists()) {
            System.out.println("ERROR: Missing dashboardConfig");
            System.exit(1);
        }
        return configFile;
    }

}
