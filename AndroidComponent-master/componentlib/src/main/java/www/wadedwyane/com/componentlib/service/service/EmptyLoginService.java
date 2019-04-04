package www.wadedwyane.com.componentlib.service.service;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import www.wadedwyane.com.componentlib.service.ILoginService;


/**
 * @author kui.liu
 * @time 2019/3/26 17:04
 */
public class EmptyLoginService implements ILoginService {
    @Override
    public void launch(Context context, String targetClass) {

    }

    @Override
    public Fragment newUserInfoFragment(FragmentManager manager, int viewId, Bundle bundle) {
        return null;
    }
}
