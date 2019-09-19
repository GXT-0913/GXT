package com.example.day3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.day3.bean.PageApdeter;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mPager;
    /**
     * 完成
     */
    private Button mButt;
    private PageApdeter pageApdeter;
    private TextView mTvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initView();
        initData();
    }

    private void initData() {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(R.drawable.bus);
        integerList.add(R.drawable.home);
        integerList.add(R.drawable.sc);
        pageApdeter = new PageApdeter(this, integerList);
        mPager.setAdapter(pageApdeter);
    }

    private void initView() {
        mPager = (ViewPager) findViewById(R.id.pager);
        mButt = (Button) findViewById(R.id.butt);
        mButt.setOnClickListener(this);
            mTvList = (TextView) findViewById(R.id.tv_list);
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mTvList.setText(position+1+"/ 3");
            }

            @Override
            public void onPageSelected(int position) {
                if (pageApdeter.getCount() - 1 == position) {
                    mButt.setVisibility(View.VISIBLE);
                } else {
                    mButt.setVisibility(View.GONE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.butt:
                Intent intent = new Intent(ListActivity.this, RecylerActivity.class);
                startActivity(intent);
                break;

        }
    }
}
