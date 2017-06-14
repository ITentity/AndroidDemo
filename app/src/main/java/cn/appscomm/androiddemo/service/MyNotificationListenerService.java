package cn.appscomm.androiddemo.service;

import android.app.Notification;
import android.content.Intent;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by zhaozx on 2017/6/14.
 * desc:
 */

public class MyNotificationListenerService extends NotificationListenerService {
    private String LOG = MyNotificationListenerService.class.getSimpleName();
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        Notification n = sbn.getNotification();
        // 标题和时间
        String title = "";
        if (n.tickerText != null) {
            title = n.tickerText.toString();
        }
        long when = n.when;
        Log.e(LOG,title+"-"+when+"-"+n.getClass());
        // Toast.makeText(this, title+when, Toast.LENGTH_LONG).show();
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
