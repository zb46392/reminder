package com.example.reminder;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public abstract class BaseMemoDetailActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private FloatingActionButton fabSaveMemo;
    private EditText title;
    private EditText body;
    private Memo memo;
    private static final String TAG = BaseMemoDetailActivity.class.getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_detail);
        this.toolbar = findViewById(R.id.memoDetailToolbar);
        setSupportActionBar(toolbar);

        this.title = findViewById(R.id.baseMemoTitle);
        this.body = findViewById(R.id.baseMemoBody);
        this.fabSaveMemo = findViewById(R.id.fabMemoDetail);
        this.memo = null;

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

    protected void setMemo(Memo memo){ this.memo = memo; }
    protected Memo getMemo() { return this.memo; }

    protected abstract void saveMemo();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_memo_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch(item.getItemId()){
            case R.id.save_memo:
                this.saveMemo();
                return true;

            case R.id.delete_single_memo:
                this.deleteMemo();
                return true;

            case R.id.set_single_reminder:
                this.setReminder();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void deleteMemo(){
        if(this.memo != null){

            new AlertDialog.Builder(this).setMessage(getResources().getString(R.string.deleteMemoDialog)).
                    setNegativeButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.d(TAG, "Deletion confirmed.");

                    new AsyncMemoDeleter().execute();
                }
            }).setPositiveButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.d(TAG, "Deletion confirmed.");
                }
            }).show();
        }
    }

    private void setReminder(){

    }

    private class AsyncMemoDeleter extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            ((MemoApp)getApplication()).getMemoDB().memoDao().delete(getMemo());

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            Toast.makeText(getApplicationContext(), getResources().getString(R.string.memoDeleted),
                    Toast.LENGTH_LONG).show();

            finish();
        }
    }
}
