package com.example.assignment2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryRecyclerAdapter extends RecyclerView.Adapter<HistoryRecyclerAdapter.viewHolder> {
    ArrayList<History> histories;
    Context myContext;

    public interface OnItemClickListner{
        void onHistoryClicked(History history);
    }

    private final OnItemClickListner listner;

    public HistoryRecyclerAdapter(ArrayList<History> histories, Context context,OnItemClickListner listnerFromActivity) {
        this.histories = histories;
        this.myContext = context;
        listner = listnerFromActivity;
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        private final TextView name;
        private final TextView price;
        private final TextView qty;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            qty = itemView.findViewById(R.id.qty);
        }

        public TextView getName() {
            return name;
        }

        public TextView getPrice() {
            return price;
        }

        public TextView getQty() {
            return qty;
        }
    }

    @NonNull
    @Override
    public HistoryRecyclerAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater myInflater =  LayoutInflater.from(myContext);
        View view =  myInflater.inflate(R.layout.recycler_view_list_row,parent,false);
        return new viewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull HistoryRecyclerAdapter.viewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.getName().setText(histories.get(position).name);
        holder.getPrice().setText(histories.get(position).total);
        holder.getQty().setText(histories.get(position).qty);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.onHistoryClicked(histories.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return histories.size();
    }
}
