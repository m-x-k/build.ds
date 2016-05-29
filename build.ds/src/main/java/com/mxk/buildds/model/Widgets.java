package com.mxk.buildds.model;

import java.util.ArrayList;
import java.util.List;

public class Widgets {

    private List<Widget> widgets;

    public Widgets() {
        // TODO adding for UX test purposes
        List<Widget> widgets = new ArrayList<Widget>();
        widgets.add(new Widget("Success", WidgetStatus.SUCCESS));
        widgets.add(new Widget("Danger", WidgetStatus.DANGER));
        widgets.add(new Widget("Disabled", WidgetStatus.DISABLED));
        widgets.add(new Widget("Warning", WidgetStatus.WARNING));
        this.widgets = widgets;
    }

    public List<Widget> getWidgets() {
        return widgets;
    }
}
