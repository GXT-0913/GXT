package com.example.day3;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    /**
     * 跳过
     */
    private Button mBtnTo;
    private ImageView mIvImage;
    private TextView mTvName;
    private int cound=5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData()
;    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 0) {
                cound--;
                String[] str = new String[]{"一", "二", "三", "四", "五"};
                mTvName.setText(str[cound]);
            }
            return false;
        }
    });

    private void initData() {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (cound == 1) {
                    timer.cancel();
                    Intent intent = new Intent(MainActivity.this, ListActivity.class);
                    startActivity(intent);
                }
                handler.sendEmptyMessage(0);
            }
        }, 1000, 1000);
    }


    private void initView() {
        mBtnTo = (Button) findViewById(R.id.btn_to);
        mBtnTo.setOnClickListener(this);
        mIvImage = (ImageView) findViewById(R.id.iv_image);
        mTvName = (TextView) findViewById(R.id.tv_name);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(mIvImage, "alpha", 1f, 0f, 1f);
        alpha.setDuration(5000);
        alpha.start();
        ObjectAnimator rotation = ObjectAnimator.ofFloat(mIvImage, "rotation", 0f, 360f, 0f);
        rotation.setDuration(5000);
        rotation.start();
        ObjectAnimator scale = ObjectAnimator.ofFloat(mIvImage, "scaleY", 1f, 4f, 1f);
        scale.setDuration(5000);
        scale.start();
        ObjectAnimator translation = ObjectAnimator.ofFloat(mIvImage, "translationX", 0, 200);
        translation.setDuration(5000);
        translation.start();
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(translation).with(scale).with(rotation).after(alpha);
        animatorSet.setDuration(5000);
        animatorSet.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_to:
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
                break;
        }
    }
}
