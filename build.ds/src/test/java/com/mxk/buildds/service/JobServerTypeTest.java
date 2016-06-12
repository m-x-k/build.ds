package com.mxk.buildds.service;

import org.junit.Test;

import static com.mxk.buildds.service.JobServerType.JENKINS;
import static org.junit.Assert.*;

public class JobServerTypeTest {

    @Test
    public void testGetName() throws Exception {
        assertEquals("jenkins", JENKINS.getName());

        JobServerType[] values = JobServerType.values();
        JobServerType[] expected = new JobServerType[1];
        expected[0] = JENKINS;
        assertArrayEquals(expected, values);

        assertEquals(JENKINS, JobServerType.valueOf("JENKINS"));
    }
}