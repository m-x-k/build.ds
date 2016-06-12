package com.mxk.buildds.service;

public enum JobServerType {

    JENKINS;

    public String getName() {
        return this.name().toLowerCase();
    }

}
