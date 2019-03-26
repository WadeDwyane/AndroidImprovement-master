package www.wadedwyane.com.androidcomponent_master.application;

import android.app.Application;

import www.wadedwyane.com.componentlib.service.AppConfig;
import www.wadedwyane.com.componentlib.service.IComponentApp;


/**
 * @author kui.liu
 * @time 2019/3/26 15:54
 */
public class MyApplication extends Application implements IComponentApp{

    private static Application application;

    public static Application getApplication() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initializal(this);
    }

    @Override
    public void initializal(Application application) {
        for(String component : AppConfig.components) {
            try {
                //通过配置文件中的Application的路径,实例化Application,并将主Application传递进去

                Class<?> clazz = Class.forName(component);
                Object object = clazz.newInstance();
                if (object instanceof IComponentApp) {
                    ((IComponentApp)object).initializal(this);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
