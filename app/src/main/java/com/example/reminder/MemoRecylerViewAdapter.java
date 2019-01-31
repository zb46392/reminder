package com.example.reminder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MemoRecylerViewAdapter extends RecyclerView.Adapter<MemoRecyclerViewHolder> {
    private static final String TAG = MemoRecylerViewAdapter.class.getSimpleName();
    private ArrayList<Memo> memos;
    private SparseBooleanArray selectedItems;
    private Context context;

    public MemoRecylerViewAdapter(ArrayList<Memo> memos, Context context){

        this.memos = memos;
        this.context = context;
        this.selectedItems = new SparseBooleanArray();
    }

    @NonNull
    @Override
    public MemoRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row, viewGroup, false);
        MemoRecyclerViewHolder vH = new MemoRecyclerViewHolder(v);

        return vH;
    }

    @Override
    public void onBindViewHolder(@NonNull final MemoRecyclerViewHolder memoRecyclerViewHolder, int i) {

        memoRecyclerViewHolder.getRepeat().setText(this.memos.get(i).getPeriod().toString());
        memoRecyclerViewHolder.getTime().setText(this.memos.get(i).getFormattedDate());
        memoRecyclerViewHolder.getTitle().setText(this.memos.get(i).getTitle());

        this.setColors(memoRecyclerViewHolder, selectedItems.get(i, false));

        memoRecyclerViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "item clicked...");
            }
        });

        memoRecyclerViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d(TAG, "item long clicked...");

                if(selectedItems.get(memoRecyclerViewHolder.getAdapterPosition(), false)){
                    selectedItems.delete(memoRecyclerViewHolder.getAdapterPosition());
                    setColors(memoRecyclerViewHolder, false);
                } else {
                    selectedItems.put(memoRecyclerViewHolder.getAdapterPosition(), true);
                    memoRecyclerViewHolder.getBackground().setSelected(true);
                    setColors(memoRecyclerViewHolder, true);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.memos.size();
    }

    private void setColors(MemoRecyclerViewHolder memoRecyclerViewHolder ,Boolean isSelected){
        if(isSelected){
            memoRecyclerViewHolder.getBackground().setSelected(isSelected);
            memoRecyclerViewHolder.getRepeat().setTextColor(ContextCompat.getColor(context, R.color.colorSelectedText));
            memoRecyclerViewHolder.getTime().setTextColor(ContextCompat.getColor(context, R.color.colorSelectedText));
            memoRecyclerViewHolder.getTitle().setTextColor(ContextCompat.getColor(context, R.color.colorSelectedText));
        } else {
            memoRecyclerViewHolder.getBackground().setSelected(isSelected);
            memoRecyclerViewHolder.getTitle().setTextColor(ContextCompat.getColor(context, R.color.colorText));
            memoRecyclerViewHolder.getTime().setTextColor(ContextCompat.getColor(context,R.color.colorText));
            memoRecyclerViewHolder.getRepeat().setTextColor(ContextCompat.getColor(context,R.color.colorText));
        }
    }
}