package com.mxk.buildds.config;

import org.junit.Test;

import static org.junit.Assert.*;

public class ServerTest {

    private Server server = new Server();

    @Test
    public void testGetUrl() throws Exception {
        String url = "";
        server.setUrl(url);
        assertEquals(url, server.getUrl());
    }

    @Test
    public void testGetLabel() throws Exception {
        String label = "";
        server.setLabel(label);
        assertEquals(label, server.getLabel());
    }
}