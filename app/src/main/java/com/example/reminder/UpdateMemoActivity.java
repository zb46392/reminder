package com.example.reminder;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class UpdateMemoActivity extends BaseMemoDetailActivity{
    private static final String TAG = UpdateMemoActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new AsyncMemoLoader().execute(getIntent().getExtras().get(MemoApp.memoIdExtra));
    }

    private void populateTextView(){
        getMemoTitle().setText(getMemo().getTitle());
        getMemoBody().setText(getMemo().getBody());

        Log.d(TAG, "TextView populated...Memo ID: " + getMemo().getId());
    }

    @Override
    protected void saveMemo() {
        getMemo().setTitle(getMemoTitle().getText().toString());
        getMemo().setBody(getMemoBody().getText().toString());

        new AsyncMemoUpdater().execute(getMemo());
    }

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "DESTROYED...");
    }
}
