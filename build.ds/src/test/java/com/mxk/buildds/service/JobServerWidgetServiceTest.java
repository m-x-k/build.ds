package com.mxk.buildds.service;

import com.mxk.buildds.config.JobServerConfig;
import com.mxk.buildds.model.Widgets;
import com.offbytwo.jenkins.JenkinsServer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class JobServerWidgetServiceTest {

    @Mock
    private JobServerConfig jobServerConfig;

    @Mock
    private JenkinsService jenkinsService;

    @InjectMocks
    private JobServerWidgetService jobServerWidgetService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpdateWidgets_jenkins_jobs() throws Exception {
        String username = "username";
        String password = "password";
        String url = "http://localhost";
        String type = "jenkins";
        ArrayList<Map> joblist = new ArrayList<Map>();
        JenkinsServer jenkinsServer = mock(JenkinsServer.class);

        when(jobServerConfig.getUsername()).thenReturn(username);
        when(jobServerConfig.getPassword()).thenReturn(password);
        when(jobServerConfig.getUrl()).thenReturn(url);
        when(jobServerConfig.getType()).thenReturn(type);
        when(jobServerConfig.getJoblist()).thenReturn(joblist);
        when(jenkinsService.getJenkinsServer(username, password, url)).thenReturn(jenkinsServer);
        when(jenkinsService.isJenkins(type)).thenReturn(true);

        jobServerWidgetService.updateWidgets();

        Widgets widgets = jobServerWidgetService.getWidgets();
        assertEquals(0, widgets.getWidgets().size());
        verify(jenkinsService).addJenkinsJobWidgets(jenkinsServer, widgets, joblist);
    }

    @Test
    public void testUpdateWidgets_unknown_type() throws Exception {
        String type = "unknown";
        when(jobServerConfig.getType()).thenReturn(type);
        when(jenkinsService.isJenkins(type)).thenReturn(false);

        jobServerWidgetService.updateWidgets();

        Widgets widgets = jobServerWidgetService.getWidgets();
        assertEquals(0, widgets.getWidgets().size());
    }
}