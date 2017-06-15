package cn.appscomm.androiddemo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by zhaozx on 2017/6/14.
 * desc:
 */

public class NotifyBroadcast extends BroadcastReceiver {
    private String LOG = NotifyBroadcast.class.getSimpleName();
    public static final String QQ="com.tencent.mobileqq";
    public static final String WX="com.tencent.mm";
    public static final String MMS="com.android.mms";
    public static final String CALL="com.android.incallui";
    public static final String MY_APK="cn.appscomm.androiddemo";
    public static final String CLOUD_MUSIC="com.netease.cloudmusic";
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle=intent.getExtras();
        String pachageName=bundle.getString("packageName");
        switch (pachageName){
            case WX:
                Log.i(LOG, "微信来消息了");
                break;
            case QQ:
                Log.i(LOG, "QQ来消息了");
                break;
            case MMS:
                Log.i(LOG, "短信来消息了");
                break;
            case CALL:
                Log.i(LOG, "电话来消息了");
                break;
            case MY_APK:
                Log.i(LOG, "自己的推送消息");
                break;
            case CLOUD_MUSIC:
                Log.i(LOG, "云音乐推送消息");
                break;
        }
    }
}
