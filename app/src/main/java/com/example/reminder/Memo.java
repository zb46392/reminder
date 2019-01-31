package com.example.reminder;

import java.util.Calendar;

public class Memo {
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
        
        ;
    private Calendar date;
    private String title;
    private String body;
    private Period period;

    public Memo(){
        this.date = Calendar.getInstance();
        this.title = new String();
        this.body = new String();
        this.period = Period.none;
    }

    public Memo(Calendar date, String title, String body, Period period){
        this.date = date;
        this.title = title;
        this.body = body;
        this.period = period;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
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

    public String getFormattedDate(){
        String formattedDate = new String();

        formattedDate += this.date.get(Calendar.DAY_OF_MONTH) + ".";
        formattedDate += (this.date.get(Calendar.MONTH) + 1) + ".";
        formattedDate += this.date.get(Calendar.YEAR) + ". - ";
        formattedDate += this.date.get(Calendar.HOUR_OF_DAY) + ":";
        formattedDate += this.date.get(Calendar.MINUTE) + ".";

        return formattedDate;
    }
}