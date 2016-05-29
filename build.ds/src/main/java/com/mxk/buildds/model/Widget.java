package com.mxk.buildds.model;

public class Widget {

    private String label;
    private WidgetStatus widgetStatus = WidgetStatus.DISABLED;

    public Widget(String label, WidgetStatus widgetStatus) {
        this.label = label;
        this.widgetStatus = widgetStatus;
    }

    public String getLabel() {
        return label;
    }

    public WidgetStatus getWidgetStatus() {
        return widgetStatus;
    }
}
