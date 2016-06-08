package com.mxk.buildds.controller;

import com.mxk.buildds.model.Widget;
import com.mxk.buildds.model.WidgetStatus;
import com.mxk.buildds.model.Widgets;
import com.mxk.buildds.service.HealthCheckWidgetService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasToString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class WidgetControllerTest {

    private MockMvc mvc;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Mock
    private HealthCheckWidgetService healthCheckWidgetService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Widgets widgets = new Widgets();
        widgets.addWidget(new Widget("labelX", WidgetStatus.SUCCESS));
        when(healthCheckWidgetService.getWidgets()).thenReturn(widgets);
        mvc = MockMvcBuilders.standaloneSetup(new WidgetController(healthCheckWidgetService)).build();
    }

    @Test
    public void testGetWidgets() throws Exception {
        String expectedSuccessWidget = "{\"widgets\":[{\"label\":\"labelX\",\"widgetStatus\":\"SUCCESS\"}]}";
        mvc.perform(MockMvcRequestBuilders.get("/widgets/health").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(content().string(expectedSuccessWidget));
    }
}