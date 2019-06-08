package com.example.thefinalexam;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> implements View.OnClickListener {

    private Context mContext;
    private ArrayList<Actress> mData;

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(view,(int)view.getTag());
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public static interface OnItemClickListener {
        void onItemClick(View view , int position);
    }


    private OnItemClickListener mOnItemClickListener = null;


    public Adapter(Context context, ArrayList<Actress> data) {
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);

        holder.ivPosterThumbnail = (ImageView) view.findViewById(R.id.ivPosterThumbnail);
        holder.tvPosterName = (TextView) view.findViewById(R.id.tvPosterName);
        holder.tvContent = (TextView) view.findViewById(R.id.tvContent);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Actress actress = mData.get(position);
        holder.tvPosterName.setText(actress.getName());
        holder.tvContent.setText(actress.getCup());
        Glide.with(mContext)
                .load(actress.getPosterThumbnailUrl())
                .into(holder.ivPosterThumbnail);
        holder.itemView.setTag(position);
    }




    @Override
    public int getItemCount() {
        return mData.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivPosterThumbnail;
        public TextView tvPosterName;
        public TextView tvContent;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

}
