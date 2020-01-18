package retrofit.www.wadedwyane.com.bitmapcachedemo.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetCacheUtil {

    private Context mContext;

    public NetCacheUtil(Context context) {
        mContext = context;
    }

    public void getBitmapFromNet(String url, ImageView imageView) {
        new DownloadBitmapTask().execute(imageView, url);
    }

    class DownloadBitmapTask extends AsyncTask<Object, Void, Bitmap> {

        private ImageView mImageView;
        private String url;

        @Override
        protected Bitmap doInBackground(Object... objects) {
            mImageView = (ImageView) objects[0];
            url = (String) objects[1];
            return downloadBitmap(url);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (null != bitmap) {
                mImageView.setImageBitmap(bitmap);
                Toast.makeText(mContext, "从网络加载图片成功!", Toast.LENGTH_SHORT).show();

                // 把图片存到内存里面
                MemoryCacheUtil.getInstance().setBitmapToMemory(url, bitmap);

                // 把图片存到本地磁盘里面
                DiskCacheUtil.setBitmapToLocal(url, bitmap);
            }
        }
    }

    public Bitmap downloadBitmap(String url) {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                // 通过BitmapFactory
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream(), null, options);
                return bitmap;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return null;
    }
}
