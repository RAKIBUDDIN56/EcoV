package com.example.eco_v;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.Toast;

public class Calender extends AppCompatActivity {

    CustomCalendarView customCalendarView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Calendar");
        actionBar.setDisplayHomeAsUpEnabled(true);

        customCalendarView=findViewById(R.id.custom_calendar_view);




    }
}








