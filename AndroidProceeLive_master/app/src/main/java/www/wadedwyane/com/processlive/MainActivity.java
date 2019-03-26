 package www.wadedwyane.com.processlive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import www.wadedwyane.com.processlive.manager.KeepLiveManager;
import www.wadedwyane.com.processlive.service.LocalService;
import www.wadedwyane.com.processlive.service.RemoteService;

 public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //方法一和方法二不要同时用,性能会影响用户
        //方法一:通过一像素activity进行提权
//        KeepLiveManager.getInstance().registerReceiver(this);
        //方法二:前台服务
//        startService(new Intent(this, ForegroundService.class));

        //进程拉活:
        //一:双进程守护
        //启动本地服务
        startService(new Intent(this, LocalService.class));
        //启动远程服务
        startService(new Intent(this, RemoteService.class));
    }
     
     @Override
     protected void onDestroy() {
         super.onDestroy();
         KeepLiveManager.getInstance().unregisterReceiver(this);
     }
 }
