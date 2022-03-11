package com.itbird.imageloader;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.itbird.R;
import com.itbird.design.chain.Client;

public class ImageLoaderTestActivity extends AppCompatActivity {
    // ImageView控件，用于显示下载的图片
    private ImageView mImageView;
    /**
     * 图片地址集合
     */
    private final String url[] = {
            "https://img-blog.csdn.net/20160903083245762",
            "https://img-blog.csdn.net/20160903083252184",
            "https://img-blog.csdn.net/20160903083257871",
            "https://img-blog.csdn.net/20160903083257871",
            "https://img-blog.csdn.net/20160903083311972",
            "https://img-blog.csdn.net/20160903083319668",
            "https://img-blog.csdn.net/20160903083326871"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageviewl_load_bitmap_test);
        mImageView = findViewById(R.id.imageview);
        ImageLoader.getInstance(this).setImageLCache(new MymageCache());
        for (int i = 0; i < url.length; i++) {
            ImageLoader.getInstance(this).setImageView(url[i], mImageView);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}