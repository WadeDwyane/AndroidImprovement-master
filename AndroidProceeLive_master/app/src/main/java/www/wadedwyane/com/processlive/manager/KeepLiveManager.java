package www.wadedwyane.com.processlive.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.lang.ref.WeakReference;

import www.wadedwyane.com.processlive.activity.KeepLiveActivity;
import www.wadedwyane.com.processlive.receiver.KeepLiveReceiver;

/**
 * @author kui.liu
 * @time 2019/3/26 10:49
 * 开关屏的管理工具
 */
public class KeepLiveManager {

    public static final KeepLiveManager instance = new KeepLiveManager();
    private KeepLiveReceiver receiver;
//    通过弱引用拿到,进行广播开启的Activity
    private WeakReference<Activity> mKeepAct;

    public static KeepLiveManager getInstance() {
        return instance;
    }

    private KeepLiveManager() {
        receiver = new KeepLiveReceiver();
    }

    /**
     * 注册开屏 关屏幕的广播监听
     */
    public void registerReceiver(Context context) {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        context.registerReceiver(receiver, filter);
    }

    /**
     * 注销开屏关屏幕广播监听
     */
    public void unregisterReceiver(Context context) {
        if (null != receiver) {
            context.unregisterReceiver(receiver);
        }
    }

    /**
     * 开启一像素act
     */
    public void startKeepLive(Context context) {
        Intent intent = new Intent(context, KeepLiveActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 开屏时关闭一像素act
     */
    public void finishKeepLive() {
        if (null != mKeepAct) {
            Activity activity = mKeepAct.get();
            if(null != activity) {
                activity.finish();
            }
            mKeepAct = null;
        }
    }

    /**
     * 绑定一像素的act
     *
     * @param activity
     */
    public void setKeepLive(Activity activity){
        mKeepAct = new WeakReference<Activity>(activity);
    }

}
