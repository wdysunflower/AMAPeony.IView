package com.peony.iview.global;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wdynetposa on 14-8-8.
 */
public class ShowToastUtil {
    private static Object lock = new Object();

    private static Handler mHandler = new Handler();

    private static List<Toast> toasts = new ArrayList<Toast>();

    public static void showToast(Context context, int text){
        showToast(context, context.getString(text), -1, android.widget.Toast.LENGTH_SHORT);
    }

    public static void showToast(Context context, String text){
        showToast(context, text, -1, android.widget.Toast.LENGTH_SHORT);
    }

    public static void showToast(Context context, int text, int duration) {
        showToast(context, context.getString(text), -1, duration);
    }

    public static void showToast(Context context, String text, int duration) {
        showToast(context, text, -1, duration);
    }

    public static void showCenterToast(Context context, int text, int duration) {
        showToast(context, context.getString(text), Gravity.CENTER, duration);
    }

    public static void showCenterToast(Context context, String text, int duration) {
        showToast(context, text, Gravity.CENTER, duration);
    }

    public static void showToast(Context context, int text, int gravity, int duration) {
        showToast(context, context.getString(text), gravity, duration);
    }

    /**
     * @param context  上下文参数
     * @param text     show出的内容
     * @param duration 对应系统的消息时间设置
     */
    public static void showToast(Context context, String text, int gravity, int duration){
        if(toasts.size() == 0){
            //show
            Toast toast = new Toast(text,duration);
            toasts.add(toast);
            showToast(context, gravity, toast);
        }else{
            Toast toast = new Toast(text,duration);
            addToast(toast);
        }
    }

    private static void addToast(Toast to){
        synchronized (lock) {
            for (Toast toast : toasts) {
                if(to.isCompare(toast)){
                    //throw away
                    return;
                }
            }
            toasts.add(to);
        }
    }

    //内部调用show出toast
    private static void showToast(final Context context, final int gravity, final Toast toast){
        android.widget.Toast t = android.widget.Toast.makeText(context, toast.text, toast.duration);
        if (gravity != -1) {
            t.setGravity(gravity, 0, 0);
        }
        t.show();
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                synchronized (lock) {
                    toasts.remove(toast);
                    if(toasts.size() == 0 ){
                        //do nothing
                    }else{
                        //递归
                        showToast(context, gravity, toasts.get(0));
                    }
                }
            }
        }, toast.duration == android.widget.Toast.LENGTH_SHORT ? 2000 : 3500);

    }

    private static class Toast{

        String text;

        int duration;

        Toast(String text , int duration){
            this.text = text;
            this.duration = duration;
        }

        boolean isCompare(Toast toast){
            return this.text == toast.text;
        }
    }
}
