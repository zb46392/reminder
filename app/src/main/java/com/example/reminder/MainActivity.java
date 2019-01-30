package com.example.reminder;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Memo> testMemos = new ArrayList<Memo>(Arrays.asList(
            new Memo(new Date(), "Ispit: Metode optimizacije", "", Memo.Period.none),
            new Memo(new Date(), "Ispit: Upravljanje kvalitetom i metrika", "", Memo.Period.none),
            new Memo(new Date(), "Ispit: Mobilne tehnologije", "", Memo.Period.none),
            new Memo(new Date(), "Ispit: Kolaboracija i upravljanje dokumentima", "", Memo.Period.none),
            new Memo(new Date(), "Ispit: Poslovni sustavi za upravljanje sadržaja na webu", "", Memo.Period.none),
            new Memo(new Date(), "Ispit: Metode optimizacije", "", Memo.Period.none),
            new Memo(new Date(), "Ispit: Upravljanje kvalitetom i metrika", "", Memo.Period.none),
            new Memo(new Date(), "Ispit: Kolaboracija i upravljanje dokumentima", "", Memo.Period.none),
            new Memo(new Date(), "Ispit: Metode optimizacije", "", Memo.Period.none),
            new Memo(new Date(), "Ispit: Upravljanje kvalitetom i metrika", "", Memo.Period.none),
            new Memo(new Date(), "Ispit: Mobilne tehnologije", "", Memo.Period.none),
            new Memo(new Date(), "Ispit: Kolaboracija i upravljanje dokumentima", "", Memo.Period.none),
            new Memo(new Date(), "Ispit: Poslovni sustavi za upravljanje sadržaja na webu", "", Memo.Period.none),
            new Memo(new Date(), "Ispit: Metode optimizacije", "", Memo.Period.none),
            new Memo(new Date(), "Ispit: Upravljanje kvalitetom i metrika", "", Memo.Period.none),
            new Memo(new Date(), "Ispit: Kolaboracija i upravljanje dokumentima", "", Memo.Period.none),
            new Memo(new Date(), "Ispit: Poslovni sustavi za upravljanje sadržaja na webu", "", Memo.Period.none)
    ));

    private MemoRecylerViewAdapter memoRecAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);

        RecyclerView recMemos = (RecyclerView)findViewById(R.id.memoRecyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(this.getApplicationContext());
        recMemos.setLayoutManager(llm);
        this.memoRecAdapter = new MemoRecylerViewAdapter(this.testMemos, this);

        recMemos.setAdapter(memoRecAdapter);
        recMemos.addItemDecoration(new DividerItemDecoration(recMemos.getContext(), llm.getOrientation()));


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
