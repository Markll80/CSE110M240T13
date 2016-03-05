package cse110mt13.tritonprofessorraterv1.noUseNormalTests;

import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

import cse110mt13.tritonprofessorraterv1.Professor;

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
            fail("first fail");
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
            fail("second fail");
        }
    }

    @Test
    public void testWrongAdditions()
    {
        boolean addResult = profTest.addRating(-1,4,5);
        if(addResult)
        {
            fail("clarity is <0");
        }

        addResult = profTest.addRating(6,4,5);
        if(addResult)
        {
            fail("clarity is >5");
        }

        addResult = profTest.addRating(3,-1,5);
        if(addResult)
        {
            fail("easiness is <0");
        }

        addResult = profTest.addRating(3,6,5);
        if(addResult)
        {
            fail("easiness is >5");
        }

        addResult = profTest.addRating(3,4,-1);
        if(addResult)
        {
            fail("helpfulness is <0");
        }

        addResult = profTest.addRating(3,4,6);
        if(addResult)
        {
            fail("helpfulness is >5");
        }
    }
}
