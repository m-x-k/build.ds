package com.mxk.buildds.service;

import com.mxk.buildds.model.Widget;
import com.mxk.buildds.model.WidgetStatus;
import com.mxk.buildds.model.Widgets;
import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JenkinsServiceTest {

    private Widgets widgets;
    private List<Map> joblist;
    private Map<String, Job> jobs;

    private JenkinsService jenkinsService = new JenkinsService();
    private JenkinsServer jenkinsServer = jenkinsService.getJenkinsServer("user", "pass", "http://localhost");

    @Mock
    private JobServerWidgetService jobServerWidgetService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        widgets = new Widgets();
        joblist = new ArrayList<Map>();
        jobs = new HashMap<String, Job>();
    }

    @Test
    public void testGetJenkinsServer() {
        assertNotNull(jenkinsService.getJenkinsServer("user", "pass", "http://localhost"));
        assertNotNull(jenkinsService.getJenkinsServer("user", "", "http://localhost"));
        assertNotNull(jenkinsService.getJenkinsServer("", "pass", "http://localhost"));
        assertNotNull(jenkinsService.getJenkinsServer("", "", "http://localhost"));
        assertNotNull(jenkinsService.getJenkinsServer(null, null, "http://localhost"));
    }

    @Test
    public void testGetJenkinsServer_uriexception() {
        assertNull(jenkinsService.getJenkinsServer(null, null, "=== URIEXCEPTION <>{}"));
    }

    @Test
    public void testGetJenkinsServer_null_url() {
        assertNull(jenkinsService.getJenkinsServer(null, null, null));
    }

    @Test
    public void testAddJenkinsJobWidgets_noJobs() throws Exception {
        jenkinsService.addJenkinsJobWidgets(jenkinsServer, widgets, new ArrayList<Map>());
        assertTrue(widgets.getWidgets().size() == 0);
    }

    @Test
    public void testAddJenkinsJobWidgets_withsuccessfuljob() throws Exception {
        joblist.add(createConfigJob("job1", "http://localhost"));
        JenkinsServer mockJenkins = mock(JenkinsServer.class);
        jobs.put("job1", mockJob(BuildResult.SUCCESS));
        when(mockJenkins.getJobs()).thenReturn(jobs);

        jenkinsService.addJenkinsJobWidgets(mockJenkins, widgets, joblist);

        List<Widget> widgets = this.widgets.getWidgets();
        assertEquals(1, widgets.size());
        assertEquals(WidgetStatus.SUCCESS, widgets.get(0).getWidgetStatus());
    }

    @Test
    public void testAddJenkinsJobWidgets_withunsuccessfuljob() throws Exception {
        joblist.add(createConfigJob("job1", "http://bogus"));
        JenkinsServer mockJenkins = mock(JenkinsServer.class);
        jobs.put("job1", mockJob(BuildResult.FAILURE));
        when(mockJenkins.getJobs()).thenReturn(jobs);

        jenkinsService.addJenkinsJobWidgets(mockJenkins, widgets, joblist);

        List<Widget> widgets = this.widgets.getWidgets();
        assertEquals(1, widgets.size());
        assertEquals(WidgetStatus.DANGER, widgets.get(0).getWidgetStatus());
    }

    @Test
    public void testAddJenkinsJobWidgets_job_not_exist() throws Exception {
        joblist.add(createConfigJob("unknown", "http://random"));
        JenkinsServer mockJenkins = mock(JenkinsServer.class);
        jobs.put("other", mockJob(BuildResult.SUCCESS));
        when(mockJenkins.getJobs()).thenReturn(jobs);

        jenkinsService.addJenkinsJobWidgets(mockJenkins, widgets, joblist);

        List<Widget> widgets = this.widgets.getWidgets();
        assertEquals(0, widgets.size());
    }

    @Test
    public void testAddJenkinsJobWidgets_no_jobs_returned() throws Exception {
        joblist.add(createConfigJob("job1", "http://bogus"));
        JenkinsServer mockJenkins = mock(JenkinsServer.class);
        jobs.put("job1", mockJobResponse(null, null, null, null, null, true));
        when(mockJenkins.getJobs()).thenReturn(jobs);

        jenkinsService.addJenkinsJobWidgets(mockJenkins, widgets, joblist);

        assertEquals(0, widgets.getWidgets().size());
    }

    @Test
    public void testAddJenkinsJobWidgets_job_no_details() throws Exception {
        joblist.add(createConfigJob("job1", "http://bogus"));
        JenkinsServer mockJenkins = mock(JenkinsServer.class);
        Job job = mock(Job.class);
        jobs.put("job1", mockJobResponse(job, null, null, null, null, true));
        when(mockJenkins.getJobs()).thenReturn(jobs);

        jenkinsService.addJenkinsJobWidgets(mockJenkins, widgets, joblist);

        assertEquals(0, widgets.getWidgets().size());
    }

    @Test
    public void testAddJenkinsJobWidgets_job_no_details_list() throws Exception {
        joblist.add(createConfigJob("job1", "http://bogus"));
        JenkinsServer mockJenkins = mock(JenkinsServer.class);
        Job job = mock(Job.class);
        JobWithDetails jobWithDetails = mock(JobWithDetails.class);
        jobs.put("job1", mockJobResponse(job, jobWithDetails, null, null, null, false));
        when(mockJenkins.getJobs()).thenReturn(jobs);

        jenkinsService.addJenkinsJobWidgets(mockJenkins, widgets, joblist);

        assertEquals(0, widgets.getWidgets().size());
    }

    @Test
    public void testAddJenkinsJobWidgets_job_no_lastbuild() throws Exception {
        joblist.add(createConfigJob("job1", "http://bogus"));
        JenkinsServer mockJenkins = mock(JenkinsServer.class);
        Job job = mock(Job.class);
        JobWithDetails jobWithDetails = mock(JobWithDetails.class);
        jobs.put("job1", mockJobResponse(job, jobWithDetails, null, null, null, true));
        when(mockJenkins.getJobs()).thenReturn(jobs);

        jenkinsService.addJenkinsJobWidgets(mockJenkins, widgets, joblist);

        assertEquals(0, widgets.getWidgets().size());
    }

    @Test
    public void testAddJenkinsJobWidgets_job_no_build_details() throws Exception {
        joblist.add(createConfigJob("job1", "http://bogus"));
        JenkinsServer mockJenkins = mock(JenkinsServer.class);
        Job job = mock(Job.class);
        JobWithDetails jobWithDetails = mock(JobWithDetails.class);
        Build jobLastBuild = mock(Build.class);
        jobs.put("job1", mockJobResponse(job, jobWithDetails, jobLastBuild, null, null, true));
        when(mockJenkins.getJobs()).thenReturn(jobs);

        jenkinsService.addJenkinsJobWidgets(mockJenkins, widgets, joblist);

        assertEquals(0, widgets.getWidgets().size());
    }

    @Test
    public void testAddJenkinsJobWidgets_job_no_build_result() throws Exception {
        joblist.add(createConfigJob("job1", "http://bogus"));
        JenkinsServer mockJenkins = mock(JenkinsServer.class);
        Job job = mock(Job.class);
        JobWithDetails jobWithDetails = mock(JobWithDetails.class);
        Build jobLastBuild = mock(Build.class);
        BuildWithDetails buildWithDetails = mock(BuildWithDetails.class);
        jobs.put("job1", mockJobResponse(job, jobWithDetails, jobLastBuild, buildWithDetails, null, true));
        when(mockJenkins.getJobs()).thenReturn(jobs);

        jenkinsService.addJenkinsJobWidgets(mockJenkins, widgets, joblist);

        assertEquals(0, widgets.getWidgets().size());
    }

    private Job mockJob(BuildResult buildResult) throws IOException {
        Job job = mock(Job.class);
        JobWithDetails jobWithDetails = mock(JobWithDetails.class);
        Build jobLastBuild = mock(Build.class);
        BuildWithDetails buildWithDetails = mock(BuildWithDetails.class);

        return mockJobResponse(job, jobWithDetails, jobLastBuild, buildWithDetails, buildResult, true);
    }

    private Job mockJobResponse(Job job, JobWithDetails jobWithDetails, Build jobLastBuild,
                                BuildWithDetails buildWithDetails, BuildResult buildResult, boolean allBuilds) throws IOException {

        if (job != null) {
            when(job.details()).thenReturn(jobWithDetails);

            if (jobWithDetails != null) {

                if (allBuilds) {
                    ArrayList<Build> builds = new ArrayList<Build>();
                    builds.add(jobLastBuild);
                    when(jobWithDetails.getAllBuilds()).thenReturn(builds);
                }
                when(jobWithDetails.getLastBuild()).thenReturn(jobLastBuild);

                if (jobLastBuild != null) {
                    when(jobLastBuild.details()).thenReturn(buildWithDetails);

                    if (buildWithDetails != null)
                        when(buildWithDetails.getResult()).thenReturn(buildResult);
                }
            }
        }

        return job;
    }

    private Map createConfigJob(String label, String path) {
        Map<String, String> job = new HashMap<String, String>();
        job.put("label", label);
        job.put("path", path);
        return job;
    }

    @Test
    public void testIsJenkins_true() throws Exception {
        assertTrue(jenkinsService.isJenkins("jenkins"));
    }

    @Test
    public void testIsJenkins_false() throws Exception {
        assertFalse(jenkinsService.isJenkins(""));
    }

    @Test
    public void testIsJenkins_empty() throws Exception {
        assertFalse(jenkinsService.isJenkins(null));
    }

    @Test
    public void testIsJenkins_mismatch() throws Exception {
        assertFalse(jenkinsService.isJenkins("other"));
    }
}