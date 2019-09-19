package com.example.day3.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.day3.R;
import com.example.day3.bean.ListBean;

import java.util.ArrayList;
import java.util.List;

public class LeedFragment extends Fragment {
    private View view;
    private ListView mLvList;
    private int i = -1;
    private List<ListBean> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.item_leed_frment, null);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {
        list = new ArrayList<>();
        Cursor query = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (query.moveToNext()) {
            String name = query.getString(query.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String num = query.getString(query.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            list.add(new ListBean(name, num));
        }
        ListLeedAdpater listLeedAdpater = new ListLeedAdpater(getActivity(), list);
        mLvList.setAdapter(listLeedAdpater);
      /*  ListBean listBean = list.get(i);
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri parse = Uri.parse("tel:" + listBean.getNumber());
        intent.setData(parse);
        startActivity(intent);*/
    }

    private void initView(View inflate) {
        mLvList = (ListView) inflate.findViewById(R.id.lv_list);
        mLvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                i = position;
            }
        });

    }
}
