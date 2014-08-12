package com.peony.iview.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.peony.iview.custom.IPageIndicator;
import com.peony.iview.custom.OnPageListener;
import com.peony.iview.global.ConstDefine;
import com.peony.iview.global.DataHelper;
import com.peony.iview.global.ShowToastUtil;

import java.util.ArrayList;

public class LandingActivity extends Activity implements OnPageListener {

    private ViewPager mViewPager;
    private IPageIndicator mIndicator;
    private ArrayList<View> mPageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_landing);

        InitialPageView();

        Boolean isFirst = DataHelper.IsFirstShow(this);
        if (!isFirst) {
            Button button = (Button) findViewById(R.id.bt_begin);
            button.setVisibility(View.VISIBLE);
        }
    }

    private void InitialPageView() {
        // 构造Page
        mPageViews = new ArrayList<View>();
        for (int i = 1; i < 6; i++) {
            ImageView view = new ImageView(LandingActivity.this);
            //String imgName = "landing_page" + i;
            String imgName = "landing_page_item1";
            int imgGid = getResources().getIdentifier(imgName, "drawable",
                    getPackageName());
            view.setImageResource(imgGid);
            view.setScaleType(ImageView.ScaleType.FIT_XY);
            mPageViews.add(view);
        }

        mViewPager = (ViewPager) findViewById(R.id.guidePages);
        mViewPager.setAdapter(new GuidePageAdapter());

        mIndicator = (IPageIndicator) findViewById(R.id.indicator);
        mIndicator.setViewPager(mViewPager);
        mIndicator.setPageListener(this);
    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if ((System.currentTimeMillis() - exitTime) > 2000) {
                    ShowToastUtil.showToast(this, "再按一次退出程序");
                    exitTime = System.currentTimeMillis();
                } else {
                    finish();
                    System.exit(0);
                }
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void OnBoundaryMove() {
        Begin();
    }

    @Override
    public void OnLastPageSelected() {
        Button button = (Button) findViewById(R.id.bt_begin);
        int vis = button.getVisibility();
        if (vis != View.VISIBLE) {
            DataHelper.SetData(this, ConstDefine.IS_FIRST_SHOW, false);
            button.setVisibility(View.VISIBLE);
        }
    }

    public void OnBTBeginClick(View view) {
        Begin();
    }

    private void Begin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra(LoginActivity.LOGIN_MODE, LoginActivity.LoginFragmentModes.Sign);
        intent.putExtra(LoginActivity.LOGIN_LOCATION,LoginActivity.LoginLocationTypes.Landing);
        startActivity(intent);
    }

    private class GuidePageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mPageViews.size();
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
            ((ViewPager) arg0).removeView(mPageViews.get(arg1));
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            // TODO Auto-generated method stub
            ((ViewPager) arg0).addView(mPageViews.get(arg1));
            return mPageViews.get(arg1);
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
