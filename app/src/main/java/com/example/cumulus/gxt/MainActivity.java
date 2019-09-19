package com.example.cumulus.gxt;

import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.cumulus.gxt.adapter.FragPagerAdapter;
import com.example.cumulus.gxt.fragment.HomeFragment;
import com.example.cumulus.gxt.fragment.PhoneFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mVpMain;
    private TabLayout mTabMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mVpMain = (ViewPager) findViewById(R.id.vp_main);
        mTabMain = (TabLayout) findViewById(R.id.tab_main);
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new PhoneFragment());
        List<String> titls = new ArrayList<>();
        titls.add("首页");
        titls.add("通讯录");
        FragPagerAdapter adapter = new FragPagerAdapter(getSupportFragmentManager(), fragmentList, titls);
        mVpMain.setAdapter(adapter);
        mTabMain.setupWithViewPager(mVpMain);
    }
}
