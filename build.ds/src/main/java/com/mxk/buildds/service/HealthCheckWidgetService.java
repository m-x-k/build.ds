package com.mxk.buildds.service;

import com.mxk.buildds.config.HealthCheckConfig;
import com.mxk.buildds.model.Widget;
import com.mxk.buildds.model.WidgetStatus;
import com.mxk.buildds.model.Widgets;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class HealthCheckWidgetService {

    @Autowired
    private HealthCheckConfig healthCheckConfig;

    private Widgets widgets;

    public Widgets getWidgets() {
        return widgets;
    }

    @Scheduled(fixedRate = 5000)
    private void setWidgets() {
        widgets = new Widgets();
        List<String> servers = healthCheckConfig.getServers();

        for (String server : servers) {
            HttpGet httpGet = new HttpGet(server);
            WidgetStatus status = getServerStatus(httpGet);
            widgets.addWidget(new Widget(server, status));
        }

        System.out.println("Updating: " + widgets);
    }

    private WidgetStatus getServerStatus(HttpGet httpGet) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        int statusCode = -1;
        try {
            CloseableHttpResponse response = httpclient.execute(httpGet);
            statusCode = response.getStatusLine().getStatusCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getWidgetStatusForStatusCode(statusCode);
    }

    private WidgetStatus getWidgetStatusForStatusCode(int statusCode) {
        if (statusCode == 200)
            return WidgetStatus.SUCCESS;
        else
            return WidgetStatus.DANGER;
    }

}
