package cse110mt13.tritonprofessorraterv1.espressoTest;

import android.app.Application;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ApplicationTestCase;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import cse110mt13.tritonprofessorraterv1.R;

/**
 * Created by Rui Deng on 2016/2/20.
 */
@RunWith(AndroidJUnit4.class)
public class loginTest{
    @Test
    public void testTypeInAndLogin()
    {
        String username = "admin";
        String password = "123456";
        onView(withId(R.id.input_email)).perform(typeText(username));
    }
}
