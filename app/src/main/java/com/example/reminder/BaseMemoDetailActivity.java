package com.example.reminder;

import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
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
import java.util.Calendar;


public abstract class BaseMemoDetailActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private FloatingActionButton fabSaveMemo;
    private EditText title;
    private EditText body;
    private Memo memo;
    private MemoViewModel memoViewModel;
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
        this.memo = new Memo();

        this.fabSaveMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMemo();
            }
        });

        this.memoViewModel = ViewModelProviders.of(this).get(MemoViewModel.class);
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

                    memoViewModel.delete(getMemo());

                    finish();
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
        startActivityForResult(new Intent(this, SetReminderActivity.class),
                MemoApp.setReminderRequestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == MemoApp.setReminderRequestCode){
            Long timeInMilis = (Long)data.getExtras().get(MemoApp.memoMilisExtra);

            if(timeInMilis != null){
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(timeInMilis);

                this.memo.setReminderDate(calendar);

                Log.d(TAG, "ActivityResult: " + timeInMilis);
            }

            Log.d(TAG, "ActivityResult: NULL");
        }
    }

    protected MemoViewModel getMemoViewModel(){ return this.memoViewModel; }
}
