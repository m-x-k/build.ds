package com.mxk.buildds.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "jobserver", ignoreUnknownFields = true)
public class JobServerConfig {

    private String type;
    private String url;
    private String username;
    private String password;

    private List<Map> joblist;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Map> getJoblist() {
        return joblist;
    }

    public void setJoblist(List<Map> joblist) {
        this.joblist = joblist;
    }

    @Override
    public String toString() {
        return "JobServerConfig{" +
                "type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", joblist=" + joblist +
                '}';
    }
}
