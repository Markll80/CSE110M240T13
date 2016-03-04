package cse110mt13.tritonprofessorraterv1.espressoTest;

import android.app.Application;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ApplicationTestCase;
import android.test.ViewAsserts;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import android.test.ActivityInstrumentationTestCase2;

import cse110mt13.tritonprofessorraterv1.MainActivity;
import cse110mt13.tritonprofessorraterv1.R;

/**
 * Created by Rui Deng on 2016/3/4.
 * must enter this test from main activity
 */
public class mainPageTest2 extends ActivityInstrumentationTestCase2<MainActivity>{
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    public mainPageTest2()
    {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        getActivity();
    }

    @Test
    public void testAddProfAndCancel()
    {
        onView(withId(R.id.listView)).check(matches(isDisplayed()));
        onView(withId(R.id.search_B)).check(matches(isDisplayed()));
        onView(withId(R.id.search_ET)).check(matches(isDisplayed()));
        onView(withId(R.id.sp_AddProf_B)).perform(click());
        onView(withId(R.id.rating)).check(matches(isDisplayed()));
        onView(withId(R.id.easiness)).check(matches(isDisplayed()));
        onView(withId(R.id.clarity)).check(matches(isDisplayed()));
        onView(withId(R.id.helpfulness)).check(matches(isDisplayed()));
        onView(withId(R.id.ap_cancel_B)).perform(click());
        onView(withText("Click yes to cancel!")).check(matches(isDisplayed()));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.listView)).check(matches(isDisplayed()));
        onView(withId(R.id.search_B)).check(matches(isDisplayed()));
        onView(withId(R.id.search_ET)).check(matches(isDisplayed()));
    }
}
