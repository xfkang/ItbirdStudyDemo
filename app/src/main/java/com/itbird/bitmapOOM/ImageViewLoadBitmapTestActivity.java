package com.itbird.bitmapOOM;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.itbird.R;

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