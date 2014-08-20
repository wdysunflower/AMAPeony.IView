package com.peony.iview.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.peony.iview.custom.GL2PlayerView;

public class VideoActivity extends Activity {
    private int mVideoSurfaceWidth;
    private int mVideoSurfaceHeight;
    private float mWHRatio = 16.f / 9.f;

    private GL2PlayerView mPlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        initVideoView();
    }

    private void initVideoView() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        mVideoSurfaceWidth = screenWidth;
        mVideoSurfaceHeight = (int) (screenWidth / mWHRatio);
        if (mVideoSurfaceHeight > screenHeight) {
            mVideoSurfaceHeight = screenHeight;
            mVideoSurfaceWidth = (int) (screenHeight * mWHRatio);
        }
        mPlayerView = (GL2PlayerView) findViewById(R.id.video_view);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                mVideoSurfaceWidth, mVideoSurfaceHeight);
        mPlayerView.setLayoutParams(lp);
    }
}
