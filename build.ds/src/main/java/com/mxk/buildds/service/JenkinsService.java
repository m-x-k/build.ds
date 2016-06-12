package com.mxk.buildds.service;

import com.mxk.buildds.model.Widget;
import com.mxk.buildds.model.WidgetStatus;
import com.mxk.buildds.model.Widgets;
import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.client.JenkinsHttpClient;
import com.offbytwo.jenkins.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;

import static com.mxk.buildds.service.JobServerType.JENKINS;

@Service
public class JenkinsService {

    private static final Logger LOG = LoggerFactory.getLogger(JenkinsService.class);

    public static final String LABEL = "label";
    public static final String SUCCESS = "success";

    public void addJenkinsJobWidgets(JenkinsServer jenkins, Widgets widgets, List<Map> joblist) {
        try {
            Map<String, Job> jobs = jenkins.getJobs();
            for (String jobName : jobs.keySet()) {
                if (jobExists(jobName, joblist))
                    addJenkinsJobWidget(widgets, jobs, jobName);
            }
        } catch (IOException e) {
            LOG.debug("IOException adding jenkins jobs: " + e.getMessage());
        }
    }

    public JenkinsServer getJenkinsServer(String username, String password, String url) {
        URI jenkinsUri = getJenkinsUri(url);
        if (jenkinsUri == null)
            return null;

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return new JenkinsServer(new JenkinsHttpClient(jenkinsUri));
        } else {
            return new JenkinsServer(new JenkinsHttpClient(jenkinsUri, username, password));
        }
    }

    private URI getJenkinsUri(String url) {
        try {
            return new URI(url);
        }
        catch (Exception e) {
            LOG.error("URISyntaxException getting jenkins server instance: " + e.getMessage());
        }
        return null;
    }

    private boolean jobExists(String jobName, List<Map> jobList) {
        for (Map jobDetails : jobList) {
            String label = (String) jobDetails.get(LABEL);
            if (label.equalsIgnoreCase(jobName)) {
                return true;
            }
        }
        return false;
    }

    private void addJenkinsJobWidget(Widgets widgets, Map<String, Job> jobs, String jobName) throws IOException {
        Job job = jobs.get(jobName);
        if (job == null)
            return;

        JobWithDetails details = job.details();
        if (details == null || details.getAllBuilds().isEmpty())
            return;

        Build lastBuild = details.getLastBuild();
        if (lastBuild == null)
            return;

        BuildWithDetails buildWithDetails = lastBuild.details();
        if (buildWithDetails == null)
            return;

        BuildResult result = buildWithDetails.getResult();
        if (result == null)
            return;

        addWidgetWithStatus(widgets, jobName, result);
    }

    private void addWidgetWithStatus(Widgets widgets, String jobName, BuildResult result) {
        boolean buildSuccess = result.name().equalsIgnoreCase(SUCCESS);
        if (buildSuccess) {
            widgets.addWidget(new Widget(jobName, WidgetStatus.SUCCESS));
        } else {
            widgets.addWidget(new Widget(jobName, WidgetStatus.DANGER));
        }
    }

    public boolean isJenkins(String type) {
        return !StringUtils.isEmpty(type) && type.equalsIgnoreCase(JENKINS.getName());
    }

}
