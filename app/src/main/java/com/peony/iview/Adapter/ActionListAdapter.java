package com.peony.iview.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.peony.iview.app.R;
import com.peony.iview.model.MActionData;

import java.util.ArrayList;

/**
 * Created by wdynetposa on 14-8-15.
 */
public class ActionListAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<MActionData> mActionDataList;

    public ActionListAdapter(Context context) {
        mContext = context;
        initialData();
    }

    public void initialData() {
        mActionDataList = new ArrayList<MActionData>();
        for (int i = 0; i < 10; i++) {
            MActionData actionData = new MActionData();
            actionData.Name = "活动" + (i + 1);
            actionData.Image = R.drawable.image_default;
            mActionDataList.add(actionData);
        }
    }

    @Override
    public int getCount() {
        return mActionDataList.size();
    }

    @Override
    public MActionData getItem(int i) {
        if (i < getCount())
            return mActionDataList.get(i);

        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.item_action_list, null);
            holder.v_Image = (ImageView) convertView
                    .findViewById(R.id.action_list_item_image);
            holder.v_Name = (TextView) convertView
                    .findViewById(R.id.action_list_item_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        adaptiveView(holder, i);
        return convertView;
    }

    private void adaptiveView(final ViewHolder holder, int position) {
        MActionData actionData = getItem(position);
        holder.v_Image.setImageResource(actionData.Image);
        holder.v_Name.setText(actionData.Name);
    }

    class ViewHolder {
        ImageView v_Image;
        TextView v_Name;
    }
}
