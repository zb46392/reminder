package com.example.reminder;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class NewMemoActivity extends BaseMemoDetailActivity {
    private final static String TAG = NewMemoActivity.class.getSimpleName();

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.delete_single_memo).setEnabled(false);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void saveMemo() {
        Memo mem = new Memo();

        mem.setTitle(getMemoTitle().getText().toString());
        mem.setBody(getMemoBody().getText().toString());

        new AsyncInsert().execute(mem);
    }

    private class AsyncInsert extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            ((MemoApp)getApplication()).getMemoDB().memoDao().insert((Memo)objects[0]);

            return (Memo)objects[0];
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            Toast.makeText(getApplicationContext(), getResources().getString(R.string.newMemoAdded),
                    Toast.LENGTH_LONG).show();

            finish();
        }
    }
}


