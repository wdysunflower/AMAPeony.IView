package com.peony.iview.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.peony.iview.custom.IPageIndicator;
import com.peony.iview.custom.OnPageListener;

import java.util.ArrayList;

public class LandingPageActivity extends Activity implements OnPageListener {

    private ViewPager _viewPager;
    private IPageIndicator _indicator;
    private ArrayList<View> _pageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_landing_page);
        InitialPageView();
    }

    private void InitialPageView() {
        // 构造Page
        _pageViews = new ArrayList<View>();
        for (int i = 1; i < 6; i++) {
            ImageView view = new ImageView(LandingPageActivity.this);
            //String imgName = "landing_page" + i;
            String imgName = "landing_page_item1";
            int imgGid = getResources().getIdentifier(imgName, "drawable",
                    getPackageName());
            view.setImageResource(imgGid);
            view.setScaleType(ImageView.ScaleType.FIT_XY);
            _pageViews.add(view);
        }

        _viewPager = (ViewPager) findViewById(R.id.guidePages);
        _viewPager.setAdapter(new GuidePageAdapter());

        _indicator = (IPageIndicator) findViewById(R.id.indicator);
        _indicator.setViewPager(_viewPager);
        _indicator.setPageListener(this);
    }

    @Override
    public void OnBoundaryMoveEvent() {
        Intent intent = new Intent(this, MainActivity.class);
//        intent.putExtra(LoginRegActivity.KEY_INIT, true);
        startActivity(intent);
    }

    private class GuidePageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return _pageViews.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getItemPosition(Object object) {
            // TODO Auto-generated method stub
            return super.getItemPosition(object);
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            // TODO Auto-generated method stub
            ((ViewPager) arg0).removeView(_pageViews.get(arg1));
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            // TODO Auto-generated method stub
            ((ViewPager) arg0).addView(_pageViews.get(arg1));
            return _pageViews.get(arg1);
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
            // TODO Auto-generated method stub

        }

        @Override
        public Parcelable saveState() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void finishUpdate(View arg0) {
            // TODO Auto-generated method stub

        }
    }
}
