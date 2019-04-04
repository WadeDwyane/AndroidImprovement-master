package www.wadedwyane.com.componentlib.service.factory;

import www.wadedwyane.com.componentlib.service.ILoginService;
import www.wadedwyane.com.componentlib.service.IMineService;
import www.wadedwyane.com.componentlib.service.service.EmptyLoginService;
import www.wadedwyane.com.componentlib.service.service.EmptyMineService;

/**
 * @author kui.liu
 * @time 2019/3/26 15:45
 */
public class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    public static ServiceFactory getInstance() {
        return instance;
    }

    private ServiceFactory() {}

    private ILoginService mLoginService;
    private IMineService mMineService;

    public ILoginService getmLoginService() {
        if (null == mLoginService) {
            mLoginService = new EmptyLoginService();
        }
        return mLoginService;
    }

    //set時候,需要判斷
    public void setmLoginService(ILoginService mLoginService) {
        this.mLoginService = mLoginService;
    }

    public IMineService getmMineServicel() {
        if (null == mMineService) {
            mMineService = new EmptyMineService();
        }
        return mMineService;
    }

    public void setmMineServicel(IMineService mMineServicel) {
        this.mMineService = mMineServicel;
    }
}
