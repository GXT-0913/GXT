package com.example.day3.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.day3.R;
import com.example.day3.bean.ArticleServer;
import com.example.day3.bean.ArtivleBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class KoneFragment extends Fragment {
    private View view;
    private RecyclerView mRvReler;
    private KoneResylerAdpater koneResylerAdpater;
    private static final String TAG = "KoneFragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.item_kone_tu, null);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ArticleServer.URL).build();
        ArticleServer articleServer = retrofit.create(ArticleServer.class);
        Call<ResponseBody> call = articleServer.getData("294");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String json = response.body().string();
                    Gson gson = new GsonBuilder().serializeNulls().create();
                    ArtivleBean artivleBean = gson.fromJson(json, ArtivleBean.class);
                    List<ArtivleBean.DataBean.DatasBean> datas = artivleBean.getData().getDatas();
                    koneResylerAdpater.initData(datas);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage() );
            }
        });
    }

    private void initView(View inflate) {
        mRvReler = (RecyclerView) inflate.findViewById(R.id.rv_reler);
        mRvReler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvReler.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        koneResylerAdpater = new KoneResylerAdpater(getActivity());
        mRvReler.setAdapter(koneResylerAdpater);

    }
}
