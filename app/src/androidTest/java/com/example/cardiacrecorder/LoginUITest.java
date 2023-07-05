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

public class LoginUITest {
    @Rule
    public ActivityScenarioRule<SignUp> activityRule =
            new ActivityScenarioRule<>(SignUp.class);

    @Test
    public void testLogin()
    {
        onView(withId(R.id.signinactivity)).check(matches(isDisplayed()));
        onView(withId(R.id.emailLogin)).perform(ViewActions.typeText("user@gmail.com"));
        onView(withId(R.id.passwordLogin)).perform(ViewActions.typeText("user"));
        onView(withId(R.id.signinButton)).perform(click());
        onView(withId(R.id.mainactivity)).check(matches(isDisplayed()));
    }
}
