package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(R.id.list_view);

        ArrayList<Date> date = new ArrayList<>();
        date.add(new Date());

        ArrayList<Date> time = new ArrayList<>();
        Date currentTime = Calendar. getInstance(). getTime();
        time.add(currentTime);

        ArrayList<Integer> systolic = new ArrayList<>();
        systolic.add(120);

        ArrayList<Integer> diastolic = new ArrayList<>();
        diastolic.add(120);

        ArrayList<Integer> heartrate = new ArrayList<>();
        heartrate.add(75);

        ArrayList<String> comment = new ArrayList<>();
        comment.add("hahhaha");

        CustomAdapter adapter = new CustomAdapter(this, date, time, systolic, diastolic, heartrate, comment);

        listview.setAdapter(adapter);


    }
}