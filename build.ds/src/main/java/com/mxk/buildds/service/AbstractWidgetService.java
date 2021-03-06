package com.mxk.buildds.service;

import com.mxk.buildds.model.Widgets;

public abstract class AbstractWidgetService {

    protected Widgets widgets = new Widgets();

    public Widgets getWidgets() {
        return widgets;
    }

    public abstract void updateWidgets();

}
