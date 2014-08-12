package com.peony.iview.app;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import com.peony.iview.fragment.RegisterFragment;
import com.peony.iview.fragment.SignFragment;

public class LoginActivity extends FragmentActivity {
    public enum LoginFragmentModes {
        Register,
        Sign
    }

    public enum LoginLocationTypes {
        Landing,
        Direct
    }

    public static final String LOGIN_MODE = "LoginMode";
    public static final String LOGIN_LOCATION = "LoginLocation";

    private LoginFragmentModes mCurrentMode;

    private FragmentManager mFragManager;
    private RegisterFragment mRegister;
    private SignFragment mSign;

    private MenuItem mChangeItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFragManager = getSupportFragmentManager();
        mRegister = new RegisterFragment();
        mSign = new SignFragment();

        // ActionBar
        ActionBar actionBar = this.getActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_gradient_bg));
        LoginLocationTypes type = (LoginLocationTypes) getIntent().getSerializableExtra(LOGIN_LOCATION);
        if (type == LoginLocationTypes.Landing) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        mChangeItem = menu.findItem(R.id.menu_change);

        LoginFragmentModes modes = (LoginFragmentModes) getIntent().getSerializableExtra(LOGIN_MODE);
        setMode(modes);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_change:
                ChangeMode();
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void ChangeMode() {
        if (mCurrentMode == LoginFragmentModes.Sign)
            setMode(LoginFragmentModes.Register);
        else setMode(LoginFragmentModes.Sign);
    }

    private void setMode(LoginFragmentModes mode) {
        FragmentTransaction trans = mFragManager.beginTransaction();
        switch (mode) {
            case Sign:
                trans.replace(R.id.main_frame, mSign);
                this.setTitle(R.string.title_activity_login_sign);
                if (mChangeItem != null)
                    mChangeItem.setTitle(R.string.title_activity_login_register);
                break;
            case Register:
                trans.replace(R.id.main_frame, mRegister);
                this.setTitle(R.string.title_activity_login_register);
                if (mChangeItem != null)
                    mChangeItem.setTitle(R.string.title_activity_login_sign);
                break;
        }
        trans.commit();
        mCurrentMode = mode;
    }
}



