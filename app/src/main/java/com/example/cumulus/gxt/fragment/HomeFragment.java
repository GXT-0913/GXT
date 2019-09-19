package com.example.cumulus.gxt.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.example.cumulus.gxt.Bean.ResultBean;
import com.example.cumulus.gxt.ContrantServer;
import com.example.cumulus.gxt.R;
import com.example.cumulus.gxt.adapter.HomeRecyAdapter;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private View view;
    private RecyclerView mRecyHome;
    private HomeRecyAdapter adapter;
    private PopupWindow popupWindow;
    private int pos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.homefragment_layout, null);
        initView(inflate);
        initData();
        initPopu();
        return inflate;
    }

    private void initPopu() {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.pupo_homefragmen, null);
        Button ok = inflate.findViewById(R.id.ok_pop);
        Button no = inflate.findViewById(R.id.no_pop);
        ok.setOnClickListener(this);
        no.setOnClickListener(this);
        popupWindow = new PopupWindow(inflate, 300, 300);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.PopAnimation);
    }

    private void initData() {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request build = new Request.Builder().url(ContrantServer.URL).build();
        Call call = client.newCall(build);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                ResultBean resultBean = new Gson().fromJson(string, ResultBean.class);
                final List<ResultBean.DataBean.DatasBean> datas = resultBean.getData().getDatas();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.initData(datas);
                    }
                });
            }
        });

    }

    private void initView(View inflate) {
        mRecyHome = (RecyclerView) inflate.findViewById(R.id.recy_home);
        mRecyHome.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new HomeRecyAdapter(getActivity());
        mRecyHome.setAdapter(adapter);
        adapter.setOnItemClickListeren(new HomeRecyAdapter.OnItemClickListeren() {
            @Override
            public void onItemCilck(int position) {
                pos = position;
                popupWindow.showAtLocation(mRecyHome,Gravity.CENTER,0,0);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.ok_pop:
                adapter.delete(pos);
                break;
            case R.id.no_pop:
                adapter.update(pos);
                break;
        }
        popupWindow.dismiss();
    }


}
