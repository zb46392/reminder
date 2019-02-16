package com.example.reminder;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private MemoViewModel memoViewModel;
    private SparseBooleanArray selectedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.selectedItems = new SparseBooleanArray();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        RecyclerView recMemos = findViewById(R.id.memoRecyclerView);

        recMemos.setLayoutManager(llm);
        recMemos.setHasFixedSize(true);
        recMemos.addItemDecoration(new DividerItemDecoration(recMemos.getContext(), llm.getOrientation()));

        final MemoRecylerViewAdapter memoRecAdapter = new MemoRecylerViewAdapter();
        recMemos.setAdapter(memoRecAdapter);

        this.memoViewModel = ViewModelProviders.of(this).get(MemoViewModel.class);
        this.memoViewModel.getLiveMemos().observe(this, new Observer<List<Memo>>() {
            @Override
            public void onChanged(@Nullable List<Memo> memos) {
                memoRecAdapter.submitList(memos);
            }
        });

        memoRecAdapter.setOnItemClickListener(new MemoRecylerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Memo memo) {
                Intent intent = new Intent(MainActivity.this, UpdateMemoActivity.class);

                intent.putExtra(MemoApp.memoExtra, memo);

                startActivity(intent);
                Log.d(TAG, "item clicked... Memo ID: " + memo.getId());
            }
        });

        memoRecAdapter.setOnItemLongClickListener(new MemoRecylerViewAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(Memo memo) {
                Log.d(TAG, "item long clicked...");

                // to_do check if at least 1 selected to enable delete else delete disabled
                if (selectedItems.get(memo.getId(), false)) {
                    selectedItems.delete(memo.getId());
                } else {
                    selectedItems.put(memo.getId(), true);
                }
            }
        });


        FloatingActionButton fab = findViewById(R.id.fabNewMemo);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewMemoActivity();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.new_memo) {
            this.startNewMemoActivity();
            //return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void startNewMemoActivity(){
        this.startActivity(new Intent(this, NewMemoActivity.class));
    }
}
