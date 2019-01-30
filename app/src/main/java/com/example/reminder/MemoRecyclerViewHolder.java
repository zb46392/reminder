package com.example.reminder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MemoRecyclerViewHolder extends RecyclerView.ViewHolder {
    private TextView repeat, time, title;
    private LinearLayout background;

    public MemoRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        this.repeat = (TextView)itemView.findViewById(R.id.textRepeat);
        this.time = (TextView)itemView.findViewById(R.id.textTime);
        this.title = (TextView)itemView.findViewById(R.id.textTitle);
        this.background = (LinearLayout)itemView.findViewById(R.id.memoRecListItem);
    }

    public TextView getRepeat() {
        return this.repeat;
    }

    public TextView getTime() {
        return this.time;
    }

    public TextView getTitle() {
        return this.title;
    }

    public LinearLayout getBackground(){
        return this.background;
    }
}
