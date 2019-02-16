package com.example.reminder;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class MemoRepository {
    private MemoDao memoDao;
    private LiveData<List<Memo>>  allMemos;

    public MemoRepository(Application application){
        MemoDB memoDB = MemoDB.getInstance(application);
        this.memoDao = memoDB.memoDao();
        this.allMemos = this.memoDao.getAll();
    }

    public void getById(){}

    public void getReminderDateById(){}

    public void insert(Memo memo) {
        new AsyncInsert(this.memoDao).execute(memo);
    }

    public void update(Memo memo) {
        new AsyncUpdate(this.memoDao).execute(memo);
    }

    public void delete(Memo memo){
        new AsyncDelete(this.memoDao).execute(memo);
    }

    public LiveData<List<Memo>> getAllMemos() {
        return allMemos;
    }

    private static class AsyncInsert extends AsyncTask<Memo, Void, Void>{
        private MemoDao memoDao;

        private AsyncInsert(MemoDao memoDao){
            this.memoDao = memoDao;
        }

        @Override
        protected Void doInBackground(Memo... memos) {
            this.memoDao.insert(memos[0]);
            return null;
        }
    }

    private static class AsyncUpdate extends AsyncTask<Memo, Void, Void>{
        private MemoDao memoDao;

        private AsyncUpdate(MemoDao memoDao){
            this.memoDao = memoDao;
        }

        @Override
        protected Void doInBackground(Memo... memos) {
            this.memoDao.update(memos[0]);
            return null;
        }
    }

    private static class AsyncDelete extends AsyncTask<Memo, Void, Void>{
        private MemoDao memoDao;

        private AsyncDelete(MemoDao memoDao){
            this.memoDao = memoDao;
        }

        @Override
        protected Void doInBackground(Memo... memos) {
            this.memoDao.delete(memos[0]);
            return null;
        }
    }
}
