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

/**
 * Created by Rui Deng on 2016/2/14.
 */
@RunWith(AndroidJUnit4.class)
public class ProfessorTest {
    private Professor profTest;

    @Before
    public void setUp() throws Exception
    {
        profTest = new Professor();
        profTest.setup("Christine Alvarado");
    }

    @Test
    public void testSetUp()
    {
        assertEquals(profTest.getName(),"Christine Alvarado");
        assertEquals(profTest.getClarity(),0);
        assertEquals(profTest.getEasiness(),0);
        assertEquals(profTest.getHelpfulness(),0);
        assertEquals(profTest.getNumRatings(), 0);
    }

    @Test
    public void testAddRating()
    {
        boolean addResult = profTest.addRating(3,4,5);
        if(addResult == true)
        {
            assertEquals(profTest.getClarity(),3);
            assertEquals(profTest.getEasiness(),4);
            assertEquals(profTest.getHelpfulness(),5);
            assertEquals(profTest.getNumRatings(),1);
        }
        else
        {
            fail();
        }

        addResult = profTest.addRating(5,4,3);
        if(addResult == true)
        {
            assertEquals(profTest.getClarity(),4);
            assertEquals(profTest.getEasiness(),4);
            assertEquals(profTest.getHelpfulness(),4);
            assertEquals(profTest.getNumRatings(),2);
        }
        else
        {
            fail();
        }
    }
}
