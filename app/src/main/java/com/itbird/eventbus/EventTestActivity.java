package com.itbird.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.itbird.R;
import com.itbird.bindViewruntime.ItbirdBindView;
import com.itbird.bindview.annotation.ItbirdAopBinderView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Created by itbird on 2022/3/1
 */


public class EventTestActivity extends AppCompatActivity {
    @ItbirdAopBinderView(R.id.button)
    Button button;
    @ItbirdAopBinderView(R.id.textview)
    Button textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventbus_test);
        ItbirdBindView.bind(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EventTestActivity.this, EventTest2Activity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onMsgEvent(EventMsg eventMsg) {
        textview.setText(eventMsg.getMessge());
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
