package com.example.cardiacrecorder;

import android.view.Gravity;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class insert {

    FirebaseUser user;
    String uid;

    List<SingleMeasurement> sm= new ArrayList<>();
    public void insert(String date, String time, String systolicPressure, String diastolicPressure,
                       String heartRate, String comment,String key)
    {
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Records").child(uid);
        SingleMeasurement singleMeasurement = new SingleMeasurement(date, time,
                systolicPressure, diastolicPressure, heartRate, comment,key);
        sm.add(singleMeasurement);
        databaseReference.child(key).setValue(singleMeasurement);

    }

}
