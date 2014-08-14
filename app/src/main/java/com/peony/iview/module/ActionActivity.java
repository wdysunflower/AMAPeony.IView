package com.peony.iview.module;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;

import com.peony.iview.app.R;
import com.peony.iview.define.ActionContentTypes;
import com.peony.iview.define.CityAreas;

public class ActionActivity extends FragmentActivity {

    private ArrayAdapter<CityAreas> mAreaAdpater;
    private ArrayAdapter<ActionContentTypes> mContentAdpater;

    private Spinner mAreaSpinner;
    private Spinner mContentSpinner;
    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_action);

        initialAreaSpinner();
        initialContentSpinner();
        initialSerachView();
    }

    private void initialAreaSpinner() {
        mAreaAdpater = new ArrayAdapter<CityAreas>(this, android.R.layout.simple_spinner_item, CityAreas.values());
        mAreaAdpater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        View header = findViewById(R.id.include_action_header);
        mAreaSpinner = (Spinner) header.findViewById(R.id.action_spinner_area);
        mAreaSpinner.setAdapter(mAreaAdpater);

        mAreaSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub

            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        /*下拉菜单弹出的内容选项触屏事件处理*/
        mAreaSpinner.setOnTouchListener(new Spinner.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                /* 将mySpinner 隐藏，不隐藏也可以，看自己爱好*/
                // v.setVisibility(View.INVISIBLE);
                return false;
            }
        });

        /*下拉菜单弹出的内容选项焦点改变事件处理*/
        mAreaSpinner.setOnFocusChangeListener(new Spinner.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                v.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initialContentSpinner() {
        mContentAdpater = new ArrayAdapter<ActionContentTypes>(this, android.R.layout.simple_spinner_item, ActionContentTypes.values());
        mContentAdpater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        View header = findViewById(R.id.include_action_header);
        mContentSpinner = (Spinner) header.findViewById(R.id.action_spinner_content);
        mContentSpinner.setAdapter(mContentAdpater);

        mContentSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub

            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        /*下拉菜单弹出的内容选项触屏事件处理*/
        mContentSpinner.setOnTouchListener(new Spinner.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                /* 将mySpinner 隐藏，不隐藏也可以，看自己爱好*/
                // v.setVisibility(View.INVISIBLE);
                return false;
            }
        });

        /*下拉菜单弹出的内容选项焦点改变事件处理*/
        mContentSpinner.setOnFocusChangeListener(new Spinner.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                v.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initialSerachView() {
        View header = findViewById(R.id.include_action_header);
        mSearchView = (SearchView) header.findViewById(R.id.action_search_view);
        mSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                mAreaSpinner.setVisibility(View.VISIBLE);
                mContentSpinner.setVisibility(View.VISIBLE);
                return false;
            }
        });

        mSearchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAreaSpinner.setVisibility(View.GONE);
                mContentSpinner.setVisibility(View.GONE);
            }
        });
    }
}
