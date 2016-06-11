package com.mxk.buildds.service;

import com.mxk.buildds.config.HealthCheckConfig;
import com.mxk.buildds.config.Server;
import com.mxk.buildds.model.Widget;
import com.mxk.buildds.model.WidgetStatus;
import com.mxk.buildds.model.Widgets;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.mockito.mockpolicies.Slf4jMockPolicy;
import org.powermock.core.classloader.annotations.MockPolicy;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isNull;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@MockPolicy(Slf4jMockPolicy.class)
@PrepareForTest(HttpClients.class)
public class HealthCheckWidgetServiceTest {

    public static final int SUCCESS_STATUS_CODE = 200;
    public static final int DANGER_STATUS_CODE = 500;

    @Mock
    private HealthCheckConfig healthCheckConfig;

    @InjectMocks
    private HealthCheckWidgetService healthCheckWidgetService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpdateWidgets_emptyWidgetList() throws Exception {
        healthCheckWidgetService.updateWidgets();
        Widgets widgets = healthCheckWidgetService.getWidgets();
        assertEquals(0, widgets.getWidgets().size());
    }

    /*
     * Scenario: Update Widget list
     *
     * Given a list of endpoints
     * When the widget list is updated
     * And the first endpoint responds successfully
     * And the second endpoint does not respond
     * Then the updated widget list is returned
     * And the first widget is marked as success
     * And the second widget is marked as danger
     */
    @Test
    public void testUpdateWidgets() throws Exception {
        List<Server> servers = new ArrayList<Server>();
        Server firstServerSuccess = createServer("success", "http://localhost");
        servers.add(firstServerSuccess);
        Server secondServerFailed = createServer("failed", "http://localhost");
        servers.add(secondServerFailed);
        when(healthCheckConfig.getServers()).thenReturn(servers);
        mockHttpGetStatus(SUCCESS_STATUS_CODE, DANGER_STATUS_CODE);
        assertWidgetListIsEmpty();

        healthCheckWidgetService.updateWidgets();

        List<Widget> widgetList = getWidgetList();
        int size = widgetList.size();
        assertEquals(2, size);
        assertServerWidget(firstServerSuccess, widgetList, 0, WidgetStatus.SUCCESS);
        assertServerWidget(secondServerFailed, widgetList, 1, WidgetStatus.DANGER);
    }

    private List<Widget> getWidgetList() {
        Widgets widgets = healthCheckWidgetService.getWidgets();
        return widgets.getWidgets();
    }

    private void assertWidgetListIsEmpty() {
        List<Widget> widgetList = getWidgetList();
        int size = widgetList.size();
        assertEquals(0, size);
    }

    private void assertServerWidget(Server expectedServer, List<Widget> widgetList, int pos,
                                    WidgetStatus widgetStatus) {
        assertEquals(widgetList.get(pos).getLabel(), expectedServer.getLabel());
        assertEquals(widgetList.get(pos).getWidgetStatus(), widgetStatus);
    }

    private Server createServer(String label, String url) {
        Server server = new Server();
        server.setLabel(label);
        server.setUrl(url);
        return server;
    }

    private void mockHttpGetStatus(Integer firstStatusCode, Integer... otherStatusCodes) throws IOException {
        PowerMockito.mockStatic(HttpClients.class);
        CloseableHttpClient closeableHttpClient = mock(CloseableHttpClient.class);
        CloseableHttpResponse closeableHttpResponse = mock(CloseableHttpResponse.class);
        StatusLine statusLine = mock(StatusLine.class);

        when(statusLine.getStatusCode()).thenReturn(firstStatusCode, otherStatusCodes);
        when(closeableHttpResponse.getStatusLine()).thenReturn(statusLine);
        when(closeableHttpClient.execute(any(HttpGet.class))).thenReturn(closeableHttpResponse);
        when(HttpClients.createDefault()).thenReturn(closeableHttpClient);
    }
}