package com.example.day3.bean;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.day3.R;

import java.util.List;

public class PageApdeter extends PagerAdapter {
    Context context;
    List<Integer> integers;

    public PageApdeter(Context context, List<Integer> integers) {
        this.context = context;
        this.integers = integers;
    }

    @Override
    public int getCount() {
        return integers.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.imaitem, null);
        ImageView ima = inflate.findViewById(R.id.imapager);
        ima.setImageResource(integers.get(position));
        container.addView(inflate);
        return inflate;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
}
