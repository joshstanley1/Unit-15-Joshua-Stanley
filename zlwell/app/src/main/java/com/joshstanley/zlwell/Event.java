package com.joshstanley.zlwell;

public class Event {

    String date;
    String time;
    String subject;
    String location;
    String details;

    public Event(String date, String time, String subject, String location, String details) {
        this.date = date;
        this.time = time;
        this.subject = subject;
        this.location = location;
        this.details = details;
    }

    public Event(String date, String time, String subject, String location) {
        this.date = date;
        this.time = time;
        this.subject = subject;
        this.location = location;
    }
}
