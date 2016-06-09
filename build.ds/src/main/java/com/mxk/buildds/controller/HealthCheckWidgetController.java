package com.mxk.buildds.controller;

import com.mxk.buildds.model.Widgets;
import com.mxk.buildds.service.HealthCheckWidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckWidgetController {

    private HealthCheckWidgetService healthCheckWidgetService;

    @Autowired
    public HealthCheckWidgetController(HealthCheckWidgetService healthCheckWidgetService) {
        this.healthCheckWidgetService = healthCheckWidgetService;
    }

    @RequestMapping("/widgets/health")
    public Widgets getHealthWidgets() {
        return healthCheckWidgetService.getWidgets();
    }

}
