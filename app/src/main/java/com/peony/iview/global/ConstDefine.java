package com.peony.iview.global;

import android.net.Uri;
import android.provider.MediaStore;

/**
 * Created by wdynetposa on 14-8-8.
 */
public class ConstDefine {
    public static final String PACKAGE_NAME = "com.peony.iview.app";

    public static final String IS_FIRST_SHOW = "IS_FIRST_SHOW";
    public static final String RUNTIME_VERSION = "RUNTIME_VERSION";

    public static final String USER_NAME = "USER_NAME";
    public static final String PASSWORD = "PASSWORD";

//NEW

    public static boolean DEBUG = true;
    // For SSO
    // 业务来源
    public static final String FROM = "mpc_ipcam_and";
    // 签名私钥
    public static final String SIGN_KEY = "b72q6r4s3";
    // des加密私钥
    public static final String CRYPT_KEY = "a5b3t8s4";
    // aes加密私钥
    public static final String AEC_KEY = "sfe023f_9fd&fwfl";

    // 微信APP_ID MD5：0dfa62adafadde2f74b51626edba2fc7
    public static final String WECHAT_APP_ID = "wx344ec4e81301be10";
    // sina微博APP_KEY
    public static final String WEIBO_APP_KEY = "1278629738";
    public static final String REDIRECT_URL = "http://jia.360.cn/wap/index.html";
    public static final String SCOPE = "email,direct_messages_read,direct_messages_write,"
            + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
            + "follow_app_official_microblog," + "invitation_write";

    public static final boolean USE_DEBUG_MIOP = false;

    // public static final String APP_SERVER_DOMAIN = "https://api.jia.360.cn";
    public static final String APP_SERVER_DOMAIN = "https://test1.jia.360.cn";
//    public static final String APP_SERVER_DOMAIN = "https://test2.jia.360.cn";

    public static final String CPU_INFO_MAX_FREQ_FILE_PATH = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq";

    // 屏幕锁住时间
    public static final long LOCK_SCREEN = 10000;

    // Actions
    public static final String ACTION_UPDATE_COMPLETE_FIRMWARE = "com.kindroid.d3.KINDROID_UPDATE_COMPLETE_FIRMWARE";
    public static final String ACTION_CAMERA_STATE_CHANGE = "com.kindroid.d3.KINDROID_CAMERA_STATE_CHANGE";
    public static final String ACTION_CAMERA_REFRESH_LIST = "com.kindroid.d3.KINDROID_CAMERA_REFRESH_LIST";
    // 登录失效广播
    public static final String BROADCAST_CREDENTIALS = "com.kindroid.d3.KINDROID_CREDENTIALS";
    // app重复登录广播
    public static final String BROADCAST_APP_AGAIN = "com.kindroid.d3.KINDROID_APP_AGAIN";
    public static final String ACTION_MESSAGE_HASNEW = "com.kindroid.d3.KINDROID_MESSAGE_HASNEW";
    public static final String UPDATE_FILENAME = "update.zip";
    public static final String UPDATE_ININAME = "update.ini";
    public static final String UPDATE_APKNAME = "360jia.apk";
    public static final Uri STORAGE_URI = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    public static final String IMAGE_MIME_TYPE = "image/jpeg";

    // Formal update urls
    public static final String UPDATAZIPURL;
    public static final String UPDATAMD5URL;

    static {
        if (APP_SERVER_DOMAIN.equals("https://api.jia.360.cn")) {
            UPDATAZIPURL = "http://msoftdl.360.cn/mobilesafe/360jiaM2/andr/update.zip";
            UPDATAMD5URL = "http://msoftdl.360.cn/mobilesafe/360jiaM2/andr/update.md5";
        } else {
            UPDATAZIPURL = "http://msoftdl.360.cn/mobilesafe/360jiaM2_test/andr/update.zip";
            UPDATAMD5URL = "http://msoftdl.360.cn/mobilesafe/360jiaM2_test/andr/update.md5";
        }
    }

    // Test update urls
    // public static final String
    // UPDATAZIPURL="http://auto.m.360.cn/mobilesafe/360jiaM2/andr/update.zip";
    // public static final String
    // UPDATAMD5URL="http://auto.m.360.cn/mobilesafe/360jiaM2/andr/update.md5";

    /**
     * httpclient相关配置
     */
    public static final String DEFAULT_CLIENT_VERSION = "com.kindroid.d3";
    public static final String CLIENT_VERSION_HEADER = "User-Agent";
    public static final int TIMEOUT = 30;
    public static final String REFRESH_LIST_TIME = "refresh_list_time";


    // web url
    public static final String FORUM_URL = "http://bbs.360safe.com/forum-2145-1.html";

    public static final String PURCHASE_URL = "http://bbs.360safe.com/forum-2145-1.html";

    public static final String FIND_PWD = "http://i.360.cn/findpwdwap";

    //push 判重文件名
    public static final String PUSH_SIGN_FILE_NAME = "pushSign";

    //防打扰模式key
    public static final String BOTHERMODE_KEY = "bother_mode_key";
}
