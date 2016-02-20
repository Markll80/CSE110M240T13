package cse110mt13.tritonprofessorraterv1.espressoTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import android.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import android.support.test.espresso.*;
import android.support.test.InstrumentationRegistry;
import cse110mt13.tritonprofessorraterv1.LogIn;

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
