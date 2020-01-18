package retrofit.www.wadedwyane.com.bitmapcachedemo.util;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * -----------------------------------------------------------
 * Copyright (C) 2018-2019, by tempus , All rights reserved.
 * -----------------------------------------------------------
 * Author: kui.liu
 * Time: 2019/11/5 12:17
 * Description: 这是内存缓存的工具类
 * -----------------------------------------------------------
 */
public class MemoryCacheUtil {

    //Android加载图片时，解析每一个像素的信息，再把每一个像素信息保存在内存中
    // 图片大小 = 总像素 * 每个像素占用的大小
    // 单色图：每个像素占用1/8字节
    // 16色图：每个像素占用1/2字节
    // 256色图：每个像素占用1个字节
    // 24位图(RGB图)： 每个像素占用3个字节
    // 32位图(ARGB图)：每个像素占用4个字节,Android系统中ARGB格式解析的

    // 通过HashMap<String, Bitmap> 键值对的方式保存图片，key为地址，value为图片对象，
    // 但因是强引用对象，很容易造成内存溢出，可以尝试SoftReference，官方提出使用LruCache.
    // LruCache<String, Bitmap> least recentlly use 最少最近使用算法，会将内存控制在一定的大小范围内，
    // 超出最大值会自动回收，这个最大值由开发者自己来决定
    private static LruCache<String, Bitmap> lruCache;

    // 使用强引用
//    private HashMap<String, Bitmap> mHashMap = new HashMap<>();
    // 使用弱引用
//    private HashMap<String, SoftReference<Bitmap>> mSoftReferenceHashMap = new HashMap<>();

    public static MemoryCacheUtil instance;

    public static MemoryCacheUtil getInstance() {
        if (null == instance) {
            synchronized (MemoryCacheUtil.class) {
                if (null == instance) {
                    instance = new MemoryCacheUtil();
                }
            }
        }
        return instance;
    }

    public MemoryCacheUtil() {
        long maxCacheSize = Runtime.getRuntime().maxMemory() / 8;
        lruCache = new LruCache<String, Bitmap>((int)maxCacheSize) {

            @Override
            protected int sizeOf(String key, Bitmap value) {
                int byteCount = value.getByteCount();
                return byteCount;
            }
        };
    }

    /**
     * 从内存中获取图片对象
     * @param url
     * @return
     */
    public static Bitmap getBitmapFromMemory(String url) {
        // 使用强引用
//        Bitmap bitmap1 = mHashMap.get(url);
        // 使用弱引用
        /*SoftReference<Bitmap> softReference = mSoftReferenceHashMap.get(url);
        if (null != softReference) {
            Bitmap bitmap = softReference.get();
            return bitmap;
        }*/

        Bitmap bitmap = lruCache.get(url);
        return bitmap;
    }

    /**
     * 将图片对象放入内存中
     * @param url
     * @return
     */
    public void setBitmapToMemory(String url, Bitmap bitmap) {
        //使用强引用
//        mHashMap.put(url, bitmap);

        //使用弱引用
//        mSoftReferenceHashMap.put(url, new SoftReference<Bitmap>(bitmap));

        //使用LruCache
        lruCache.put(url, bitmap);
    }

}
