package www.wadedwyane.com.logincomponent.application;

import android.app.Application;

import www.wadedwyane.com.componentlib.service.IComponentApp;
import www.wadedwyane.com.componentlib.service.factory.ServiceFactory;
import www.wadedwyane.com.logincomponent.service.LoginService;


/**
 * @author kui.liu
 * @time 2019/3/26 15:52
 */
public class LoginApplication extends Application implements IComponentApp{

    private static Application application;

    public static Application getApplication() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //当LoginApplications是个独立的app时,调用这个
        initializal(this);
    }

    @Override
    public void initializal(Application app) {
        application = app;
        ServiceFactory.getInstance().setmLoginService(new LoginService());
    }
}
