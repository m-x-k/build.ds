package com.mxk.buildds.model;

public enum WidgetStatus {

    SUCCESS, DANGER, WARNING, DISABLED;

    public String getName() {
        return this.name().toLowerCase();
    }
}
