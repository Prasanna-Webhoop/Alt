package com.alt.login.model;

public class NavDrawerItem {

    private String navigationName;
    private boolean selected;

    public NavDrawerItem(String navigationName, boolean selected) {
        this.navigationName = navigationName;
        this.selected = selected;
    }

    public String getNavigationName() {
        return navigationName;
    }

    public void setNavigationName(String navigationName) {
        this.navigationName = navigationName;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}