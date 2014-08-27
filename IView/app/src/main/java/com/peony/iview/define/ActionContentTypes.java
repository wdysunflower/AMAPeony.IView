package com.peony.iview.define;

public enum ActionContentTypes {
    List("列表"),
    Map("地图"),
    Calender("日历");

    private String name;

    private ActionContentTypes(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
