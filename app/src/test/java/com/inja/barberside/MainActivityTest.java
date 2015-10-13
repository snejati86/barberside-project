package com.inja.barberside;

import com.inja.barberside.activities.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,sdk = 19)
public class MainActivityTest {

    MainActivity mainActivity;

    @Before
    public void createActivity() throws Exception {
        mainActivity = Robolectric.buildActivity(MainActivity.class).create().get();
    }

    @After
    public void tearDown()
    {
        mainActivity.finish();
    }

    /**
     * Test.
     */
    @Test
    public void testActivityShowsAllElements()
    {
        mainActivity.findViewById(R.id.customer_barber);
    }
}