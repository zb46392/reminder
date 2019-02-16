package com.example.reminder;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import java.util.Calendar;

public class SetReminderActivity extends AppCompatActivity implements
        ReminderInformationFragment.ReminderConfirmation, ReminderInformationFragment.ReminderRejection{

    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private ReminderInformationFragment infoFrag;
    private ReminderTimepickerFragment timeFrag;
    private ReminderDatepickerFragment dateFrag;
    private Calendar calendar;

    private static Boolean isReminderConfirmed = false;
    private final static String TAG = SetReminderActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_set_reminder);

        if(savedInstanceState == null){
            this.infoFrag = new ReminderInformationFragment();
            this.timeFrag = new ReminderTimepickerFragment();
            this.dateFrag = new ReminderDatepickerFragment();
            this.calendar = Calendar.getInstance();
            this.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHolder, this.infoFrag).
                    commit();
        }

        //setSupportActionBar((Toolbar) findViewById(R.id.setReminderToolbar));

        this.tabLayout = findViewById(R.id.setReminderTabs);

        this.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                switch (tab.getPosition()){
                    case 0:
                        if(timeFrag.getTpHour() != null && timeFrag.getTpMinute() != null){
                            infoFrag.setTime(timeFrag.getTpHour(), timeFrag.getTpMinute());
                        }

                        if(dateFrag.getDpDay() != null && dateFrag.getDpMonth() != null && dateFrag.getDpYear() != null){
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

/*
                switch(tab.getPosition()){
                    case 0:
                        if(timeFrag.getTpHour() != null && timeFrag.getTpMinute() != null){
                            infoFrag.setTime(timeFrag.getTpHour(), timeFrag.getTpMinute());
                        }

                        if(dateFrag.getDpDay() != null && dateFrag.getDpMonth() != null && dateFrag.getDpYear() != null){
                            infoFrag.setDate(dateFrag.getDpDay(), dateFrag.getDpMonth(), dateFrag.getDpYear());
                        }

                        if (infoFrag.isAdded()) {
                            ft.show(infoFrag);
                        } else {
                            ft.add(R.id.fragmentHolder, infoFrag);
                        }

                        if (timeFrag.isAdded()) { ft.hide(timeFrag); }

                        if (dateFrag.isAdded()) { ft.hide(dateFrag); }
                        break;
                    case 1:
                        if (timeFrag.isAdded()) {
                            ft.show(timeFrag);
                        } else {
                            ft.add(R.id.fragmentHolder, timeFrag);
                        }

                        if (infoFrag.isAdded()) { ft.hide(infoFrag); }

                        if (dateFrag.isAdded()) { ft.hide(dateFrag); }
                        break;
                    case 2:
                        if (dateFrag.isAdded()) {
                            ft.show(dateFrag);
                        } else {
                            ft.add(R.id.fragmentHolder, dateFrag);
                        }

                        if (timeFrag.isAdded()) { ft.hide(timeFrag); }

                        if (infoFrag.isAdded()) { ft.hide(infoFrag); }
                        break;
                }
*/
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


        if(item.getItemId() == R.id.set_single_reminder){

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
        intent.putExtra(MemoApp.memoMilisExtra, (Long)null);

        this.setResult(MemoApp.setReminderRequestCode, intent);
        finish();
    }


    /*
    public static class PlaceholderFragment extends Fragment{

        private static final String ARG_SECTION_NUMBER = "section_number";

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.reminder_information_fragment, container, false);

            return rootView;
        }
    }

    private class SectionsPagerAdapter extends FragmentPagerAdapter{

        public SectionsPagerAdapter(FragmentManager fm) { super(fm); }

        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0:
                    return new ReminderInformationFragment();
                case 1:
                    return new ReminderTimepickerFragment();
                case 2:
                    return new ReminderDatepickerFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
    */
}
