package com.peony.iview.define;

public enum CityAreas {
    BEIJING("北京"),
    TIANJING("天津"),
    SHANGHAI("上海"),
    SHENGZHEN("深圳"),
    CHOGNQING("重庆");

    private String name;

    private CityAreas(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
