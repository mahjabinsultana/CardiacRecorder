package com.example.cardiacrecorder;

import android.app.Activity;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.List;

public class CustomAdapter extends ArrayAdapter {

    private Activity c;
    List<SingleMeasurement> sm;
    Integer s, d, h;

    public CustomAdapter(Activity c, List<SingleMeasurement> sm) {
        super(c, R.layout.list_view,sm);
        this.c = c;
        this.sm = sm;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = c.getLayoutInflater();
        View view = inflater.inflate(R.layout.list_view, null , true);

        TextView dat, tim, sys, dia, hr, com;

        dat = view.findViewById(R.id.date);
        tim = view.findViewById(R.id.time);
        sys = view.findViewById(R.id.systolic);
        dia = view.findViewById(R.id.diastolic);
        hr = view.findViewById(R.id.heartrate);
        com = view.findViewById(R.id.comment);

        SingleMeasurement singleMeasurement = sm.get(position);

        s = Integer.parseInt(singleMeasurement.getSystolicPressure());
        d = Integer.parseInt(singleMeasurement.getDiastolicPressure());
        h = Integer.parseInt(singleMeasurement.getHeartRate());
        dat.setText(singleMeasurement.getDate());
        tim.setText(singleMeasurement.getTime());
        sys.setText(singleMeasurement.getSystolicPressure());
        if(s<90 || s>140)sys.setTextColor(Color.parseColor("#800000"));
        dia.setText(singleMeasurement.getDiastolicPressure());
        if(s<60 || s>90)dia.setTextColor(Color.parseColor("#800000"));
        hr.setText(singleMeasurement.getHeartRate());
        if(h<60 || s>100)hr.setTextColor(Color.parseColor("#800000"));
        com.setText(singleMeasurement.getComment());


        return view;
    }


}
