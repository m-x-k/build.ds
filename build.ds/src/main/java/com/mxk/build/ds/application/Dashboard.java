package com.mxk.build.ds.application;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Dashboard {

    @RequestMapping("/dashboard")
    public String display(Model model) {
        model.addAttribute("name", "TEST");
        return "dashboard";
    }

}
