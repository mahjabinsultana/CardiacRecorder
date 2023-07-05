package com.example.cardiacrecorder;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.anything;

import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityUITest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testAddRecord()
    {

        onView(withId(R.id.add)).perform(click());
        onView(withId(R.id.insertmeasurement)).check(matches(isDisplayed()));

        onView(withId(R.id.dateId)).perform(ViewActions.click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2023, 9, 27));
        //onView(isAssignableFrom(DatePicker.class)).perform(setDate(2023, 10, 30));
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.timeId)).perform(ViewActions.click());
      //  onView(isAssignableFrom(DatePicker.class)).perform(setTime(12, 12));
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(12, 12));
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.systolicPressureId)).perform(ViewActions.typeText("80"));
        onView(withId(R.id.diastolicPressureId)).perform(ViewActions.typeText("80"));
        onView(withId(R.id.heartRateId)).perform(ViewActions.typeText("75"));
        onView(withId(R.id.commentId)).perform(ViewActions.typeText("comment added"));
        Espresso.pressBack();
        onView(withId(R.id.insertButtonId)).perform(click());
        /*Espresso.pressBack();
        onView(withId(R.id.mainactivity)).check(matches(isDisplayed()));
        onView(withText("25-6-2023")).check(matches(isDisplayed()));*/
        onView(withText("Measurement added")).check(matches(isDisplayed()));

    }

    public void testUpdateRecord()
    {
        // Perform a click on the first item in the ListView
        onData(anything()).inAdapterView(withId(R.id.list_view)).atPosition(0).perform(click());

        onView(withId(R.id.update)).perform(click());
        onView(withId(R.id.updatemeasurement)).check(matches(isDisplayed()));

        // checking the data first
        Espresso.onView(withId(R.id.dateId)).check(matches(withText("")));
        Espresso.onView(withId(R.id.timeId)).check(matches(withText("")));
        Espresso.onView(withId(R.id.systolicPressureId)).check(matches(withText("")));
        Espresso.onView(withId(R.id.diastolicPressureId)).check(matches(withText("")));
        Espresso.onView(withId(R.id.heartRateId)).check(matches(withText("")));
        Espresso.onView(withId(R.id.commentId)).check(matches(withText("")));


        onView(withId(R.id.dateId)).perform(ViewActions.click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2023, 9, 27));
        //onView(isAssignableFrom(DatePicker.class)).perform(setDate(2023, 10, 30));
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.timeId)).perform(ViewActions.click());
        //  onView(isAssignableFrom(DatePicker.class)).perform(setTime(12, 12));
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(12, 12));
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.systolicPressureId)).perform(ViewActions.typeText("80"));
        onView(withId(R.id.diastolicPressureId)).perform(ViewActions.typeText("80"));
        onView(withId(R.id.heartRateId)).perform(ViewActions.typeText("75"));
        onView(withId(R.id.commentId)).perform(ViewActions.typeText("comment added"));
        Espresso.pressBack();
        onView(withId(R.id.insertButtonId)).perform(click());
        /*Espresso.pressBack();
        onView(withId(R.id.mainactivity)).check(matches(isDisplayed()));
        onView(withText("25-6-2023")).check(matches(isDisplayed()));*/
        onView(withText("Measurement updated")).check(matches(isDisplayed()));
    }

    /*public void testDeleteRecord()
    {
        onData(anything()).inAdapterView(withId(R.id.list_view)).atPosition(0).perform(click());
        onView(withId(R.id.delete)).perform(click());
        onView(withText("Deleted Successfully")).check(matches(isDisplayed()));

    }*/


}
