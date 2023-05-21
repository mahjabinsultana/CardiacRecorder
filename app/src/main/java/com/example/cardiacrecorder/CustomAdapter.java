package com.example.cardiacrecorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;

public class CustomAdapter extends BaseAdapter {

    Context c;
    ArrayList<Date> date;
    ArrayList<Date> time;
    ArrayList<Integer> systolic;
    ArrayList<Integer> diastolic;
    ArrayList<Integer> heartrate;
    ArrayList<String> comment;

    public CustomAdapter(Context c, ArrayList<Date> date, ArrayList<Date> time, ArrayList<Integer> systolic, ArrayList<Integer> diastolic, ArrayList<Integer> heartrate, ArrayList<String> comment) {
        this.c = c;
        this.date = date;
        this.time = time;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.heartrate = heartrate;
        this.comment = comment;
    }

    @Override
    public int getCount() {
        return comment.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_view, parent , false);

        TextView dat, tim, sys, dia, hr, com;

        dat = view.findViewById(R.id.date);
        tim = view.findViewById(R.id.time);
        sys = view.findViewById(R.id.systolic);
        dia = view.findViewById(R.id.diastolic);
        hr = view.findViewById(R.id.heartrate);
        com = view.findViewById(R.id.comment);

        dat.setText(String.valueOf(date.get(i)));
        tim.setText(String.valueOf(time.get(i)));
        sys.setText(String.valueOf(systolic.get(i)));
        dia.setText(String.valueOf(diastolic.get(i)));
        hr.setText(String.valueOf(heartrate.get(i)));
        com.setText(String.valueOf(comment.get(i)));


        return view;
    }
}
