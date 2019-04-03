package www.wadedwyane.com.componentlib.service;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * @author kui.liu
 * @time 2019/3/26 15:36
 */
public interface ILoginService {

    void launch(Context context, String targetClass);

    Fragment newUserInfoFragment(FragmentManager manager, int viewId, Bundle bundle);
}
