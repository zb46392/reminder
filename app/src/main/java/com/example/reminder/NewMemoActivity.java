package com.example.reminder;

import android.view.Menu;

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

        getMemoViewModel().insert(mem);

        finish();
    }
}


