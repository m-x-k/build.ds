package com.mxk.buildds.service;

import com.mxk.buildds.config.JobServerConfig;
import com.mxk.buildds.model.Widgets;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class JobServerWidgetServiceTest {

    @Mock
    private JobServerConfig jobServerConfig;

    @InjectMocks
    private JobServerWidgetService jobServerWidgetService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpdateWidgets_emptyWidgetList() throws Exception {
        jobServerWidgetService.updateWidgets();
        Widgets widgets = jobServerWidgetService.getWidgets();
        assertEquals(0, widgets.getWidgets().size());
    }
}