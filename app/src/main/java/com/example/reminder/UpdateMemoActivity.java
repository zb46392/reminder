package com.example.reminder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

public class UpdateMemoActivity extends BaseMemoDetailActivity{
    private static final String TAG = UpdateMemoActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMemo((Memo)getIntent().getExtras().getSerializable(MemoApp.memoExtra));

        this.populateTextView();
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

        getMemoViewModel().update(getMemo());

        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "DESTROYED...");
    }
}
