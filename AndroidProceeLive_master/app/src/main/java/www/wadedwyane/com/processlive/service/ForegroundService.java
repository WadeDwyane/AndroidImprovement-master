package www.wadedwyane.com.processlive.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;


/**
 * @author kui.liu
 * @time 2019/3/26 11:16
 */
public class ForegroundService extends Service {
    private static final int SERVICE_ID = 1;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new LocalBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //版本兼容
        if (Build.VERSION.SDK_INT < 18) {
            //小于4.3
            startForeground(SERVICE_ID, new Notification());
        }else if (Build.VERSION.SDK_INT < 26) {
            //小於7.0
            startForeground(SERVICE_ID, new Notification());
            //让用户无感知,删除通知栏消息
            startService(new Intent(this, InnerService.class));
        } else  {
            //设置channel
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            //越小,通知重要性越低
            NotificationChannel channel =
                    new NotificationChannel("channel", "processlive", NotificationManager.IMPORTANCE_MIN);
            if (null != manager) {
                manager.createNotificationChannel(channel);
                Notification notification = new NotificationCompat.Builder(this, "channel").build();
                startForeground(SERVICE_ID, notification);
            }
        }
        //开启前台进程
        return super.onStartCommand(intent, flags, startId);
    }

    public static class InnerService  extends Service {

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            //ID一样
            startForeground(SERVICE_ID, new Notification());
            //就能让通知不显示
            stopForeground(true);
            stopSelf();
            return super.onStartCommand(intent, flags, startId);
        }
    }

    private class LocalBinder  extends Binder {

    }
}
