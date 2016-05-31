package com.mxk.buildds.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mxk.buildds.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.File;
import java.io.IOException;

@SpringBootApplication(scanBasePackages = "com.mxk.buildds")
public class Application {

    private static String configLocation = "config/config.json";

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        Config config = loadConfig();
        System.out.println("CONFIG: " + config);
    }

    private static Config loadConfig() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(configLocation), Config.class);
    }

}
