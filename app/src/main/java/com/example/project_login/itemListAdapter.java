package com.example.project_login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class itemListAdapter extends RecyclerView.Adapter<itemListAdapter.MyViewHolder> {

    private final DataBaseHelper dataBaseHelper;
    private Activity mContext;
    private List<AddModel> an_list = new ArrayList<>();
    int pos = 0;

    public itemListAdapter(Activity context, List<AddModel> list){
        mContext = context;
        an_list = list;
        dataBaseHelper = new DataBaseHelper(context);
    }

    public void updateList(List<AddModel> list){
        an_list.clear();
        an_list = list;
    }
    @Override
    public int getItemCount() {
        return an_list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle;
        Button deleteBtn , viewBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTitle = (TextView) itemView.findViewById(R.id.itemTitle);
            deleteBtn = (Button) itemView.findViewById(R.id.deleteBtn);
            viewBtn = (Button) itemView.findViewById(R.id.viewBtn);

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_single_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {


        AddModel current = an_list.get(position);
        holder.itemTitle.setText(current.toString());
        if(mContext instanceof Addme) {
            Addme temp = (Addme)mContext;
            holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    temp.deleteItem(current, position);
                }
            });
        }else{
            holder.deleteBtn.setVisibility(View.GONE);
        }
        holder.viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, detailsActivity.class);
                i.putExtra("item_id" , current.getId());
                mContext.startActivity(i);
            }
        });


    }







}