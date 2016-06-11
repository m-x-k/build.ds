package com.mxk.buildds.config;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class HealthCheckConfigTest {

    private HealthCheckConfig healthCheckConfig = new HealthCheckConfig();

    @Test
    public void testGetServers() throws Exception {
        ArrayList<Server> servers = new ArrayList<Server>();
        healthCheckConfig.setServers(servers);
        assertEquals(servers, healthCheckConfig.getServers());
    }
}