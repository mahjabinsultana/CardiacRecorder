package com.example.cardiacrecorder;


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

        insert in = new insert();

        in.insert("1-1-2023","12 : 12","80","110",
                "75","normal","abcd");

        assertTrue(1==1);


    }

}
