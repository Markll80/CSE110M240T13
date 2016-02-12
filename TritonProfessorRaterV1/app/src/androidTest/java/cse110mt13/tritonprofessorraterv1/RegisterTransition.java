package cse110mt13.tritonprofessorraterv1;

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

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
@RunWith(AndroidJUnit4.class)
public class RegisterTransition{

    private String username;
    @Rule
    public ActivityTestRule<LogIn> mActivityRule = new ActivityTestRule<>(LogIn.class);

    @Before
    public void initUsername(){
        username = "nltu@ucsd.edu";
    }

    @Test
    public void RegisterButtonTransition(){
        onView(withId(R.id.username)).perform(typeText(username), closeSoftKeyboard());
        onView(withId(R.id.bRegister)).perform(click());
        onView(withId(R.id.username)).check(matches(withText(username)));
    }


}

