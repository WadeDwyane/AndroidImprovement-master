package retrofit.www.wadedwyane.com.bitmapcachedemo.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * -----------------------------------------------------------
 * Copyright (C) 2018-2019, by tempus , All rights reserved.
 * -----------------------------------------------------------
 * Author: kui.liu
 * Time: 2019/11/5 12:18
 * Description: 三级缓存之本地缓存的工具类
 * -----------------------------------------------------------
 */
public class DiskCacheUtil {

    private static final String CACHE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() +"/wernews";

    /**
     * 从本地获取图片
     * @param url
     * @return
     */
    public static Bitmap getBitmapFromLocal(String url) {

        try {
            String fileName = MD5Util.encode(url);
            File file = new File(CACHE_PATH, fileName);

            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将图片保存到本地
     * @param url
     * @param bitmap
     */
    public static void setBitmapToLocal(String url, Bitmap bitmap) {

        try {
            String fileName = MD5Util.encode(url);
            File file = new File(CACHE_PATH, fileName);

            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }

            //把图片保存到本地
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
