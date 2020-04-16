package com.joshstanley.zlwell;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.EventLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Events extends Fragment {


    public Events() {
        // Required empty public constructor
    }

    private ScrollView eventScrollView;
    private View calendarScrollView;
    private LinearLayout eventItemLayout;
    private TextView eventDate;
    private LinearLayout eventItem;
    private TextView eventTime;
    private TextView eventSubject;
    private TextView eventLocation;
    private CalendarView calendar;

    ArrayList<Event> events;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_events, container, false);
    }

    private ArrayList<Event> generateEvents() throws ParseException{
        ArrayList<Event> events = new ArrayList<>();
        for (int i = 1; i < 25; i++){
            String eventDate = i + "/03/2020";
            events.add(new Event(formatDate(eventDate),"10:00 - 14:30", "Meeting +" + i, "room " + i));
        }
        return events;
    }

    private String formatDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date newDate = sdf.parse(date);
        sdf = new SimpleDateFormat("dd/MMM", Locale.ENGLISH);
        return sdf.format(newDate);
    }
}
