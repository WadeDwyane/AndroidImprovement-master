package www.wadedwyane.com.processlive.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * @author kui.liu
 * @time 2019/3/26 13:45
 */
public class RemoteService extends ForegroundService {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        bindService(new Intent(this, LocalService.class),
                serviceConnection,
                Service.BIND_IMPORTANT);
        return super.onStartCommand(intent, flags, startId);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //服务已经与自己服务断开了,就开启远程服务
            startService(new Intent(RemoteService.this, LocalService.class));
            bindService(new Intent(RemoteService.this, LocalService.class), serviceConnection, Service.BIND_IMPORTANT);
        }
    };
}
