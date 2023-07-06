package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class UpdateMeasurement extends AppCompatActivity {

    DatabaseReference databaseReference;
    TextView dateView;
    TextView timeView;
    EditText systolicPressureText,diastolicPressureText,heartRateText,commentText;
    Button updateButton;
    DatePickerDialog.OnDateSetListener setListener;
    TimePickerDialog timePickerDialog;
    FirebaseUser user;
    String uid;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_measurement);

        Intent intent = getIntent();
        String date = intent.getExtras().getString("date");
        String time = intent.getExtras().getString("time");
        String systolicPressure = intent.getExtras().getString("systolicPressure");
        String diastolicPressure = intent.getExtras().getString("diastolicPressure");
        String heartRate = intent.getExtras().getString("heartRate");
        String comment = intent.getExtras().getString("comment");
        String key = intent.getExtras().getString("key");
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("Records").child(uid);
        dateView = findViewById(R.id.dateId);
        timeView = findViewById(R.id.timeId);
        systolicPressureText = findViewById(R.id.systolicPressureId);
        diastolicPressureText = findViewById(R.id.diastolicPressureId);
        heartRateText = findViewById(R.id.heartRateId);
        commentText = findViewById(R.id.commentId);
        updateButton = findViewById(R.id.updateButtonId);


        dateView.setText(date);
        timeView.setText(time);
        systolicPressureText.setText(systolicPressure);
        diastolicPressureText.setText(diastolicPressure);
        heartRateText.setText(heartRate);
        commentText.setText(comment);

        // This portion gets the date
        dateView = findViewById(R.id.dateId);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateMeasurement.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                String date = day+" - "+month+" - "+year;
                dateView.setText(date);
            }
        };

        // This portion gets the time
        timeView = findViewById(R.id.timeId);
        timeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open_TimePickerDialog();
                int hourOfDay = 12;
                int minute = 12;
                boolean is24HourView = true;

                timePickerDialog = new TimePickerDialog(UpdateMeasurement.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        timeView.setText(hourOfDay+" : "+ minute);
                    }
                },hourOfDay,minute,is24HourView);
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.show();
            }
        });


        // This portion sends data to firebase



        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData(key);
            }
        });

    }

    public void updateData(String key)
    {
        String date = dateView.getText().toString().trim();

        String time = timeView.getText().toString().trim();
        String systolicPressure = systolicPressureText.getText().toString().trim();
        String diastolicPressure = diastolicPressureText.getText().toString().trim();
        String heartRate = heartRateText.getText().toString().trim();
        String comment = commentText.getText().toString().trim();
        int intSystolicPressure = -1,intDiastolicPressure=-1,intHeartRate=-1;


        // Helping user by ensuring proper data type
        if(date.matches("")){
            Toast toast = Toast.makeText(getApplicationContext(),"Click on Date Box",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
        }
        else if(time.matches("")){
            Toast toast = Toast.makeText(getApplicationContext(),"Click on Time Box",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
        }
        else if(systolicPressure.matches("")){
            Toast toast = Toast.makeText(getApplicationContext(),"Click on Systolic Pressure Box",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
        }
        else if(diastolicPressure.matches("")) {
            Toast toast = Toast.makeText(getApplicationContext(), "Click on Diastolic Pressure Box", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
        }

        else if(heartRate.matches("")){
            Toast toast = Toast.makeText(getApplicationContext(),"Click on Heart Rate Box",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
        }
        else{
            intSystolicPressure = Integer.parseInt(systolicPressure);
            intDiastolicPressure = Integer.parseInt(diastolicPressure);
            intHeartRate = Integer.parseInt(heartRate);


            if(intSystolicPressure<0||intSystolicPressure>200){
                Toast toast = Toast.makeText(getApplicationContext(),"Enter Systolic Pressure between 0 and 200",Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            }
            else if(intDiastolicPressure<0||intDiastolicPressure>200){
                Toast toast = Toast.makeText(getApplicationContext(),"Enter Diastolic Pressure between 0 and 200",Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            }
            else if(intHeartRate<0||intHeartRate>200){
                Toast toast = Toast.makeText(getApplicationContext(),"Enter Heart Rate between 0 and 200",Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            }
            else{

                SingleMeasurement singleMeasurement = new SingleMeasurement(date, time,
                        systolicPressure, diastolicPressure, heartRate, comment,key);
                databaseReference.child(key).setValue(singleMeasurement);
                Toast toast = Toast.makeText(getApplicationContext(),"Measurement updated",Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            }
        }
    }
}