package com.peony.iview.model;

import com.peony.iview.define.ActionCategoryTypes;
import com.peony.iview.define.CityAreaTypes;

/**
 * Created by wdynetposa on 14-8-15.
 */
public class MActionData {

    public String Name;
    public int Image;
    public long Cost;
    public int PlanNum;
    public int RealNum;
    public String Location;
    public String Description;

    public String PubDate;
    public String ActBeginDate;
    public String ActEndDate;

    public ActionCategoryTypes Category;
    public CityAreaTypes City;

    @Override
    public String toString()
    {
        return Name;
    }
}
