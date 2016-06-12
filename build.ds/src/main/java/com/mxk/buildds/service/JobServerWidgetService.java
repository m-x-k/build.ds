package com.mxk.buildds.service;

import com.mxk.buildds.config.JobServerConfig;
import com.mxk.buildds.model.Widgets;
import com.offbytwo.jenkins.JenkinsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JobServerWidgetService extends AbstractWidgetService {

    @Autowired
    private JobServerConfig jobServerConfig;

    @Autowired
    private JenkinsService jenkinsService;

    @Override
    @Scheduled(fixedRate = 50000)
    public void updateWidgets() {
        widgets = new Widgets();

        String type = jobServerConfig.getType();
        if (jenkinsService.isJenkins(type)) {
            String username = jobServerConfig.getUsername();
            String password = jobServerConfig.getPassword();
            String url = jobServerConfig.getUrl();
            List<Map> joblist = jobServerConfig.getJoblist();

            JenkinsServer jenkinsServer = jenkinsService.getJenkinsServer(username, password, url);

            jenkinsService.addJenkinsJobWidgets(jenkinsServer, widgets, joblist);
        }

        System.out.printf("Jobs: %s%n", jobServerConfig);
    }

}
