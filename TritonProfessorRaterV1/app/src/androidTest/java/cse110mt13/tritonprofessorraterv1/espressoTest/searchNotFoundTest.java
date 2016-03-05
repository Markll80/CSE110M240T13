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
import cse110mt13.tritonprofessorraterv1.R;
import cse110mt13.tritonprofessorraterv1.StartHere;
import cse110mt13.tritonprofessorraterv1.MainActivity;
import java.lang.Thread;

/**
 * Created by Rui Deng on 2016/3/4.
 * must enter after logging in with stored log in information
 */
@RunWith(AndroidJUnit4.class)
public class searchNotFoundTest extends ActivityInstrumentationTestCase2<StartHere>{
    @Rule
    public ActivityTestRule<StartHere> activityTestRule =
            new ActivityTestRule<>(StartHere.class);

    public searchNotFoundTest()
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
    public void NotFoundTest()
    {
        onView(withId(R.id.listView)).check(matches(isDisplayed()));
        onView(withId(R.id.search_B)).check(matches(isDisplayed()));
        onView(withId(R.id.search_ET)).check(matches(isDisplayed()));
        String profname = "gewjigo";
        onView(withId(R.id.search_ET)).perform(typeText(profname), closeSoftKeyboard());
        onView(withId(R.id.search_B)).perform(click());

        try
        {
            Thread.sleep(1000);
        }
        catch(InterruptedException e)
        {

        }

        onView(withId(R.id.searchString_TV)).check(matches(isDisplayed()));
        onView(withId(R.id.search_B)).check(matches(isDisplayed()));
        onView(withId(R.id.search_ET)).check(matches(isDisplayed()));
        onView(withId(R.id.textView6)).check(matches(isDisplayed()));
        onView(withId(R.id.textView7)).check(matches(isDisplayed()));
        onView(withId(R.id.add_Prof_B)).perform(click());

        try
        {
            Thread.sleep(2000);
        }
        catch(InterruptedException e)
        {

        }

        onView(withId(R.id.rating)).check(matches(isDisplayed()));
        onView(withId(R.id.easiness)).check(matches(isDisplayed()));
        onView(withId(R.id.clarity)).check(matches(isDisplayed()));
        onView(withId(R.id.helpfulness)).check(matches(isDisplayed()));
    }
}
