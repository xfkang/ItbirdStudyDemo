package com.itbird.bitmapOOM;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.itbird.R;

/**
 * Android 位图（图片）加载引入的内存溢出问题分析
 * Created by itbird on 2022/2/1
 */
public class ImageViewLoadBitmapTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageviewl_load_bitmap_test);
        ImageView imageView = findViewById(R.id.imageview);
        imageView.setImageResource(R.drawable.bigpic);
        imageView.setBackgroundResource(R.drawable.bigpic);
        imageView.setImageBitmap(BitmapFactory.decodeFile("path/big.jpg"));
        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.bigpic));
    }
}