package com.mxk.buildds.controller;

import com.mxk.buildds.config.JobServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

    @Autowired
    private JobServerConfig jobServerConfig;

    @RequestMapping("/")
    public String display(Model model) {
        return "dashboard";
    }

}
