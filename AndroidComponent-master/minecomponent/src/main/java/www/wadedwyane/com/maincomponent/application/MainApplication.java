package www.wadedwyane.com.maincomponent.application;

import android.app.Application;

import www.wadedwyane.com.componentlib.service.IComponentApp;
import www.wadedwyane.com.componentlib.service.factory.ServiceFactory;
import www.wadedwyane.com.maincomponent.service.MineService;


/**
 * @author kui.liu
 * @time 2019/3/26 15:52
 */
public class MainApplication extends Application implements IComponentApp{

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
    public void initializal(Application app) {
        application = app;
        ServiceFactory.getInstance().setmMineServicel(new MineService());
    }
}
