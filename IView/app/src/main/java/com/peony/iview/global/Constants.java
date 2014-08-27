package com.peony.iview.global;

/**
 * Created by wdynetposa on 14-8-15.
 */
/**
 *
 * 系统常量
 *
 */
public final class Constants {
    /*
     * 固件常量
     */
    public static final class FirmWare {
        public static final int STATE_NONE_UPDATE = 0;// 没有更新
        public static final int STATE_HAVE_UPDATE = 1;// 有更新
        public static final int STATE_FORCE_UPDATE = 2;// 正在升级
    }

    /*
     * 固件升级常量
     */
    public static final class upgrade {
        public static final int FIRMWARE_UPGRADE_SUCCEED = 0; // 固件升级成功
        public static final int FIRMWARE_MD5CHECK_FAILURE = -40001; // 下载固件校验失败
        public static final int FIRMWARE_DOWNLOAD_FAILURE = -40002;// 网络异常,下载失败
        public static final int FIRMWARE_INSTALL_FAILURE = -40003;// 固件安装过程失败
        public static final int FIRMWARE_UNKNOWN_ERROR = -1;// 未知错误
    }

    /*
     * 推送消息常量
     */
    public static final class Push {
        public static final int TYPE_ONLINE = 1;// 上线报警
        public static final int TYPE_OFFLINE = 2;// 离线警报
        public static final int TYPE_MOTION = 3;// 动作警报
        public static final int TYPE_SOUND = 4;// 声音报警
        public static final int TYPE_WEB_LOGIN = 6; //web页面登录了
        public static final int TYPE_APP_LOGIN = 7; // 下线通知
        public static final int TYPE_SESSION_OVERDUE = 8; // session 过期
        public static final int TYPR_CLOUD_SAVE_PAST_DUE = 9; //云存储服务到期通知
        public static final int TYPE_FIRMWARE = 10;// 固件升级
    }

    /*
     * HTTP常量
     */
    public static final class Http {
        public static final int ERROR_CODE_SSL_HANDS = -8;// SSL握手异常
        public static final int ERROR_CODE_SSL = -7;// SSL通道建立失败
        public static final int ERROR_UNKNOWN = -6;// 未定义
        public static final int ERROR_CODE_DES = -5;// 加解密失败
        public static final int ERROR_CODE_IO = -4;// IO异常
        public static final int ERROR_CODE_ENCODING = -3;// URL参数编码失败
        public static final int ERROR_CODE_JSON = -2;// Json解析异常
        public static final int ERROR_CODE_SUCCEED = 0;// 成功
        public static final int ERROR_DEV_IS_BIND = 409;// 摄像头已经被其他人绑定
        public static final int ERROR_DEV_ADD = 412;// 添加摄像头失败
        public static final int ERROR_DEV_ADD_SET = 413;// 添加摄像头基本属性失败
        public static final int ERROR_DEV_ADD_FW = 414;// 添加固件版本失败
        public static final int ERROR_DEV_INIT = 415; // 初始化设置信息失败
        public static final int ERROR_DEV_BIND = 416;// 建立绑定关系失败
        public static final int ERROR_REQUEST = 417; // 错误请求
        public static final int ERROR_DEV_GET_SET = 418;// 获取摄像头信息失败
        public static final int ERROR_LOGIN = 419;// 登录失败
        public static final int ERROR_REMOVE_BIND = 420;// 解除绑定失败
        public static final int ERROR_UNPERMISSION = 421;// 无权操作
        public static final int ERROR_GET_EVENTS = 422;// 获取事件列表失败
        public static final int ERROR_ADD_EVENT = 423;// 添加事件出错
        public static final int ERROR_GET_EVENT_DATA = 424; // 获取事件数据出错
        public static final int ERROR_WRITE_FEEDBACK = 425;// 用户反馈写入失败
        public static final int ERROR_DEV_UPDATE = 426;// 设备信息更新失败
        public static final int ERROR_FIRMWARE_UPDATE = 427;// 修改升级状态失败
        public static final int ERROR_GET_RELAY = 428;// 获取Relay失败
        public static final int ERROR_GET_FIRMWARE_UPGRADE = 429;// 获取固件升级信息失败
        public static final int ERROR_GET_DEV = 430;// 获取设备信息失败
        public static final int ERROR_GET_DEV_SET = 431;// 获取设备设置信息失败
        public static final int ERROR_UPDATE_SET = 432;// 更新设备设置信息失败
        public static final int ERROR_SET_DEV = 433;// 设置设备失败
        public static final int ERROR_GET_FIRMWARE = 434;// 获取固件信息失败
        public static final int ERROR_CAM_NOT_EXIST = 435;// 摄像头不存在
        public static final int ERROR_USER_NOT_EXIST = 436; // 用户不存在
        public static final int ERROR_CONNECT_SERVER = 437;// 连接服务器失败
        public static final int ERROR_SEND_COMMAND = 438; // 发送命令失败
        public static final int ERROR_GET_MASTER = 439; // 获取master信息失败
        public static final int ERROR_VALIDATION_SIGNATURE = 440; // 验证signature失败
        public static final int ERROR_CAM_OFFLINE = 441; // 摄像头离线了
        public static final int ERROR_CODE_SIDOVERDUE = 442; // sid过期
        public static final int ERROR_GET_THUMBNAIL = 443;// 获取缩略图失败
        public static final int ERROR_PUBCAM_CANCEL = 444;// 取消公共摄像头失败
        public static final int ERROR_CAM_SHARE = 445;// 共享摄像头失败
        public static final int ERROR_GET_PUBCAM = 446;// 获取公共摄像头列表失败
        public static final int ERROR_PUBCAM_ADD_TYPE = 447;// 添加公共摄像头分类失败
        public static final int ERROR_GET_PUBCAM_TYPE = 448;// 获取公共摄像头分类失败
        public static final int ERROR_CAM_REMOVE = 449;// 该摄像头被移除
        public static final int ERROR_GET_CAM = 450;// 获取我的公共摄像头列表失败
        public static final int ERROR_CAM_MODIFY_STATE= 451;// 修改摄像头状态失败
        public static final int ERROR_CODE_UNAUTHORIZED = 452; // 认证失败
    }

    /*
     * 任务运行状态
     */
    public static final class TaskState {
        public static final int SUCCESS = 0x1111;// 任务成功
        public static final int FAILURE = 0x1112;// 任务失败
        public static final int ISRUNING = 0x1113;// 任务正在运行
        public static final int PAUSE = 0x1114;// 任务暂停
        public static final int EXCEPITON = 0x1115;// 任务异常
    }

    public static final class SSL {
        public static final String CLIENT_AGREEMENT = "TLS"; // 使用协议
        public static final String CLIENT_KEY_MANAGER = "X509"; // 密钥管理器
        public static final String CLIENT_TRUST_MANAGER = "X509"; // 信任证书管理器
        public static final String CLIENT_KEY_KEYSTORE = "BKS"; // "JKS";//密库，这里用的是BouncyCastle密库
        public static final String CLIENT_TRUST_KEYSTORE = "BKS"; // "JKS";//
    }

    /*
     * 通知栏ID
     */
    public static final class Notify{
        public static final int NORMAL = 0x2222;
        public static final int APP_UPDATE = 0x2223;
        public static final int FIRMWARE_UPDATE = 0x2224;
    }
}
