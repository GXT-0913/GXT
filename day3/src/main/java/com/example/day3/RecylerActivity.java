package com.example.day3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.day3.fragment.FragmentApater;
import com.example.day3.fragment.HomeFragment;
import com.example.day3.fragment.KoneFragment;
import com.example.day3.fragment.LeedFragment;
import com.example.day3.fragment.SeverFragment;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;

import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.READ_PHONE_NUMBERS;
import static android.Manifest.permission.WRITE_CONTACTS;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class RecylerActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks, EasyPermissions.RationaleCallbacks {


    private static final int REQUEST_FILE_CODE = 200;
    private Toolbar mToobar;
    private ViewPager mPader;
    private TabLayout mTabyout;
    private LinearLayout mCentent;
    private NavigationView mNation;
    private DrawerLayout mDlDrwer;
    private ImageButton img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyler);

        String[] permissions = {READ_PHONE_NUMBERS, READ_PHONE_NUMBERS, WRITE_EXTERNAL_STORAGE, READ_CONTACTS, WRITE_CONTACTS, CALL_PHONE};
        if (EasyPermissions.hasPermissions(this, permissions)) {
            initView();
        } else {

            EasyPermissions.requestPermissions(
                    new PermissionRequest.Builder(this, REQUEST_FILE_CODE, permissions)
                            .setRationale("请确认相关权限！！")
                            .setPositiveButtonText("ok")
                            .setNegativeButtonText("cancal")
//                            .setTheme(R.style.my_fancy_style)
                            .build());
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
    private void initView() {
        mToobar = (Toolbar) findViewById(R.id.toobar);
        mPader = (ViewPager) findViewById(R.id.pader);
        mTabyout = (TabLayout) findViewById(R.id.tabyout);
        mCentent = (LinearLayout) findViewById(R.id.centent);
        mNation = (NavigationView) findViewById(R.id.nation);
        mDlDrwer = (DrawerLayout) findViewById(R.id.dl_drwer);
        mToobar.setTitle("首页");
        setSupportActionBar(mToobar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDlDrwer, mToobar, R.string.open, R.string.colse);
        mDlDrwer.addDrawerListener(toggle);
        toggle.syncState();
        List<Fragment> fragments=new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new KoneFragment());
        fragments.add(new LeedFragment());
        fragments.add(new SeverFragment());
        FragmentApater myfrment = new FragmentApater(getSupportFragmentManager(), fragments);
        mPader.setAdapter(myfrment);
        mTabyout.addTab(mTabyout.newTab().setCustomView(setHomeView("1")));
        mTabyout.addTab(mTabyout.newTab().setCustomView(setKoneView("1")));
        mTabyout.addTab(mTabyout.newTab().setCustomView(setleedView("1")));
        mTabyout.addTab(mTabyout.newTab().setCustomView(setServer("1")));
        mTabyout.setupWithViewPager(mPader);
        mTabyout.getTabAt(0).setCustomView(setHomeView("首页"));
        mTabyout.getTabAt(1).setCustomView(setKoneView("我的"));
        mTabyout.getTabAt(2).setCustomView(setleedView("通讯录"));
        mTabyout.getTabAt(3).setCustomView(setServer("服务"));
        View headerView = mNation.getHeaderView(0);
        img = headerView.findViewById(R.id.ib_img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecylerActivity.this,WebActivity.class);
                startActivity(intent);
            }
        });
    }



    public View setHomeView (String name){
        View inflate = LayoutInflater.from(this).inflate(R.layout.item_home, null);
        TextView na = inflate.findViewById(R.id.name);
        na.setText(name);
        return inflate;
    }
    public View setKoneView (String name){
        View inflate = LayoutInflater.from(this).inflate(R.layout.item_kone, null);
        TextView na = inflate.findViewById(R.id.name);
        na.setText(name);
        return inflate;
    }
    public View setleedView (String name){
        View inflate = LayoutInflater.from(this).inflate(R.layout.item_kone, null);
        TextView na = inflate.findViewById(R.id.name);
        na.setText(name);
        return inflate;
    }
    public View setServer (String name){
        View inflate = LayoutInflater.from(this).inflate(R.layout.item_kone, null);
        TextView na = inflate.findViewById(R.id.name);
        na.setText(name);
        return inflate;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.itemheader,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        initView();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onRationaleAccepted(int requestCode) {
        initView();
    }

    @Override
    public void onRationaleDenied(int requestCode) {

    }
}
