package cse110mt13.tritonprofessorraterv1.espressoTest;

import android.app.Application;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ApplicationTestCase;
import android.test.ViewAsserts;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.anything;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import android.test.ActivityInstrumentationTestCase2;

import cse110mt13.tritonprofessorraterv1.ProfPage;
import cse110mt13.tritonprofessorraterv1.R;
import cse110mt13.tritonprofessorraterv1.StartHere;

/**
 * Created by Rui Deng on 2016/3/4.
 * must enter after logging in with stored log in information
 */
@RunWith(AndroidJUnit4.class)
public class profPageTest extends ActivityInstrumentationTestCase2<StartHere>{
    @Rule
    public ActivityTestRule<StartHere> activityTestRule =
            new ActivityTestRule<>(StartHere.class);

    public profPageTest()
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
    public void testProfPageAndGoToAddComment()
    {
        onView(withId(R.id.listView)).check(matches(isDisplayed()));
        onView(withId(R.id.search_B)).check(matches(isDisplayed()));
        onView(withId(R.id.search_ET)).check(matches(isDisplayed()));
        String profname = "rist";
        onView(withId(R.id.search_ET)).perform(typeText(profname), closeSoftKeyboard());
        onView(withId(R.id.search_B)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listViewSearch_page)).atPosition(0).perform(click());
        onView(withText("Christine Alvarado")).check(matches(isDisplayed()));
        onView(withId(R.id.Prof_Ess)).check(matches(isDisplayed()));
        onView(withId(R.id.Prof_Hlp)).check(matches(isDisplayed()));
        onView(withId(R.id.Prof_Clt)).check(matches(isDisplayed()));
    }
}
