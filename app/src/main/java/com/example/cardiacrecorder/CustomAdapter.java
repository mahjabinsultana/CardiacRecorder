package com.example.cardiacrecorder;

import android.app.Activity;

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

        dat.setText(singleMeasurement.getDate());
        tim.setText(singleMeasurement.getTime());
        sys.setText(singleMeasurement.getSystolicPressure());
        dia.setText(singleMeasurement.getDiastolicPressure());
        hr.setText(singleMeasurement.getHeartRate());
        com.setText(singleMeasurement.getComment());


        return view;
    }
}
