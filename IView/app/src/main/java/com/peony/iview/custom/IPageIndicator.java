package com.peony.iview.custom;

import android.support.v4.view.ViewPager;

/**
 * Created by wdynetposa on 14-8-7.
 */

public interface IPageIndicator extends ViewPager.OnPageChangeListener {

    void setPageListener(OnPageListener listener);

    void setViewPager(ViewPager view);

    void setCurrentItem(int item);
}
