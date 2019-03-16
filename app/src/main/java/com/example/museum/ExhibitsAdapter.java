package com.example.museum;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

public class ExhibitsAdapter extends RecyclerView.Adapter<ExhibitsAdapter.ViewHolder> {

    private Context mContext;
    private List<Exhibit>exhibitList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View recyclerView;
        ImageView imageView;
        TextView titleText;
        TextView contentText;

        public ViewHolder(View view){
            super(view);
            recyclerView=view;
            imageView=(ImageView)view.findViewById(R.id.image);
            titleText=(TextView) view.findViewById(R.id.title);
            contentText=(TextView)view.findViewById(R.id.content);
        }
    }

    public ExhibitsAdapter(List<Exhibit>list){
        exhibitList=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mContext==null){
            mContext=viewGroup.getContext();
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.list_exhitbit,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        Exhibit exhibit=exhibitList.get(i);
        viewHolder.titleText.setText(exhibit.getName());
        viewHolder.contentText.setText(exhibit.getContent());
        Glide.with(mContext).load(exhibit.getImg()).into(viewHolder.imageView);
//        Glide.with(mContext).load(exhibition.getId()).into(viewHolder.exhibitionImage);

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
        return exhibitList.size();
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
