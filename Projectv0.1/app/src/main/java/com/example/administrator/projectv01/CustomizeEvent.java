package com.example.administrator.projectv01;

/**
 * Created by Administrator on 2017-10-18.
 */

public class CustomizeEvent
{
    private String eventName, date, detail;

    public CustomizeEvent(String eventName, String date, String detail) {
        this.eventName = eventName;
        this.date = date;
        this.detail = detail;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
