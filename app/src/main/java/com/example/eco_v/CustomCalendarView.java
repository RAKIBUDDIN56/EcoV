package com.example.eco_v;



import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;


public class CustomCalendarView extends LinearLayout {
    ImageButton previousBtn,nextBtn;
    TextView currentDate;
    GridView gridView;
    private static final int MAX_CALENDAR_DAYS=42;
    Calendar calendar=Calendar.getInstance(Locale.ENGLISH);
    Context context;
    SimpleDateFormat dateFormat=new SimpleDateFormat("MMMM yyyy",Locale.ENGLISH);
    SimpleDateFormat monthFormat=new SimpleDateFormat("MMMM",Locale.ENGLISH);
    SimpleDateFormat yearFormat=new SimpleDateFormat("yyyy",Locale.ENGLISH);
    SimpleDateFormat eventDateFormate=new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);

    MyGridAdapter myGridAdapter;
    AlertDialog alertDialog;
    List<Date> dates=new ArrayList<>();
    List<Events> eventList=new ArrayList<>();
    DBOpenHelper dbOpenHelper;

    public CustomCalendarView(Context context)
    {
        super(context);
    }

    public CustomCalendarView( final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        InitializeLayout();
        SetUpCalendar();

        previousBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.add(Calendar.MONTH,-1);
                SetUpCalendar();

            }
        });
        nextBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.add(Calendar.MONTH,1);
                SetUpCalendar();
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setCancelable(true);
                final View addView=LayoutInflater.from(parent.getContext()).inflate(R.layout.add_newevent_layout,null);
                final EditText EventName=addView.findViewById(R.id.eventname);
                final TextView EventTime=addView.findViewById(R.id.eventtime);
                ImageButton SetTime=addView.findViewById(R.id.seteventtime);
                Button AddEvent=addView.findViewById(R.id.addevent);
                SetTime.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar calendar=Calendar.getInstance();
                        int hours=calendar.get(Calendar.HOUR_OF_DAY);
                        int minuts=calendar.get(Calendar.MINUTE);
                        TimePickerDialog timePickerDialog =new TimePickerDialog(addView.getContext(), R.style.Theme_AppCompat_Dialog,
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                        Calendar c=Calendar.getInstance();
                                        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                                        c.set(Calendar.MINUTE,minute);
                                        c.setTimeZone(TimeZone.getDefault());
                                        SimpleDateFormat hformate=new SimpleDateFormat("K:mm a",Locale.ENGLISH);
                                        String event_Time=hformate.format(c.getTime());
                                        EventTime.setText(event_Time);

                                    }
                                },hours,minuts,false);
                        timePickerDialog.show();
                    }
                });
                final String date=eventDateFormate.format(dates.get(position));
                final String month=monthFormat.format(dates.get(position));
                final String year=yearFormat.format(dates.get(position));

                AddEvent.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SaveEvent(EventName.getText().toString(),EventTime.getText().toString(),date,month,year);
                        SetUpCalendar();
                        alertDialog.dismiss();
                    }
                });

                builder.setView(addView);
                alertDialog=builder.create();
                alertDialog.show();
            }
        });

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String date=eventDateFormate.format(dates.get(position));

                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setCancelable(true);
                View showView=LayoutInflater.from(parent.getContext()).inflate(R.layout.show_events_layout,null);
                RecyclerView recyclerView=showView.findViewById(R.id.EventsRV);
                RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(showView.getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
                EventRecyclerAdapter eventRecyclerAdapter=new EventRecyclerAdapter(showView.getContext(),CollectEventByDate(date));
                recyclerView.setAdapter(eventRecyclerAdapter);
                eventRecyclerAdapter.notifyDataSetChanged();

                builder.setView(showView);
                alertDialog=builder.create();
                alertDialog.show();
                alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        SetUpCalendar();
                    }
                });



                return true;
            }
        });

    }

    private ArrayList<Events> CollectEventByDate(String date){
        ArrayList<Events> arrayList=new ArrayList<>();
        dbOpenHelper=new DBOpenHelper(context);
        SQLiteDatabase database=dbOpenHelper.getReadableDatabase();
        Cursor cursor=dbOpenHelper.ReadEvent(date,database);
        while (cursor.moveToNext()){
            String event=cursor.getString(cursor.getColumnIndex(DBStructure.EVENT));
            String time=cursor.getString(cursor.getColumnIndex(DBStructure.TIME));
            String Date=cursor.getString(cursor.getColumnIndex(DBStructure.DATE));
            String month=cursor.getString(cursor.getColumnIndex(DBStructure.MONTH));
            String Year=cursor.getString(cursor.getColumnIndex(DBStructure.YEAR));
            Events events=new Events(event,time,Date,month,Year);
            arrayList.add(events);

        }
        cursor.close();
        dbOpenHelper.close();

        return arrayList;
    }

    public CustomCalendarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, Context context1) {
        super(context, attrs, defStyleAttr);

    }

    private void SaveEvent(String event,String time,String date,String month,String year){
        dbOpenHelper=new DBOpenHelper(context);
        SQLiteDatabase database=dbOpenHelper.getWritableDatabase();
        dbOpenHelper.SaveEvent(event,time,date,month,year,database);
        dbOpenHelper.close();
        Toast.makeText(context, "Event Saved", Toast.LENGTH_SHORT).show();
    }

    private void InitializeLayout(){
        LayoutInflater inflater=(LayoutInflater)context.getSystemService((context.LAYOUT_INFLATER_SERVICE));
        View view=inflater.inflate(R.layout.calendar_layout,this);
        nextBtn=view.findViewById(R.id.nextBtn);
        previousBtn=view.findViewById(R.id.previousBtn);
        currentDate=view.findViewById(R.id.currentDate);
        gridView=view.findViewById(R.id.gridView);
    }

    private void SetUpCalendar(){
        String CurrentDate=dateFormat.format(calendar.getTime());
        currentDate.setText(CurrentDate);
        dates.clear();
        Calendar monthCalendar=(Calendar) calendar.clone();
        monthCalendar.set(Calendar.DAY_OF_MONTH,1);
        int FirstDAyOfMonth=monthCalendar.get(Calendar.DAY_OF_WEEK)-1;
        monthCalendar.add(Calendar.DAY_OF_MONTH,-FirstDAyOfMonth);
        CollectEventsPerMonth(monthFormat.format(calendar.getTime()),yearFormat.format(calendar.getTime()));

        while (dates.size() < MAX_CALENDAR_DAYS){
            dates.add(monthCalendar.getTime());
            monthCalendar.add(Calendar.DAY_OF_MONTH,1);
        }
        myGridAdapter=new MyGridAdapter(context,dates,calendar,eventList);
        gridView.setAdapter(myGridAdapter);

    }

    private void CollectEventsPerMonth(String Month,String year){
        eventList.clear();
        dbOpenHelper=new DBOpenHelper(context);
        SQLiteDatabase database=dbOpenHelper.getReadableDatabase();
        Cursor cursor=dbOpenHelper.ReadEventPerMonth(Month,year,database);
        while (cursor.moveToNext()){
            String event=cursor.getString(cursor.getColumnIndex(DBStructure.EVENT));
            String time=cursor.getString(cursor.getColumnIndex(DBStructure.TIME));
            String date=cursor.getString(cursor.getColumnIndex(DBStructure.DATE));
            String month=cursor.getString(cursor.getColumnIndex(DBStructure.MONTH));
            String Year=cursor.getString(cursor.getColumnIndex(DBStructure.YEAR));
            Events events=new Events(event,time,date,month,Year);
            eventList.add(events);
        }
        cursor.close();
        dbOpenHelper.close();
    }
}


