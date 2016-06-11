package com.mxk.buildds.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class WidgetStatusTest {

    @Test
    public void testGetName() throws Exception {
        assertEquals(WidgetStatus.DANGER.getName(), "danger");
        assertEquals(WidgetStatus.DISABLED.getName(), "disabled");
        assertEquals(WidgetStatus.SUCCESS.getName(), "success");
        assertEquals(WidgetStatus.WARNING.getName(), "warning");
    }
}