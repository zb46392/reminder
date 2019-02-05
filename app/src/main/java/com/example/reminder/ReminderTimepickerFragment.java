package com.example.reminder;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import java.util.Calendar;

public class ReminderTimepickerFragment extends Fragment {

    private TimePicker timePicker;
    private Integer tpHour, tpMinute;
    private final static String TAG = ReminderTimepickerFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.tpHour = null;
        this.tpMinute = null;
        return inflater.inflate(R.layout.reminder_timepicker_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.timePicker = getView().findViewById(R.id.setReminderTimePicker);
        this.timePicker.setIs24HourView(true);

        if (Build.VERSION.SDK_INT >= 23 ) {
            this.timePicker.setHour(Calendar.getInstance().getTime().getHours());
            this.timePicker.setMinute(Calendar.getInstance().getTime().getMinutes());
        } else {
            this.timePicker.setCurrentHour(Calendar.getInstance().getTime().getHours());
            this.timePicker.setCurrentMinute(Calendar.getInstance().getTime().getMinutes());
        }

        this.timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                tpHour = hourOfDay;
                tpMinute = minute;
            }
        });
    }



    public TimePicker getTimePicker() { return this.timePicker; }

    public Integer getTpHour() { return this.tpHour; }
    public Integer getTpMinute() { return this.tpMinute; }
}
