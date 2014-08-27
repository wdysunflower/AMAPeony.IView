package com.peony.iview.model;

import android.content.Context;
import android.widget.Toast;

import com.peony.iview.app.R;
import com.peony.iview.global.ShowToastUtil;
import com.peony.iview.global.OperateUtils;

/**
 * Created by wdynetposa on 14-8-8.
 */
public class LoginManager {

    public boolean Login(Context context,String username,String password)
    {
        if (!OperateUtils.isNetworkAvailable(context)) {
            ShowToastUtil.showCenterToast(context,
                    R.string.error_net_failed, Toast.LENGTH_SHORT);
        }
        return true;
    }

    public boolean Logout()
    {
        return true;
    }
}
