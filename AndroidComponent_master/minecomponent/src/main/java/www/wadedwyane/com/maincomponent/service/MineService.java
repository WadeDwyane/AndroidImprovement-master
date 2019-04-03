package www.wadedwyane.com.maincomponent.service;

import android.content.Context;
import android.content.Intent;

import www.wadedwyane.com.componentlib.service.IMineService;
import www.wadedwyane.com.maincomponent.activity.MineActivity;

/**
 * @author kui.liu
 * @time 2019/3/26 15:42
 */
public class MineService implements IMineService{
    @Override
    public void launch(Context context, int userId) {
        Intent intent = new Intent(context, MineActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
