package com.mxk.buildds.controller;

import com.mxk.buildds.model.Widgets;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WidgetController {

    @RequestMapping("/widgets")
    public Widgets getWidgets() {
        return new Widgets();
    }

}
