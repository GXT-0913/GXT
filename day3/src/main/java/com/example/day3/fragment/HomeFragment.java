package com.example.day3.fragment;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.example.day3.R;
import com.example.day3.bean.ArticleServer;
import com.example.day3.bean.ArtivleBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment {
    private View view;
    private RecyclerView mRvRecyler;
    private SmartRefreshLayout mSrlRefresh;
    private ResylerAdpater resylerAdpater;
    private static final String TAG = "HomeFragment";
    private List<ArtivleBean.DataBean.DatasBean> datas;
    private int pos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.item_fragment, null);
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
                    String jsin = response.body().string();
                    Gson gson = new GsonBuilder().serializeNulls().create();
                    ArtivleBean artivleBean = gson.fromJson(jsin, ArtivleBean.class);
                    datas = artivleBean.getData().getDatas();
                    resylerAdpater.initData(datas);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


    private void initView(View inflate) {
        mRvRecyler = (RecyclerView) inflate.findViewById(R.id.rv_recyler);
        mSrlRefresh = (SmartRefreshLayout) inflate.findViewById(R.id.srl_refresh);
        mRvRecyler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvRecyler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        resylerAdpater = new ResylerAdpater(getActivity());
        mRvRecyler.setAdapter(resylerAdpater);
        mSrlRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                initData();
                mSrlRefresh.autoLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                datas.clear();
                initData();
                mSrlRefresh.finishRefresh();
            }
        });
        resylerAdpater.setOnitemClick(new ResylerAdpater.OnitemClick() {
            @Override
            public void itemClick(int position) {
                pos = position;
                View inflate1 = LayoutInflater.from(getActivity()).inflate(R.layout.item_pop, null);
                PopupWindow popupWindow = new PopupWindow(inflate1, 300, 300);
                final EditText pop = inflate1.findViewById(R.id.et_pop);
                Button ok = inflate1.findViewById(R.id.btn_ok);
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String str = pop.getText().toString();
                        ArtivleBean.DataBean.DatasBean bean = datas.get(pos);
                        bean.setTitle(str);
                        resylerAdpater.notifyDataSetChanged();
                    }
                });
                Button no = inflate1.findViewById(R.id.btn_no);
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        resylerAdpater.deletede(pos);
                    }
                });
                popupWindow.setOutsideTouchable(true);
                popupWindow.setFocusable(true);
                popupWindow.setAnimationStyle(R.style.popitem);
                popupWindow.setBackgroundDrawable(new ColorDrawable());
                popupWindow.showAtLocation(mRvRecyler, Gravity.CENTER, 0, 0);
            }

            @Override
            public void itemLongClick() {

            }
        });
        registerForContextMenu(mRvRecyler);
    }
}
