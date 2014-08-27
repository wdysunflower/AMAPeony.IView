package com.peony.iview.define;

/**
 * Created by wdynetposa on 14-8-15.
 */
public class GL2JNILibTypes {
    static {
        System.loadLibrary("ffmpeg");
        System.loadLibrary("jplayer");
    }

    /**
     * @param width the current view width
     * @param height the current view height
     */
    public static native void init(int width, int height);
    public static native void step();
}
