package com.nada.ecommerceapp.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nada.ecommerceapp.R;

import org.w3c.dom.Comment;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    private Context mContext;
    private List<Comment> mData;


    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row= LayoutInflater.from(mContext).inflate(R.layout.comments,parent,false);
        return new CommentViewHolder(row);
    }

    @Override
    public void onBindViewHolder (@NonNull CommentViewHolder holder, int viewType) {

    }
    public int getItemCount(){
        return mData.size();
    }
    public class CommentViewHolder extends RecyclerView.ViewHolder{
        ImageView userImg;
        TextView userName,CommBody;
        public CommentViewHolder(View itemView) {
            super(itemView);
            userImg=itemView.findViewById(R.id.UserPic);
            userName=itemView.findViewById(R.id.User);
            CommBody=itemView.findViewById(R.id.commBody);
        }
    }
}
