package com.example.reminder;

import java.util.Date;

public class Memo {
    public static enum Period {none, daily, monthly, yearly};
    private Date date;
    private String title;
    private String body;
    private Period period;

    public Memo(){
        this.date = new Date();
        this.title = new String();
        this.body = new String();
        this.period = Period.none;
    }

    public Memo(Date date, String title, String body, Period period){
        this.date = date;
        this.title = title;
        this.body = body;
        this.period = period;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
}