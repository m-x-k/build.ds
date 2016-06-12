package com.mxk.buildds.controller;

import com.mxk.buildds.model.Widgets;
import com.mxk.buildds.service.HealthCheckWidgetService;
import com.mxk.buildds.service.JobServerWidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobServerWidgetController {

    private JobServerWidgetService jobServerWidgetService;

    @Autowired
    public JobServerWidgetController(JobServerWidgetService jobServerWidgetService) {
        this.jobServerWidgetService = jobServerWidgetService;
    }

    @RequestMapping("/widgets/jobs")
    public Widgets getJobWidgets() {
        return jobServerWidgetService.getWidgets();
    }

}
