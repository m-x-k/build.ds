package com.mxk.buildds.service;

import org.junit.Test;

import static org.junit.Assert.*;

public class JobServerTypeTest {

    @Test
    public void testGetName() throws Exception {
        assertEquals("jenkins", JobServerType.JENKINS.getName());
    }
}