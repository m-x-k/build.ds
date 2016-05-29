package com.mxk.buildds.config;

public class Job {

    private String label;
    private String path;
    private Boolean display;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getDisplay() {
        return display;
    }

    public void setDisplay(Boolean display) {
        this.display = display;
    }

    @Override
    public String toString() {
        return "Job{" +
                "label='" + label + '\'' +
                ", path='" + path + '\'' +
                ", display=" + display +
                '}';
    }
}
