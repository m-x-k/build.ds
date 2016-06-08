package com.mxk.buildds.model;

import java.util.ArrayList;
import java.util.List;

public class Widgets {

    private List<Widget> widgets = new ArrayList<Widget>();

    public List<Widget> getWidgets() {
        return widgets;
    }

    public void addWidget(Widget widget) {
        widgets.add(widget);
    }

    @Override
    public String toString() {
        return "Widgets{" +
                "widgets=" + widgets +
                '}';
    }
}
