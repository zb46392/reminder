package com.example.reminder;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Calendar;

public class SetReminderActivity extends AppCompatActivity implements
        ReminderInformationFragment.ReminderConfirmation, ReminderInformationFragment.ReminderRejection {

    private TabLayout tabLayout;
    private ReminderInformationFragment infoFrag;
    private ReminderTimepickerFragment timeFrag;
    private ReminderDatepickerFragment dateFrag;
    private Calendar calendar;

    private final static String TAG = SetReminderActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_set_reminder);

        if (savedInstanceState == null) {
            this.infoFrag = new ReminderInformationFragment();
            this.timeFrag = new ReminderTimepickerFragment();
            this.dateFrag = new ReminderDatepickerFragment();
            this.calendar = Calendar.getInstance();
            this.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHolder, this.infoFrag).
                    commit();
        }

        this.tabLayout = findViewById(R.id.setReminderTabs);

        this.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                switch (tab.getPosition()) {
                    case 0:
                        if (timeFrag.getTpHour() != null && timeFrag.getTpMinute() != null) {
                            infoFrag.setTime(timeFrag.getTpHour(), timeFrag.getTpMinute());
                        }

                        if (dateFrag.getDpDay() != null && dateFrag.getDpMonth() != null && dateFrag.getDpYear() != null) {
                            infoFrag.setDate(dateFrag.getDpDay(), dateFrag.getDpMonth(), dateFrag.getDpYear());
                        }
                        ft.replace(R.id.fragmentHolder, infoFrag);
                        break;
                    case 1:
                        ft.replace(R.id.fragmentHolder, timeFrag);
                        break;
                    case 2:
                        ft.replace(R.id.fragmentHolder, dateFrag);
                        break;
                }

                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.d(TAG, "tabUnselected : " + tab.getText());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.d(TAG, "tabReselected : " + tab.getText());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_set_reminder, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == R.id.set_single_reminder) {

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void confirmReminder(Calendar calendar) {

        Intent intent = new Intent(SetReminderActivity.this, BaseMemoDetailActivity.class);
        if (calendar != null) {
            intent.putExtra(MemoApp.memoMilisExtra, calendar.getTimeInMillis());
        } else {
            intent.putExtra(MemoApp.memoMilisExtra, (Long) null);
        }
        Log.d(TAG, "Reminder confirmed: " + intent.getExtras().get(MemoApp.memoMilisExtra));

        this.setResult(MemoApp.setReminderRequestCode, intent);

        finish();

    }

    @Override
    public void rejectReminder() {
        Intent intent = new Intent(SetReminderActivity.this, BaseMemoDetailActivity.class);
        intent.putExtra(MemoApp.memoMilisExtra, (Long) null);

        this.setResult(MemoApp.setReminderRequestCode, intent);
        finish();
    }

}
