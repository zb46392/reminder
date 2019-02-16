package com.example.reminder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MemoRecylerViewAdapter extends ListAdapter<Memo, MemoRecylerViewAdapter.MemoRecyclerViewHolder> {
    private static final String TAG = MemoRecylerViewAdapter.class.getSimpleName();
    private OnItemClickListener clickListener;
    private OnItemLongClickListener longClickListener;
    private static final DiffUtil.ItemCallback<Memo> DIFF_CALLBACK = new DiffUtil.ItemCallback<Memo>() {
        @Override
        public boolean areItemsTheSame(@NonNull Memo oldMemo, @NonNull Memo newMemo) {
            return oldMemo.getId() == newMemo.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Memo oldMemo, @NonNull Memo newMemo) {
            return oldMemo.getTitle().equals(newMemo.getTitle())
                    && oldMemo.getBody().equals(newMemo.getBody())
                    && oldMemo.getFormattedReminderDate().equals(newMemo.getFormattedReminderDate())
                    && oldMemo.getPeriod().equals(newMemo.getPeriod());
        }
    };

    public MemoRecylerViewAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public MemoRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row, viewGroup, false);

        return new MemoRecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MemoRecyclerViewHolder memoRecyclerViewHolder, int i) {
        memoRecyclerViewHolder.repeat.setText(getItem(i).getPeriod().toString());
        memoRecyclerViewHolder.time.setText(getItem(i).getFormattedReminderDate());
        memoRecyclerViewHolder.title.setText(getItem(i).getTitle());
        memoRecyclerViewHolder.background.setSelected(false);
    }

    class MemoRecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView repeat, time, title;
        private LinearLayout background;
        private Context context;

        public MemoRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            this.repeat = itemView.findViewById(R.id.textRepeat);
            this.time = itemView.findViewById(R.id.textTime);
            this.title = itemView.findViewById(R.id.textTitle);
            this.background = itemView.findViewById(R.id.memoRecListItem);
            this.context = itemView.getContext();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer position = getAdapterPosition();
                    if (clickListener != null && position != RecyclerView.NO_POSITION) {
                        clickListener.onItemClick(getItem(position));
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Integer position = getAdapterPosition();
                    if (longClickListener != null && position != RecyclerView.NO_POSITION) {
                        setColors();
                        longClickListener.onItemLongClick(getItem(position));
                        return true;
                    } else {
                        return false;
                    }
                }
            });
        }

        public void setColors() {

            if (this.background.isSelected()) {
                this.background.setSelected(false);
                this.repeat.setTextColor(ContextCompat.getColor(context, R.color.colorSelectedText));
                this.time.setTextColor(ContextCompat.getColor(context, R.color.colorSelectedText));
                this.title.setTextColor(ContextCompat.getColor(context, R.color.colorSelectedText));
            } else {
                this.background.setSelected(true);
                this.title.setTextColor(ContextCompat.getColor(context, R.color.colorText));
                this.time.setTextColor(ContextCompat.getColor(context, R.color.colorText));
                this.repeat.setTextColor(ContextCompat.getColor(context, R.color.colorText));
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Memo memo);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(Memo memo);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.longClickListener = listener;
    }
}