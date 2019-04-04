package www.wadedwyane.com.logincomponent.service;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import www.wadedwyane.com.componentlib.service.ILoginService;
import www.wadedwyane.com.logincomponent.activity.LoginActivity;
import www.wadedwyane.com.logincomponent.fragment.UserInfoFragment;

/**
 * @author kui.liu
 * @time 2019/3/26 15:41
 */
public class LoginService implements ILoginService{
    @Override
    public void launch(Context context, String targetClass) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public Fragment newUserInfoFragment(FragmentManager manager, int viewId, Bundle bundle) {
        UserInfoFragment fragment = new UserInfoFragment();
        fragment.setArguments(bundle);
        manager.beginTransaction().add(viewId, fragment).commit();
        return fragment;
    }
}
