package com.example.day3.fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.day3.R;
import com.example.day3.bean.ArtivleBean;

import java.util.ArrayList;
import java.util.List;

public class KoneResylerAdpater extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int HOME=1;
    private static final int KONE=2;

  Context context;
  List<ArtivleBean.DataBean.DatasBean> beanList=new ArrayList<>();

    public KoneResylerAdpater(Context context) {
        this.context = context;
    }
    public void initData(List<ArtivleBean.DataBean.DatasBean> beanList){
        this.beanList.clear();
        this.beanList.addAll(beanList);
        notifyDataSetChanged();
    }
    public void deletede(int position){
        this.beanList.remove(position);
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       if (viewType==HOME){
           View inflate = LayoutInflater.from(context).inflate(R.layout.kone_frment_item, parent, false);
           return new HomeViewHolder(inflate);
       }else {
           View inflate = LayoutInflater.from(context).inflate(R.layout.kont_frment_item_tu, parent, false);
           return new KoneViewHolder(inflate);
       }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ArtivleBean.DataBean.DatasBean datasBean = beanList.get(position);
        if (holder instanceof HomeViewHolder){
            ((HomeViewHolder) holder).name.setText(datasBean.getChapterName());
            ((HomeViewHolder) holder).title.setText(datasBean.getTitle());

            Glide.with(context).load(datasBean.getEnvelopePic()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(((HomeViewHolder) holder).ima);
        }else {
            ((KoneViewHolder) holder).name.setText(datasBean.getChapterName());
            ((KoneViewHolder) holder).title.setText(datasBean.getTitle());
            Glide.with(context).load(datasBean.getEnvelopePic()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(((KoneViewHolder) holder).ima);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onitemClick!=null){
                    onitemClick.itemClick(position);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
              if (onitemClick!=null){
                  onitemClick.itemLongClick();
              }

                return false;
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        if (position%2==0){
            return HOME;
        }else {
            return KONE;
        }
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }


    class HomeViewHolder extends RecyclerView.ViewHolder{
      ImageView ima;
      TextView name;
      TextView title;
        public HomeViewHolder(View itemView) {
            super(itemView);
            ima=itemView.findViewById(R.id.iv_img);
            title=itemView.findViewById(R.id.tv_konetitle);
            name=itemView.findViewById(R.id.tv_konename);
        }
    }
    class KoneViewHolder extends RecyclerView.ViewHolder{
        ImageView ima;
        TextView name;
        TextView title;

        public KoneViewHolder(View itemView) {
            super(itemView);
            ima=itemView.findViewById(R.id.iv_img);
            title=itemView.findViewById(R.id.tv_konetitle);
            name=itemView.findViewById(R.id.tv_konename);
        }
    }
    OnitemClick onitemClick;

    public void setOnitemClick(OnitemClick onitemClick) {
        this.onitemClick = onitemClick;
    }

    public interface OnitemClick{
        void itemClick(int position);
        void itemLongClick();
    }
}
