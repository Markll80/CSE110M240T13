package cse110mt13.tritonprofessorraterv1.espressoTest;

import android.app.Application;
import android.support.test.InstrumentationRegistry;
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
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;

/**
 * Created by Rui Deng on 2016/3/4.
 */
@RunWith(AndroidJUnit4.class)
public class logoutTest extends ActivityInstrumentationTestCase2<StartHere>{
    @Rule
    public ActivityTestRule<StartHere> activityTestRule =
            new ActivityTestRule<>(StartHere.class);

    public logoutTest()
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
    public void testLogOut() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withId(R.id.action_Logout)).perform(click());
    }
}
