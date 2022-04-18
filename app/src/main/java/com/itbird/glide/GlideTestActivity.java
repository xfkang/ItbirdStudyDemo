package com.itbird.glide;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.itbird.R;
import com.itbird.imageloader.ImageLoader;
import com.itbird.imageloader.MymageCache;

public class GlideTestActivity extends AppCompatActivity {

    private static final String TAG = GlideTestActivity.class.getSimpleName();

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
        setContentView(R.layout.glide_test);
        mImageView = findViewById(R.id.imageview);
        for (int i = 0; i < url.length; i++) {
            Glide.with(this).load(url[i]).into(mImageView);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}