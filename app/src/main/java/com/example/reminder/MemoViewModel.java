package com.example.reminder;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class MemoViewModel extends AndroidViewModel {
    private MemoRepository memoRepository;
    private LiveData<List<Memo>> liveMemos;
    public MemoViewModel(@NonNull Application application) {
        super(application);

        this.memoRepository = new MemoRepository(application);
        this.liveMemos = this.memoRepository.getAllMemos();
    }

    public void insert(Memo memo){
        this.memoRepository.insert(memo);
    }

    public void update(Memo memo){
        this.memoRepository.update(memo);
    }

    public void delete(Memo memo){
        this.memoRepository.delete(memo);
    }

    public LiveData<List<Memo>> getLiveMemos() {
        return liveMemos;
    }
}
