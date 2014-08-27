package com.peony.iview.global;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by wdynetposa on 14-8-8.
 */

public class OperateUtils {

    public static boolean isGingerbreadOrLater() {
        return android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.GINGERBREAD;
    }

    public static boolean isHCOrLater() {
        return android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB;
    }

    public static String inputStreamToString(InputStream is) {
        String response = "";
        try {
            byte buffer[] = new byte[1024];
            int n = 0;
            while ((n = is.read(buffer)) != -1) {
                response += new String(buffer, 0, n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static String decodeBase64(String text) {
        try {
            return new String(Base64.decode(text.getBytes(), Base64.DEFAULT),
                    "utf-8");
        } catch (UnsupportedEncodingException e) {
        }
        return text;
    }

    public static String encodeBase64(String text) {
        return Base64.encodeToString(text.getBytes(), Base64.NO_WRAP
                | Base64.URL_SAFE);
    }

    public static String getFormatDate(long timeMillis) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(timeMillis);
        Date d = c.getTime();
        String date = SimpleDateFormat.getDateInstance().format(d);
        String time = SimpleDateFormat.getTimeInstance().format(d);
        return date + time;
    }

    public static JSONObject getPreviewParams(String fileName)
            throws JSONException {
        String[] Params = fileName.split("-");
        JSONObject JParams = new JSONObject();
        if (Params.length >= 3) {
            JParams.put("sn", Params[0]);
            JParams.put("time", Params[1]);
            JParams.put("type", Params[2]);
        }
        return JParams;
    }

    public static JSONObject getThumbParams(String fileName)
            throws JSONException {
        JSONObject JParams = new JSONObject();
        JParams.put("sn", fileName);
        return JParams;
    }

    public static int convertDpToPixel(Context context, float dp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return (int) px;
    }

    public static String getBody(String[] pairKeys, String... params) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < pairKeys.length; i++)
            sb.append(pairKeys[i] + "=" + params[i] + "&");
        return sb.toString();
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static boolean isMobileNetwork(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
        }
        return false;
    }

    public static String getIMEI(Context context) {
        TelephonyManager telManage = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return telManage.getDeviceId();
    }

    public static int getMaxCpuFreq() {
        int result = 0;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(ConstDefine.CPU_INFO_MAX_FREQ_FILE_PATH);
            br = new BufferedReader(fr);
            String text = br.readLine();
            result = Integer.parseInt(text.trim());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fr != null)
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (br != null)
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        return result;
    }

    public static boolean IsRoot() {
        Process process = null;
        DataOutputStream os = null;
        try {
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
        } catch (Exception e) {
            Log.d("*** DEBUG ***", "Unexpected error - Here is what I know: "
                    + e.getMessage());
            return false;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                process.destroy();
            } catch (Exception e) {
                // nothing
            }
        }
        return true;
    }

    // 将127.0.0.1形式的IP地址转换成十进制整数
    public static long ipToLong(String strIp) {
        try {
            String[] arr = strIp.split("\\.");
            long[] ip = new long[arr.length];
            for (int i = 0; i < arr.length; i++) {
                ip[i] = Long.parseLong(arr[i]);
            }
            return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1L;
    }

    public static long ipToLongR(String strIp) {
        try {
            String[] arr = strIp.split("\\.");
            long[] ip = new long[arr.length];
            for (int i = 0; i < arr.length; i++) {
                ip[i] = Long.parseLong(arr[i]);
            }
            return (ip[3] << 24) + (ip[2] << 16) + (ip[1] << 8) + ip[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1L;
    }

    public static String longToIP(long longIp) {
        StringBuffer sb = new StringBuffer("");
        sb.append(String.valueOf((longIp >>> 24)));
        sb.append(".");
        sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));
        sb.append(".");
        sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));
        sb.append(".");
        sb.append(String.valueOf((longIp & 0x000000FF)));
        return sb.toString();
    }
}
