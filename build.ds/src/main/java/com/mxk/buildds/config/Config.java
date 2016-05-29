package com.mxk.buildds.config;

import java.util.List;

public class Config {
    private JobServer jobServer;
    private List<Job> jobList;

    public JobServer getJobServer() {
        return jobServer;
    }

    public void setJobServer(JobServer jobServer) {
        this.jobServer = jobServer;
    }

    public List<Job> getJobList() {
        return jobList;
    }

    public void setJobList(List<Job> jobList) {
        this.jobList = jobList;
    }

    @Override
    public String toString() {
        return "Config{" +
                "jobServer=" + jobServer +
                ", jobList=" + jobList +
                '}';
    }
}
