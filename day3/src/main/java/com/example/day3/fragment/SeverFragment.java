package com.example.day3.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.day3.AnServer;
import com.example.day3.R;

public class SeverFragment extends Fragment implements View.OnClickListener {
    private View view;
    /**
     * 启动服务
     */
    private Button mStart;
    /**
     * 关闭服务
     */
    private Button mStop;
    private Intent intent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.item_sever, null);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        mStart = (Button) inflate.findViewById(R.id.start);
        mStart.setOnClickListener(this);
        mStop = (Button) inflate.findViewById(R.id.stop);
        mStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.start:
                intent = new Intent(getActivity(), AnServer.class);
               getActivity().startService(intent);
                break;
            case R.id.stop:
                getActivity().stopService(intent);
                break;
        }
    }
}
