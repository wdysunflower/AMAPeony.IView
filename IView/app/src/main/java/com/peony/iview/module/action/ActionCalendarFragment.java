package com.peony.iview.module.action;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.peony.iview.app.R;

public class ActionCalendarFragment extends Fragment {
    private Activity parentActivity;

    private CalendarView mCalendar;

    public ActionCalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_action_calendar, container, false);
       // mCalendar = (CalendarView) view.findViewById(R.id.action_calendar_view);
        return view;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        parentActivity = activity;
    }

    @Override
    public void onDetach() {
        parentActivity = null;
        super.onDetach();
    }
}
