package com.mxk.buildds.config;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class JobServerConfigTest {

    private JobServerConfig jobServerConfig = new JobServerConfig();

    @Test
    public void testGetType() throws Exception {
        String type = "";
        jobServerConfig.setType(type);
        assertEquals(type, jobServerConfig.getType());
    }

    @Test
    public void testGetUrl() throws Exception {
        String url = "";
        jobServerConfig.setUrl(url);
        assertEquals(url, jobServerConfig.getUrl());
    }

    @Test
    public void testGetUsername() throws Exception {
        String username = "";
        jobServerConfig.setUsername(username);
        assertEquals(username, jobServerConfig.getUsername());
    }

    @Test
    public void testGetPassword() throws Exception {
        String password = "";
        jobServerConfig.setPassword(password);
        assertEquals(password, jobServerConfig.getPassword());
    }

    @Test
    public void testGetJoblist() throws Exception {
        ArrayList<Map> joblist = new ArrayList<Map>();
        jobServerConfig.setJoblist(joblist);
        assertEquals(joblist, jobServerConfig.getJoblist());
    }

    @Test
    public void testToString() throws Exception {
        String expected = "JobServerConfig{type='null', url='null', username='null', password='null', joblist=null}";
        assertEquals(expected, jobServerConfig.toString());
    }
}