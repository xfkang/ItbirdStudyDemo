package com.itbird.design.chain;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.itbird.R;
import com.itbird.design.chain.upgrade.UpgradeManager;

/**
 * Created by itbird on 2022/3/1
 */


public class Client extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageviewl_load_bitmap_test);
        RequestHandler1 handler1 = new RequestHandler1();
        RequestHandler2 handler2 = new RequestHandler2();
        RequestHandler3 handler3 = new RequestHandler3();
        handler1.setNext(handler2);
        handler2.setNext(handler3);
        Request request = new Request();
        request.setUrl("xxxxxxxxxxxx");
        handler1.handlerRequeset(request);

        UpgradeManager.getInstance().startUpgrade("/sdcard/xxx.zip");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
