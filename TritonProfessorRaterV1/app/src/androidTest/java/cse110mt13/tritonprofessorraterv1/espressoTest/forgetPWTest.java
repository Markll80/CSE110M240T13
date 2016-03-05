package cse110mt13.tritonprofessorraterv1.espressoTest;

import android.app.Application;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ApplicationTestCase;
import android.test.ViewAsserts;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
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
public class forgetPWTest extends ActivityInstrumentationTestCase2<StartHere>{
    @Rule
    public ActivityTestRule<StartHere> activityTestRule =
            new ActivityTestRule<>(StartHere.class);

    public forgetPWTest()
    {
        super(StartHere.class);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        getActivity();
    }

    public void logOut()
    {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Logout")).perform(click());
    }

    public void testForgetPWPage()
    {
        onView(withId(R.id.forgetPW)).perform(click());
        onView(withId(R.id.forget_link_login)).check(matches(isDisplayed()));
        onView(withId(R.id.forget_link_signup)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_findUser)).check(matches(isDisplayed()));
    }

    public void backAndLogIn()
    {
        onView(withId(R.id.forget_link_login)).perform(click());
        String username = "admin";
        String password = "123456";
        onView(withId(R.id.input_email)).perform(typeText(username),closeSoftKeyboard());
        onView(withId(R.id.input_password)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());
    }

    @Test
    public void testForgetPWandLogIn()
    {
        logOut();
        try
        {
            Thread.sleep(2000);
        }
        catch(InterruptedException e)
        {

        }
        testForgetPWPage();
        try
        {
            Thread.sleep(2000);
        }
        catch(InterruptedException e)
        {

        }
        backAndLogIn();
    }
}
