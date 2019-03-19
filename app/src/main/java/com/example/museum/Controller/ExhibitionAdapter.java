package com.example.museum.Controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.museum.Model.Exhibition;
import com.example.museum.R;

import java.util.List;

public class ExhibitionAdapter extends RecyclerView.Adapter<ExhibitionAdapter.ViewHolder> {

    private Context mContext;
    private List<Exhibition>mExhibitionList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView exhibitionImage;
        TextView exhibitionText;

        public ViewHolder(View view){
            super(view);
            cardView=(CardView)view;
            exhibitionImage=(ImageView)view.findViewById(R.id.image);
            exhibitionText=(TextView) view.findViewById(R.id.text);
        }
    }

    public ExhibitionAdapter(List<Exhibition>exhibitionList){
        mExhibitionList=exhibitionList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mContext==null){
            mContext=viewGroup.getContext();
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.exhibition_item,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        Exhibition exhibition=mExhibitionList.get(i);
        viewHolder.exhibitionText.setText(exhibition.getName());
        Glide.with(mContext).load(exhibition.getId()).into(viewHolder.exhibitionImage);

//        添加点击
        if (mOnItemClickListener!=null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    int position= viewHolder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(viewHolder.itemView,position);
                }
            });
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position= viewHolder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(viewHolder.itemView,position);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mExhibitionList.size();
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }

    public OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener=mOnItemClickListener;
    }


}
