package com.example.reminder;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);

        new MemosLoader().execute();

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

    @Override
    protected void onResume() {
        super.onResume();
        new MemosLoader().execute();
    }

    private void startNewMemoActivity(){
        this.startActivity(new Intent(this, NewMemoActivity.class));
    }

    private void updateMemoRecycleView(List<Memo> memos){
        MemoRecylerViewAdapter memoRecAdapter = new MemoRecylerViewAdapter(memos, getApplicationContext());
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        RecyclerView recMemos = findViewById(R.id.memoRecyclerView);

        recMemos.setLayoutManager(llm);

        recMemos.addItemDecoration(new DividerItemDecoration(recMemos.getContext(), llm.getOrientation()));

        recMemos.swapAdapter(memoRecAdapter, true);
    }

    private class MemosLoader extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {

            return this.loadMemosFromDb();
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            updateMemoRecycleView((List<Memo>) o);
        }

        private List<Memo> loadMemosFromDb(){
            return ((MemoApp)getApplication()).getMemoDB().memoDao().getAll();
        }
    }
}
