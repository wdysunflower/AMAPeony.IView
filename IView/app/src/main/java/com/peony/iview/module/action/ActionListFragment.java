package com.peony.iview.module.action;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.peony.iview.Adapter.ActionListAdapter;
import com.peony.iview.app.R;

public class ActionListFragment extends Fragment {
    private Activity parentActivity;

    private ListView mListView;

    public ActionListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_action_list, container, false);
        mListView = (ListView) view.findViewById(R.id.action_list_view);
        initialListView();
        return view;
    }

    private void initialListView() {
        mListView.setAdapter(new ActionListAdapter(parentActivity));
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
