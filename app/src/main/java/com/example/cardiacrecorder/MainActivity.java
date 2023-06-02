package com.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listview;
    Button add;
    Button delete;

    List<SingleMeasurement> sm;

    DatabaseReference dref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(R.id.list_view);


        dref = FirebaseDatabase.getInstance().getReference("Records");

        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sm= new ArrayList<>();
                for(DataSnapshot snap : snapshot.getChildren())
                {
                 //   SingleMeasurement data = snap.getValue(SingleMeasurement.class);
                     String date = snap.child("date").getValue(String.class);
                     String time = snap.child("time").getValue(String.class);
                     String systolicPressure = snap.child("systolicPressure").getValue(String.class);
                     String diastolicPressure = snap.child("diastolicPressure").getValue(String.class);
                     String heartRate = snap.child("heartRate").getValue(String.class);
                     String comment = snap.child("comment").getValue(String.class);

                     SingleMeasurement singleMeasurement = new SingleMeasurement(date, time, systolicPressure, diastolicPressure, heartRate, comment);


                     sm.add(singleMeasurement);
                }

                CustomAdapter adapter = new CustomAdapter(MainActivity.this, sm);
                listview.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        add = (Button) findViewById(R.id.add);
        delete = (Button) findViewById(R.id.delete);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), InsertMeasurement.class);
                startActivity(intent);
            }
        });

    }
}