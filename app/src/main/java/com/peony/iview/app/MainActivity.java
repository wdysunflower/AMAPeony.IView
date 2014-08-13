package com.peony.iview.app;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.peony.iview.global.ShowToastUtil;
import com.peony.iview.module.ActionActivity;
import com.peony.iview.module.GroupActivity;
import com.peony.iview.module.MineActivity;
import com.peony.iview.module.MoreActivity;
import com.peony.iview.module.RouteActivity;

public class MainActivity extends Activity {
    private TabHost tabHost;
    private TabWidget widget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        tabHost = (TabHost) this.findViewById(android.R.id.tabhost);

        LocalActivityManager mLocalActivityManager = new LocalActivityManager(this, false);
        mLocalActivityManager.dispatchCreate(savedInstanceState);
        tabHost.setup(mLocalActivityManager);
        InitialTab();
    }

    private void InitialTab() {
        Intent it_one = new Intent(this, ActionActivity.class);
        String action = getResources().getString(R.string.module_activity_action);
        addTab(it_one, action);

        Intent it_two = new Intent(this, RouteActivity.class);
        String route = getResources().getString(R.string.module_activity_route);
        addTab(it_two, route);

        Intent it_three = new Intent(this, GroupActivity.class);
        String group = getResources().getString(R.string.module_activity_group);
        addTab(it_three, group);

        Intent it_four = new Intent(this, MineActivity.class);
        String mine = getResources().getString(R.string.module_activity_mine);
        addTab(it_four, mine);

        Intent it_five = new Intent(this, MoreActivity.class);
        String more = getResources().getString(R.string.module_activity_more);
        addTab(it_five, more);

        tabHost.setCurrentTab(0);
        widget = tabHost.getTabWidget();
    }

    private void addTab(Intent intent, String name) {
        TabHost.TabSpec tabSpec = tabHost.newTabSpec(name);
        tabSpec.setContent(intent);
        View view = getLayoutInflater().inflate(
                R.layout.maintab_textview, null);
        TextView textView =(TextView) view.findViewById(R.id.tab_Label);
        textView.setText(name);
        tabSpec.setIndicator(view);
        tabHost.addTab(tabSpec);
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
                return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
                onKeyDown(KeyEvent.KEYCODE_BACK, event);
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }
}
