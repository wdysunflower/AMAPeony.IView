package com.peony.iview.define;

/**
 * Created by wdynetposa on 14-8-15.
 */
public enum  ActionCategoryTypes {
    HIKING("徒步"),
    SHOOTING("摄影"),
    MOUNTAINEERING("登山");

    private String name;
    private ActionCategoryTypes(String name)
    {
        this.name=name;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
