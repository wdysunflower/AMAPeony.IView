package com.peony.iview.define;

public enum CityAreaTypes {
    BEIJING("北京"),
    TIANJING("天津"),
    SHANGHAI("上海"),
    SHENGZHEN("深圳"),
    CHOGNQING("重庆");

    private String name;

    private CityAreaTypes(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
