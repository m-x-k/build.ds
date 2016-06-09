package com.mxk.buildds.service;

import com.mxk.buildds.config.JobServerConfig;
import com.mxk.buildds.model.Widgets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class JobServerWidgetService extends AbstractWidgetService {

    @Autowired
    private JobServerConfig jobServerConfig;

    @Override
    @Scheduled(fixedRate = 50000)
    public void updateWidgets() {
        widgets = new Widgets();
        System.out.printf("Jobs: %s%n", jobServerConfig);
    }
}
