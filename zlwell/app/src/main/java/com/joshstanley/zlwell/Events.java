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
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        eventScrollView = view.findViewById(R.layout.sv_events);
        LinearLayout eventsContainer = eventScrollView.findViewById.event_sv_li);
        try{
            events = generateEvents();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        for(Event e : events){
            calendarEventView = inflater.inflate(R.layout.calendar_event, container, false);
            eventDate = calendarEventView.findViewById(R.id.tv_calendar_event_date);
            eventDate.setText(e.date);
            eventItemLayout = calendarEventView.findViewById(R.id.ll_calendar_event_items);
            eventItem = (LinearLayout)inflater.inflate(R.layout.event_item, container, false);
            eventTime = eventItem.findViewById(R.id.event_time);
            eventSubject = eventItem.findViewById(R.id.event_subject);
            eventLocation = eventItem.findViewById(R.id.event_location);
            eventTime.setText(e.time);
            eventSubject.setText(e.subject);
            eventLocation.setText(e.location);
            eventItemLayout.addView(eventItem);
            eventsContainer.addView(eventItemLayout);

        }
        return view;
    }

    private String formatDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date newDate = sdf.parse(date);
        sdf = new SimpleDateFormat("dd/MMM", Locale.ENGLISH);
        return sdf.format(newDate);
    }

    private ArrayList<Event> generateEvents() throws ParseException{
        ArrayList<Event> events = new ArrayList<>();
        for (int i = 1; i < 25; i++){
            String eventDate = i + "/03/2020";
            events.add(new Event(formatDate(eventDate),"10:00 - 14:30", "Meeting +" + i, "room " + i));
        }
        return events;
    }


}
