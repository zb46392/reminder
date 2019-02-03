package com.example.reminder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public abstract class BaseMemoDetailActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private FloatingActionButton fabSaveMemo;
    private EditText title;
    private EditText body;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_detail);
        this.toolbar = findViewById(R.id.memoDetailToolbar);
        setSupportActionBar(toolbar);

        this.title = findViewById(R.id.baseMemoTitle);
        this.body = findViewById(R.id.baseMemoBody);
        this.fabSaveMemo = findViewById(R.id.fabMemoDetail);

        this.fabSaveMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMemo();
            }
        });
    }

    protected Toolbar getToolbar(){
        return this.toolbar;
    }

    protected FloatingActionButton getFabSaveMemo(){
        return this.fabSaveMemo;
    }

    protected EditText getMemoTitle(){
        return this.title;
    }

    protected EditText getMemoBody(){
        return  this.body;
    }

    protected abstract void saveMemo();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_memo_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.save_memo) {
            this.saveMemo();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
