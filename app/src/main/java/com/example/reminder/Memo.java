package com.example.reminder;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.Calendar;


@Entity(tableName = "Memos")
public class Memo implements Serializable {
    public static enum Period {
        none(MemoApp.getLocalResources().getString(R.string.none)),
        daily(MemoApp.getLocalResources().getString(R.string.daily)),
        monthly(MemoApp.getLocalResources().getString(R.string.monthly)),
        yearly(MemoApp.getLocalResources().getString(R.string.yearly));
        private final String period;

        Period(String period) {
            this.period = period;
        }


        @Override
        public String toString() {
            return this.period;
        }
    }

    @PrimaryKey(autoGenerate = true)
    private Integer id;
    @ColumnInfo(name="create_date")
    private Calendar createDate;
    @ColumnInfo(name="reminder_date")
    private Calendar reminderDate;
    private String title;
    private String body;
    private Period period;

    public Memo(){
        this.createDate = Calendar.getInstance();
        this.reminderDate = null;
        this.title = new String();
        this.body = new String();
        this.period = Period.none;
    }

    @Ignore
    public Memo(String title, String body, Period period, Calendar reminderDate){
        this.createDate = Calendar.getInstance();
        this.title = title;
        this.body = body;
        this.period = period;
        this.reminderDate = reminderDate;
    }

    public Integer getId() {return this.id;}

    public void setId(Integer id){ this.id = id;}

    public Calendar getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Calendar getReminderDate(){ return this.reminderDate; }

    public void setReminderDate(Calendar reminderDate) { this.reminderDate = reminderDate; }

    private String getFormattedDate(Calendar date){
        if(date != null) {
            String formattedDate = new String();

            formattedDate += date.get(Calendar.DAY_OF_MONTH) + ".";
            formattedDate += (date.get(Calendar.MONTH) + 1) + ".";
            formattedDate += date.get(Calendar.YEAR) + ". - ";
            formattedDate += date.get(Calendar.HOUR_OF_DAY) + ":";
            formattedDate += date.get(Calendar.MINUTE) + ".";

            return formattedDate;
        } else {
            return "-";
        }
    }

    public String getFormattedCreateDate(){
        return this.getFormattedDate(this.createDate);
    }

    public String getFormattedReminderDate(){
        return this.getFormattedDate(this.reminderDate);
    }
}