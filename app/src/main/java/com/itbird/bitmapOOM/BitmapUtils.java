package com.itbird.bitmapOOM;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.widget.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by itbird on 2022/2/1
 */
public class BitmapUtils {

    /**
     * 以固定的压缩比例加载大图片
     *
     * @param path
     * @param opt
     * @param is
     * @return
     */
    public static Bitmap decodeFixedSampleBitmapFromFile(String path, BitmapFactory.Options opt, InputStream is) {
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        opt.inSampleSize = 2;//二分之一缩放，可写1即100%显示
        //获取资源图片
        try {
            is = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return BitmapFactory.decodeStream(is, null, opt);
    }

    /**
     * 以计算的压缩比例加载大图片
     *
     * @param res
     * @param resId
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static Bitmap decodeCalSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        // 检查bitmap的大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        // 设置为true，BitmapFactory会解析图片的原始宽高信息，并不会加载图片
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        BitmapFactory.decodeResource(res, resId, options);

        // 计算采样率
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // 设置为false，加载bitmap
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeResource(res, resId, options);
    }

    /*********************************
     * @function: 计算出合适的图片倍率
     * @options: 图片bitmapFactory选项
     * @reqWidth: 需要的图片宽
     * @reqHeight: 需要的图片长
     * @return: 成功返回倍率, 异常-1
     ********************************/
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth,
                                             int reqHeight) {
        // 设置初始压缩率为1
        int inSampleSize = 1;
        try {
            // 获取原图片长宽
            int width = options.outWidth;
            int height = options.outHeight;
            // reqWidth/width,reqHeight/height两者中最大值作为压缩比
            int w_size = width / reqWidth;
            int h_size = height / reqHeight;
            inSampleSize = w_size > h_size ? w_size : h_size;  // 取w_size和h_size两者中最大值作为压缩比
            Log.e("inSampleSize", String.valueOf(inSampleSize));
        } catch (Exception e) {
            return -1;
        }
        return inSampleSize;
    }

    /**
     * ImageView回收bitmap
     *
     * @param iv
     */
    private static void recycleBitmap(ImageView iv) {
        if (iv != null && iv.getDrawable() != null) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) iv.getDrawable();
            iv.setImageDrawable(null);
            if (bitmapDrawable != null) {
                Bitmap bitmap = bitmapDrawable.getBitmap();
                if (bitmap != null) {
                    bitmap.recycle();
                }
            }
        }
    }

    /**
     * 在Activity或Fragment的onDestory方法中进行回收(必须确保bitmap不在使用)
     *
     * @param bitmap
     */
    public static void recycleBitmap(Bitmap bitmap) {
        // 先判断是否已经回收
        if (bitmap != null && !bitmap.isRecycled()) {
            // 回收并且置为null
            bitmap.recycle();
            bitmap = null;
        }
    }
}
