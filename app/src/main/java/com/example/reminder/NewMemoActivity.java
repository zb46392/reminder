package com.example.reminder;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

public class NewMemoActivity extends BaseMemoDetailActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFabSaveMemo().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Memo mem = new Memo();
                mem.setTitle(getMemoTitle().getText().toString());
                mem.setBody(getMemoBody().getText().toString());

                new AsyncInsert().execute(mem);
            }
        });
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

            // refresh main activity...
            finish();
        }
    }
}


