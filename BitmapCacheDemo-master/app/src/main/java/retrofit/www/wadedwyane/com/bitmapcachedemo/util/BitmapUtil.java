package retrofit.www.wadedwyane.com.bitmapcachedemo.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.Toast;

public class BitmapUtil {

    private NetCacheUtil mNetCacheUtil;
    private DiskCacheUtil mDiskCacheUtil;
    private MemoryCacheUtil mMemoryCacheUtil;
    private Context mContext;


    public BitmapUtil(Context context) {
        mNetCacheUtil = new NetCacheUtil(context);
        mDiskCacheUtil = new DiskCacheUtil();
        mMemoryCacheUtil = MemoryCacheUtil.getInstance();
        mContext = context;
    }

    public void display(String url, ImageView imageView) {

        Bitmap bitmap;
        //先从内存加载图片
        bitmap = mMemoryCacheUtil.getBitmapFromMemory(url);
        if (null != bitmap) {
            imageView.setImageBitmap(bitmap);
            Toast.makeText(mContext, "从内存加载图片成功", Toast.LENGTH_SHORT).show();
            return;
        }

        //再从本地磁盘加载图片
        bitmap = mDiskCacheUtil.getBitmapFromLocal(url);
        if (null != bitmap) {
            imageView.setImageBitmap(bitmap);
            Toast.makeText(mContext, "从本地加载图片成功", Toast.LENGTH_SHORT).show();
            return;
        }

        mNetCacheUtil.getBitmapFromNet(url, imageView);
    }
}
