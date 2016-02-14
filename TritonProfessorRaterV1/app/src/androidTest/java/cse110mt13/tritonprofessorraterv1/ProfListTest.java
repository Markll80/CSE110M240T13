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
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

import java.util.ArrayList;

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

    @Test
    public void testGetComments()
    {
        ArrayList<Comment> comments = testList.getComments("Qtj4d8H1ZC");

        //assert comments should be based on what's on the parse database
        assertEquals("nneGzg9mNw", comments.get(0));
        assertEquals("fb56kmvk5s",comments.get(1));
    }

    @Test
    public void testProfListConst()
    {
        ArrayList<Professor> profList = testList.professors;

        //assert professors' information should be based on what's on the parse database
        assertEquals("Prof B", profList.get(0).getName());
        assertEquals("Prof A", profList.get(1).getName());
    }
}


