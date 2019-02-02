package com.example.reminder;

import android.arch.persistence.room.TypeConverter;

import java.util.Calendar;

public class MemoTypeConverters {

    @TypeConverter
    public Long convertCalendarToLong(Calendar calendar){
        if(calendar != null) {
            return calendar.getTimeInMillis();
        } else {
            return null;
        }
    }

    @TypeConverter
    public Calendar convertLongToCalendar(Long timeInMilis){
        if(timeInMilis != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(timeInMilis);
            return calendar;
        } else {
            return null;
        }
    }

    @TypeConverter
    public Integer convertMemoPeriodToInt(Memo.Period period){

        switch (period){
            case daily:
                return 1;
            case monthly:
                return 2;
            case yearly:
                return 3;
            case none:
            default:
                return 0;
        }
    }

    @TypeConverter
    public Memo.Period convertIntToMemoPeriod(Integer periodInt){
        switch (periodInt){
            case 1:
                return Memo.Period.daily;
            case 2:
                return Memo.Period.monthly;
            case 3:
                return Memo.Period.yearly;
            case 0:
            default:
                return Memo.Period.none;
        }
    }
}
