package org.szachniewicz.model;

import java.io.Serializable;

public class FormSchema implements Serializable {
    private String display;
    private String title;
    private String name;
    private String path;
    private Object components;

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Object getComponents() {
        return components;
    }

    public void setComponents(Object components) {
        this.components = components;
    }
}
