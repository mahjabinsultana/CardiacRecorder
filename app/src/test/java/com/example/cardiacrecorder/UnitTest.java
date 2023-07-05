package com.example.cardiacrecorder;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UnitTest {

    @Test
    public void testAddRecord()
    {
        SingleMeasurementList dataList = new SingleMeasurementList();
        SingleMeasurement singleMeasurement1 = new SingleMeasurement("2023-1-23", "12 : 12", "80",
                "80", "75", "aa", "abc");

        dataList.addData(singleMeasurement1);
        assertEquals(1, dataList.getData().size());

        SingleMeasurement singleMeasurement2 = new SingleMeasurement("2023-2-23", "12 : 12", "80",
                "80", "75", "aa", "abcd");

        dataList.addData(singleMeasurement2);
        assertEquals(2, dataList.getData().size());

        assertTrue(dataList.getData().contains(singleMeasurement1));
        assertTrue(dataList.getData().contains(singleMeasurement2));


    }

    @Test
    public void testDeleteData() {
        SingleMeasurementList dataList = new SingleMeasurementList();
        SingleMeasurement singleMeasurement1 = new SingleMeasurement("2023-1-23", "12 : 12", "80",
                "80", "75", "aa", "abc");

        dataList.addData(singleMeasurement1);
        assertEquals(1, dataList.getData().size());

        SingleMeasurement singleMeasurement2 = new SingleMeasurement("2023-2-23", "12 : 12", "80",
                "80", "75", "aa", "abcd");

        dataList.addData(singleMeasurement2);
        assertEquals(2, dataList.getData().size());

        assertTrue(dataList.getData().contains(singleMeasurement1));
        assertTrue(dataList.getData().contains(singleMeasurement2));

        dataList.deleteData(singleMeasurement1);
        assertEquals(1, dataList.getData().size());
        assertFalse(dataList.getData().contains(singleMeasurement1));

        dataList.deleteData(singleMeasurement2);
        assertEquals(0, dataList.getData().size());
        assertFalse(dataList.getData().contains(singleMeasurement2));
    }

    @Test
    public void testAddRecordException() {
        SingleMeasurementList dataList = new SingleMeasurementList();
        SingleMeasurement singleMeasurement1 = new SingleMeasurement("2023-1-23", "12 : 12", "80",
                "80", "75", "aa", "abc");

        dataList.addData(singleMeasurement1);

        assertThrows(IllegalArgumentException.class, () -> dataList.addData(singleMeasurement1));
    }

    /**
     * testing deleteData method for exceptions
     */
    @Test
    public void testDeleteRecordException() {
        SingleMeasurementList dataList = new SingleMeasurementList();
        SingleMeasurement singleMeasurement1 = new SingleMeasurement("2023-1-23", "12 : 12", "80",
                "80", "75", "aa", "abc");

        dataList.addData(singleMeasurement1);

        dataList.deleteData(singleMeasurement1);

        assertThrows(IllegalArgumentException.class, () -> dataList.deleteData(singleMeasurement1));
    }

    @Test
    public void updateTest()
    {
        SingleMeasurementList dataList = new SingleMeasurementList();
        SingleMeasurement singleMeasurement1 = new SingleMeasurement("2023-1-23", "12 : 12", "80",
                "80", "75", "aa", "abc");

        dataList.addData(singleMeasurement1);
        SingleMeasurement s = dataList.getData().get(0);
        s.setComment("new comment");

        dataList.addDataAt(0, s);

    }



}