package www.wadedwyane.com.processlive.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import www.wadedwyane.com.processlive.manager.KeepLiveManager;


/**
 * @author kui.liu
 * @time 2019/3/26 10:45
 */
public class KeepLiveReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        if (TextUtils.equals(action, Intent.ACTION_SCREEN_OFF)) {
        //设置关屏,显示一像素
            KeepLiveManager.getInstance().startKeepLive(context);
        }else if (TextUtils.equals(action, Intent.ACTION_SCREEN_ON)){
            //开屏
            KeepLiveManager.getInstance().finishKeepLive();
        }
    }
}
