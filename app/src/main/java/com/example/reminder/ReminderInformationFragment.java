package com.example.reminder;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;

public class ReminderInformationFragment extends Fragment {

    private TextView timeText;
    private TextView dateText;
    private static Integer hour, minute;
    private static Integer day, month, year;
    private static final String TAG = ReminderInformationFragment.class.getSimpleName();

    private View.OnClickListener setReminder;
    private View.OnClickListener cancelReminder;

    ReminderComfirmation remCon;
    ReminderRejector remRej;

    public interface ReminderComfirmation{
        void confirmReminder(Calendar calendar);
    }

    public interface ReminderRejector{
        void rejectReminder();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate...");
        return inflater.inflate(R.layout.reminder_information_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreate...");
        super.onViewCreated(view, savedInstanceState);

        this.timeText = getView().findViewById(R.id.reminderTimeText);
        this.dateText = getView().findViewById(R.id.reminderDateText);

        if(this.hour != null && this.minute != null){
            this.timeText.setText(this.hour + ":" + this.minute);
        }

        if(this.day != null && this.month != null && this.year != null){
            this.dateText.setText(this.day + "." + Integer.toString(this.month + 1) + "." + this.year + ".");
        }

        this.setReminder = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(hour != null && minute != null){
                    Calendar calendar = Calendar.getInstance();

                    if(day != null && month != null && year != null){
                        calendar.set(year, month, day, hour, minute, 0);
                    } else {
                        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DAY_OF_MONTH), hour, minute);
                    }

                    Log.d(TAG, "SetReminderFab: " + calendar.getTimeInMillis());

                    remCon.confirmReminder(calendar);
                }

                Log.d(TAG, "SetReminderFab: NULL");

                remCon.confirmReminder(null);
            }
        };

        this.cancelReminder = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remRej.rejectReminder();
            }
        };


        ((FloatingActionButton)getView().findViewById(R.id.fabSetReminder)).setOnClickListener(this.setReminder);

        ((FloatingActionButton)getView().findViewById(R.id.fabCancelReminder)).setOnClickListener(this.cancelReminder);
    }


    public void setTime(Integer hour, Integer minute){
        this.hour = hour;
        this.minute = minute;
    }

    public void setDate(Integer day, Integer month, Integer year){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            this.remCon = (ReminderComfirmation) context;
            this.remRej = (ReminderRejector) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement TextClicked");
        }
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "Fragment Detached...");
        this.setReminder = null;
        this.cancelReminder = null;
        this.remCon = null;
        this.remRej = null;
        super.onDetach();
    }

}
