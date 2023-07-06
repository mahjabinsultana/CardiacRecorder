package com.example.cardiacrecorder;
import android.view.Gravity;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
public class SingleMeasurementList {

    List<SingleMeasurement> sm = new ArrayList<>();
    public void addData(SingleMeasurement data)
    {
        if(sm.contains(data))
        {
            throw new IllegalArgumentException();
        }
        sm.add(data);
    }
    public List<SingleMeasurement> getData()
    {
        List<SingleMeasurement> dataList = sm;
        return dataList;
    }
    public void deleteData(SingleMeasurement data)
    {
        List<SingleMeasurement> dataList = sm;
        if(dataList.contains(data)){
            sm.remove(data);
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }

    public int countData()
    {
        return sm.size();
    }

    public void addDataAt(int i, SingleMeasurement s)
    {
        sm.add(i, s);
    }

}



