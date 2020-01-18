package eventbus.www.wadedwyane.myselfeventbus;

import androidx.appcompat.app.AppCompatActivity;
import eventbus.www.wadedwyane.eventbus.subscriber.Subscribe;
import eventbus.www.wadedwyane.eventbus.mode.ThreadMode;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void event(UserInfo info) {
        Toast.makeText(this, "name = " + info.name + ", age = " + info.age, Toast.LENGTH_SHORT).show();
    }

}
