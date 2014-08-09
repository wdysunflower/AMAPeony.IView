package com.peony.iview.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.peony.iview.fragment.RegisterFragment;
import com.peony.iview.fragment.SignFragment;
import com.peony.iview.global.ShowToastUtil;

import static com.peony.iview.app.LoginActivity.LoginFragmentModes.*;

public class LoginActivity extends FragmentActivity {
    public static final String LOGIN_MODE = "LoginMode";
    private FragmentManager mFragManager;

    RegisterFragment mRegister;
    SignFragment mSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFragManager = getSupportFragmentManager();
        mRegister = new RegisterFragment();
        mSign = new SignFragment();

        LoginFragmentModes modes = (LoginFragmentModes) getIntent().getSerializableExtra(LOGIN_MODE);
        setMode(modes);
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

    // 登录
    public enum LoginFragmentModes {
        Register,
        Sign
    }

    public void setMode(LoginFragmentModes mode) {
        FragmentTransaction trans = mFragManager.beginTransaction();
        switch (mode) {
            case Sign:
                trans.replace(R.id.main_frame, mSign);
                this.setTitle(R.string.title_activity_login_sign);
                break;
            case Register:
                trans.replace(R.id.main_frame, mRegister);
                this.setTitle(R.string.title_activity_login_register);
                break;
        }
        trans.commit();
        //mTitleBar.setMode(mode);
    }
}



