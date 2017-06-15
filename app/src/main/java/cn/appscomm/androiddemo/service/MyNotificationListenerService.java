package cn.appscomm.androiddemo.service;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

/**
 * Created by zhaozx on 2017/6/14.
 * desc:
 */

public class MyNotificationListenerService extends NotificationListenerService {
    private String LOG = MyNotificationListenerService.class.getSimpleName();
    public static final String SEND_BROADCAST="SEND_BROADCAST";
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        Log.e("AAA", "=2==onNotificationPosted   ID :"
                + sbn.getId() + "\t"
                + sbn.getNotification().tickerText + "\t"
                + sbn.getNotification().toString() + "\t"
                + sbn.toString() + "\t"
                + sbn.getPackageName());
        Notification n = sbn.getNotification();
        String title = "";
        if (n.tickerText != null) {
            title = n.tickerText.toString();
        }
        long when = n.when;
        String packageName=sbn.getPackageName();
        Intent intent=new Intent();
        intent.setAction(SEND_BROADCAST);
        Bundle bundle=new Bundle();
        bundle.putString("packageName",packageName);
        bundle.putString("title",title);
        bundle.putString("when",when+"");
        intent.putExtras(bundle);
        this.sendBroadcast(intent);
//        // 标题和时间
//        String title = "";
//        if (n.tickerText != null) {
//            title = n.tickerText.toString();
//        }
//        long when = n.when;
//        Log.e(LOG,title+"-"+when+"-"+sbn.getPackageName());
//        // Toast.makeText(this, title+when, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
        Log.e(LOG,"通知被删除");
        // Toast.makeText(this, "通知被删除", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
