package cse110mt13.tritonprofessorraterv1.noUseNormalTests;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

import cse110mt13.tritonprofessorraterv1.ProfList;
import cse110mt13.tritonprofessorraterv1.SearchPage;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
@RunWith(AndroidJUnit4.class)
public class ProfListTest{

    private String username;
    ProfList testList;
    @Rule
    public ActivityTestRule<SearchPage> mActivityRule = new ActivityTestRule<>(SearchPage.class);

    @Before
    public void setUp() throws Exception
    {
        testList = new ProfList();
    }

}


