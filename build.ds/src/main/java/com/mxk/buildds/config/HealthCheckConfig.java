package com.mxk.buildds.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "healthcheck", ignoreUnknownFields = true)
public class HealthCheckConfig {

    private List<Server> servers = new ArrayList<Server>();

    public List<Server> getServers() {
        return servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }
}
