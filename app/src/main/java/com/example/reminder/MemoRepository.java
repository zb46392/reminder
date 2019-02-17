package com.example.reminder;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.util.List;

public class MemoRepository {
    private MemoDao memoDao;
    private LiveData<List<Memo>> allMemos;

    public MemoRepository(Application application) {
        MemoDB memoDB = MemoDB.getInstance(application);
        this.memoDao = memoDB.memoDao();
        this.allMemos = this.memoDao.getAll();
    }

    public void getById() {
    }

    public void getReminderDateById() {
    }

    public void insert(Memo memo, Context context) {
        new AsyncInsert(this.memoDao, context).execute(memo);
    }

    public void update(Memo memo) {
        new AsyncUpdate(this.memoDao).execute(memo);
    }

    public void delete(Memo memo) {
        new AsyncDelete(this.memoDao).execute(memo);
    }

    public LiveData<List<Memo>> getAllMemos() {
        return allMemos;
    }

    private static class AsyncInsert extends AsyncTask<Memo, Void, Long> {
        private MemoDao memoDao;
        private Memo memo;
        private Context context;

        private AsyncInsert(MemoDao memoDao, Context context) {
            this.memoDao = memoDao;
            this.context = context;
        }

        @Override
        protected Long doInBackground(Memo... memos) {
            this.memo = memos[0];
            return this.memoDao.insert(this.memo);
        }

        @Override
        protected void onPostExecute(Long memoId) {
            super.onPostExecute(memoId);

            this.memo.setId(memoId.intValue());

            Intent intent = new Intent(MemoInsertedReceiver.INTENT_NAME);
            intent.putExtra(MemoApp.memoExtra, this.memo);

            this.context.sendBroadcast(intent);
        }
    }

    private static class AsyncUpdate extends AsyncTask<Memo, Void, Void> {
        private MemoDao memoDao;

        private AsyncUpdate(MemoDao memoDao) {
            this.memoDao = memoDao;
        }

        @Override
        protected Void doInBackground(Memo... memos) {
            this.memoDao.update(memos[0]);
            return null;
        }
    }

    private static class AsyncDelete extends AsyncTask<Memo, Void, Void> {
        private MemoDao memoDao;

        private AsyncDelete(MemoDao memoDao) {
            this.memoDao = memoDao;
        }

        @Override
        protected Void doInBackground(Memo... memos) {
            this.memoDao.delete(memos[0]);
            return null;
        }
    }
}
