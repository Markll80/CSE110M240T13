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

import cse110mt13.tritonprofessorraterv1.LoginActivity;
import cse110mt13.tritonprofessorraterv1.MainActivity;
import cse110mt13.tritonprofessorraterv1.R;
import cse110mt13.tritonprofessorraterv1.StartHere;

/**
 * Created by Rui Deng on 2016/3/4.
 * must enter after logging in with stored log in information
 */
@RunWith(AndroidJUnit4.class)
public class mainPageTest extends ActivityInstrumentationTestCase2<StartHere>{
    @Rule
    public ActivityTestRule<StartHere> activityTestRule =
            new ActivityTestRule<>(StartHere.class);

    public mainPageTest()
    {
        super(StartHere.class);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        getActivity();
    }

    @Test
    public void testIsDisplayedandSearch()
    {
        onView(withId(R.id.listView)).check(matches(isDisplayed()));
        onView(withId(R.id.search_B)).check(matches(isDisplayed()));
        onView(withId(R.id.search_ET)).check(matches(isDisplayed()));
        String profname = "rist";
        onView(withId(R.id.search_ET)).perform(typeText(profname), closeSoftKeyboard());
        onView(withId(R.id.search_B)).perform(click());
        onView(withText("Christine Alvarado")).check(matches(isDisplayed()));
        onView(withId(R.id.listViewSearch_page)).check(matches(isDisplayed()));
        onView(withId(R.id.search_B)).check(matches(isDisplayed()));
        onView(withId(R.id.search_ET)).check(matches(isDisplayed()));
        onView(withId(R.id.sp_AddProf_B)).check(matches(isDisplayed()));
    }

    /*@Test
    // seems that I can't make two tests in one tester file, it will cause parse error
    public void testSearch()
    {
        String profname = "rist";
        onView(withId(R.id.search_ET)).perform(typeText(profname), closeSoftKeyboard());
        onView(withId(R.id.search_B)).perform(click());
        onView(withId(R.id.listViewSearch_page)).check(matches(isDisplayed()));
    }*/
}
