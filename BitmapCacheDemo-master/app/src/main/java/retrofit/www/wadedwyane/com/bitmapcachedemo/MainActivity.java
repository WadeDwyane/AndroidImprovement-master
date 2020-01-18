package retrofit.www.wadedwyane.com.bitmapcachedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import retrofit.www.wadedwyane.com.bitmapcachedemo.util.BitmapUtil;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;
    private Button mBtnLoad;
    private Button mBtnAnimate;

    //    private String URL = "https://goss2.cfp.cn/creative/vcg/800/version23/VCG41495833187.jpg?x-oss-process=image/format,jpg/interlace,1";
    private String URL = "https://goss3.cfp.cn/creative/vcg/800/version23/VCG41534203250.jpg?x-oss-process=image/format,jpg/interlace,1";

    private static int REQUEST_STORAGE_CODE = 1;
    private String[] PERMISSION_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        verifyStoragePermission();

        mImageView = findViewById(R.id.imv);
        mBtnLoad = findViewById(R.id.btn_load_bitmap);

        mBtnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapUtil util = new BitmapUtil(MainActivity.this);
                util.display(URL, mImageView);
            }
        });

    }

    private void verifyStoragePermission() {
        int permission = ActivityCompat.checkSelfPermission(this, PERMISSION_STORAGE[0]);
        if (permission != PermissionChecker.PERMISSION_GRANTED) {
            //没有权限，需要申请权限，弹出对话框
            ActivityCompat.requestPermissions(this, PERMISSION_STORAGE, REQUEST_STORAGE_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults[0] == PermissionChecker.PERMISSION_GRANTED) {
            //申请权限成功
            Toast.makeText(this, "授权SD卡权限成功", Toast.LENGTH_SHORT).show();
        } else {
            //申请权限失败
            Toast.makeText(this, "授权SD卡权限失败，可能会影响应用的使用", Toast.LENGTH_SHORT).show();
        }
    }
}
