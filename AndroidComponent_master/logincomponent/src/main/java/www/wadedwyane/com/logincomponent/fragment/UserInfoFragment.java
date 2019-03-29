package www.wadedwyane.com.logincomponent.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.wadedwyane.com.logincomponent.R;

/**
 * @author kui.liu
 * @time 2019/3/26 16:44
 */
public class UserInfoFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_userinfo, null);
        return view;
    }
}
