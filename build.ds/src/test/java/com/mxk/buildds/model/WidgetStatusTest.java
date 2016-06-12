package com.mxk.buildds.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class WidgetStatusTest {

    @Test
    public void testGetName() throws Exception {
        assertEquals("danger",   WidgetStatus.DANGER.getName());
        assertEquals("disabled", WidgetStatus.DISABLED.getName());
        assertEquals("success",  WidgetStatus.SUCCESS.getName());
        assertEquals("warning",  WidgetStatus.WARNING.getName());

        assertEquals(WidgetStatus.DANGER, WidgetStatus.valueOf("DANGER"));
    }
}