package com.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listview;
    Button add;
    Button delete;
    Integer indexvalue;
    List<SingleMeasurement> sm;
    SingleMeasurement smd;
    String date;
    String time;
    String systolicPressure;
    String diastolicPressure;
    String heartRate;
    String comment;
    String key;
    Button update;
    DatabaseReference dref;
    FirebaseUser user;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(R.id.list_view);

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        dref = FirebaseDatabase.getInstance().getReference("Records").child(uid);

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
                     String key = snap.child("key").getValue(String.class);
                     SingleMeasurement singleMeasurement = new SingleMeasurement(date, time,
                             systolicPressure, diastolicPressure, heartRate, comment,key);


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
        update = (Button) findViewById(R.id.update);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), InsertMeasurement.class);
                startActivity(intent);
            }
        });


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                indexvalue=i;
                smd = sm.get(indexvalue);
                key=smd.getKey();
                date = smd.getDate();
                time = smd.getTime();
                systolicPressure = smd.getSystolicPressure();
                diastolicPressure = smd.getDiastolicPressure();
                heartRate = smd.getHeartRate();
                comment = smd.getComment();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sm.remove(smd);
                CustomAdapter adapter=new CustomAdapter(MainActivity.this,sm);
                listview.setAdapter(adapter);
                FirebaseDatabase.getInstance().getReference().child("Records").child(uid).child(key).removeValue();

                Toast toast = Toast.makeText(getApplicationContext(),"Deleted Successfully",Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UpdateMeasurement.class);
                intent.putExtra("date", date );
                intent.putExtra("time", time );
                intent.putExtra("systolicPressure",systolicPressure );
                intent.putExtra("diastolicPressure", diastolicPressure );
                intent.putExtra("heartRate", heartRate );
                intent.putExtra("comment", comment );
                intent.putExtra("key", key );
                startActivity(intent);
            }
        });

    }
}