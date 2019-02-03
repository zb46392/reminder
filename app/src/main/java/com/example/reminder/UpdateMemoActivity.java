package com.example.reminder;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class UpdateMemoActivity extends BaseMemoDetailActivity{
    private Memo memo;
    private static final String TAG = UpdateMemoActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new AsyncMemoLoader().execute(getIntent().getExtras().get(MemoApp.memoIdExtra));
    }

    private void populateTextView(){
        getMemoTitle().setText(this.memo.getTitle());
        getMemoBody().setText(this.memo.getBody());

        Log.d(TAG, "TextView populated...Memo ID: " + this.memo.getId());
    }

    @Override
    protected void saveMemo() {
        this.memo.setTitle(getMemoTitle().getText().toString());
        this.memo.setBody(getMemoBody().getText().toString());

        new AsyncMemoUpdater().execute(this.memo);
    }

    private void setMemo(Memo memo) { this.memo = memo; }

    private class AsyncMemoLoader extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            return ((MemoApp)getApplication()).getMemoDB().memoDao().getById((Integer)objects[0]);
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            Log.d(TAG, ((Memo)o).getTitle() + " : " + ((Memo)o).getBody());

            setMemo((Memo)o);
            populateTextView();
        }
    }

    private class AsyncMemoUpdater extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {


            ((MemoApp)getApplication()).getMemoDB().memoDao().update((Memo)objects[0]);

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            Toast.makeText(getApplicationContext(), getResources().getString(R.string.memoUpdated),
                    Toast.LENGTH_LONG).show();

            finish();
        }
    }
}
