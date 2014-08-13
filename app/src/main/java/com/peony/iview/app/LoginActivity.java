package com.peony.iview.app;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.peony.iview.fragment.RegisterFragment;
import com.peony.iview.fragment.SignFragment;

import java.lang.reflect.Field;

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
        setMenuItemFore();

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_login, menu);
        mChangeItem = menu.findItem(R.id.menu_change);
        LoginFragmentModes modes = (LoginFragmentModes) getIntent().getSerializableExtra(LOGIN_MODE);
        setMode(modes);
        return super.onCreateOptionsMenu(menu);
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

    private void setMenuItemFore() {
        try {
            LayoutInflater layoutInflater = getLayoutInflater();
            Field field = LayoutInflater.class.getDeclaredField("mFactorySet");
            field.setAccessible(true);
            field.setBoolean(layoutInflater, false);

            getLayoutInflater().setFactory(new LayoutInflater.Factory() {
                @Override
                public View onCreateView(String name, Context context,
                                         AttributeSet attrs) {
                    System.out.println(name);
                    if (name.equalsIgnoreCase("com.android.internal.view.menu.IconMenuItemView")
                            || name.equalsIgnoreCase("com.android.internal.view.menu.ActionMenuItemView")) {
                        try {
                            final View view;
                            LayoutInflater f = getLayoutInflater();
                            view = f.createView(name, null, attrs);
                            System.out.println((view instanceof TextView));
                            if (view instanceof TextView) {
                                ((TextView) view).setTextColor(R.color.rainbow_2);
                            }
                            return view;
                        } catch (InflateException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    return null;
                }
            });
        } catch (Exception ex) {
            Log.d("SetMenuItemFore", ex.getMessage());
        }
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



