package com.example.day3.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.day3.R;
import com.example.day3.bean.ListBean;

import java.util.ArrayList;
import java.util.List;

public class ListLeedAdpater extends BaseAdapter {
    Context context;
    List<ListBean> list=new ArrayList<>();

    public ListLeedAdpater(Context context, List<ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       convertView= LayoutInflater.from(context).inflate(R.layout.item_leed,null);
        TextView tit = convertView.findViewById(R.id.tv_tit);
        TextView name = convertView.findViewById(R.id.tv_na);
        tit.setText(list.get(position).getNumber());
        name.setText(list.get(position).getName());
        return convertView;
    }
}
