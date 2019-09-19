package com.example.cumulus.gxt.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.cumulus.gxt.Bean.ResultBean;
import com.example.cumulus.gxt.R;

import java.util.ArrayList;
import java.util.List;

public class HomeRecyAdapter extends RecyclerView.Adapter {
    List<ResultBean.DataBean.DatasBean> datas = new ArrayList<>();
    Context context;

    public HomeRecyAdapter(Context context) {
        this.context = context;
    }

    public void initData(List<ResultBean.DataBean.DatasBean> datas) {
        this.datas.clear();
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    public void delete(int pos) {
        this.datas.remove(pos);
        notifyDataSetChanged();
    }

    public void update(int pos) {
        ResultBean.DataBean.DatasBean datasBean = this.datas.get(pos);
        datasBean.setChapterName("我是修改的的诗句");
        datasBean.setDesc("我的下表是" + pos);
        notifyDataSetChanged();
    }

    private static final int IMG_LEFT_TYPE = 0;
    private static final int IMG_RIGHT_TYPE = 1;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == IMG_LEFT_TYPE) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.itme_left, parent, false);
            return new ImgLeftViewHolder(inflate);
        } else {
            View inflate = LayoutInflater.from(context).inflate(R.layout.itme_right, parent, false);
            return new ImgRightViewHolder(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ImgLeftViewHolder) {
            ResultBean.DataBean.DatasBean datasBean = datas.get(position);
            ((ImgLeftViewHolder) holder).title_left.setText(datasBean.getChapterName());
            ((ImgLeftViewHolder) holder).desc_left.setText(datasBean.getDesc());
            RequestOptions options = new RequestOptions().circleCrop();
            Glide.with(context).load(datasBean.getEnvelopePic()).apply(options).into(((ImgLeftViewHolder) holder).img_left);
        } else {
            ResultBean.DataBean.DatasBean datasBean = datas.get(position);
            ((ImgRightViewHolder) holder).title_right.setText(datasBean.getChapterName());
            ((ImgRightViewHolder) holder).desc_right.setText(datasBean.getDesc());
            RequestOptions options = new RequestOptions().circleCrop();
            Glide.with(context).load(datasBean.getEnvelopePic()).apply(options).into(((ImgRightViewHolder) holder).img_right);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListeren != null) {
                    onItemClickListeren.onItemCilck(position);
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return IMG_LEFT_TYPE;
        } else {
            return IMG_RIGHT_TYPE;
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ImgLeftViewHolder extends RecyclerView.ViewHolder {
        ImageView img_left;
        TextView title_left;
        TextView desc_left;

        public ImgLeftViewHolder(View itemView) {
            super(itemView);
            img_left = itemView.findViewById(R.id.img_left);
            title_left = itemView.findViewById(R.id.title_left);
            desc_left = itemView.findViewById(R.id.desc_left);
        }
    }

    class ImgRightViewHolder extends RecyclerView.ViewHolder {
        ImageView img_right;
        TextView title_right;
        TextView desc_right;

        public ImgRightViewHolder(View itemView) {
            super(itemView);
            img_right = itemView.findViewById(R.id.img_right);
            title_right = itemView.findViewById(R.id.title_right);
            desc_right = itemView.findViewById(R.id.desc_right);
        }
    }

    OnItemClickListeren onItemClickListeren;

    public void setOnItemClickListeren(OnItemClickListeren onItemClickListeren) {
        this.onItemClickListeren = onItemClickListeren;
    }

    public interface OnItemClickListeren {
        void onItemCilck(int position);
    }
}
