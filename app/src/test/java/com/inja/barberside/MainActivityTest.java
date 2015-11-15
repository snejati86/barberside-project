package com.inja.barberside;

import android.content.ContentResolver;
import android.support.v7.widget.RecyclerView;

import com.inja.barberside.activities.MainActivity;
import com.inja.barberside.provider.CustomerProvider;
import com.inja.barberside.provider.CustomerSQLiteOpenHelper;
import com.inja.barberside.provider.customer.CustomerColumns;
import com.inja.barberside.provider.customer.CustomerContentValues;
import com.inja.barberside.provider.customer.CustomerSelection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowContentResolver;

import java.lang.reflect.Field;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;
import static org.robolectric.Shadows.shadowOf;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,sdk = 19)
public class MainActivityTest {

    MainActivity mainActivity;

    CustomerProvider mProvider;

    ContentResolver mContentResolver;

    ShadowContentResolver mShadowContentResolver;

    @Before
    public void createActivity() throws Exception {
        mProvider = new CustomerProvider();
        mContentResolver = RuntimeEnvironment.application.getContentResolver();
        mShadowContentResolver = shadowOf(mContentResolver);
        mProvider.onCreate();
        ShadowContentResolver.registerProvider(
                CustomerProvider.AUTHORITY,    //authority of your provider
                mProvider   //instance of your ContentProvider (you can just use default constructor)
        );
        mainActivity = Robolectric.buildActivity(MainActivity.class).create().get();

    }

    @After
    public void tearDown()
    {
        resetSingleton(CustomerSQLiteOpenHelper.class, "sInstance");
        mainActivity.finish();
    }


    private void resetSingleton(Class clazz, String fieldName) {
        Field instance;
        try {
            instance = clazz.getDeclaredField(fieldName);
            instance.setAccessible(true);
            instance.set(null, null);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    /**
     * Test.
     */
    @Test
    public void testActivityShowsAllElements()
    {
        CustomerContentValues customer = new CustomerContentValues();
        customer.putBarber("TEST_BARBER");
        customer.putName("TEST_NAME");
        customer.putPhone(858L);
        customer.putSigned(10L);
        mainActivity.getContentResolver().insert(CustomerColumns.CONTENT_URI, customer.values());
        RecyclerView rv = ( RecyclerView) mainActivity.findViewById(R.id.customer_list);
        assertNotNull(rv);
        assertEquals(1,rv.getAdapter().getItemCount());
    }

    @Test
    public void testCustomerInsert()
    {
        CustomerContentValues customer = new CustomerContentValues();
        customer.putBarber("TEST_BARBER");
        customer.putName("TEST_NAME");
        customer.putPhone(858L);
        customer.putSigned(10L);
        mShadowContentResolver.insert(CustomerColumns.CONTENT_URI, customer.values());

        RecyclerView rv = (RecyclerView) mainActivity.findViewById(R.id.customer_list);
        assertEquals(1, rv.getAdapter().getItemCount());
        CustomerContentValues customer2 = new CustomerContentValues();
        customer2.putBarber("TEST_BARBER2");
        customer2.putName("TEST_NAME2");
        customer2.putPhone(858L);
        customer2.putSigned(10L);
        mShadowContentResolver.insert(CustomerColumns.CONTENT_URI, customer2.values());
        Robolectric.flushForegroundThreadScheduler();

        assertEquals(2,rv.getAdapter().getItemCount());

    }

    @Test
    public void testCustomerTimeSort()
    {
        fail();
    }

    @Test
    public void testCustomerUpdateReflectsOnList()
    {
        CustomerContentValues customer = new CustomerContentValues();
        customer.putBarber("TEST_BARBER");
        customer.putName("TEST_NAME");
        customer.putPhone(858L);
        customer.putSigned(10L);
        mainActivity.getContentResolver().insert(CustomerColumns.CONTENT_URI, customer.values());
        mainActivity.getContentResolver().insert(CustomerColumns.CONTENT_URI, customer.values());

        RecyclerView rv = ( RecyclerView) mainActivity.findViewById(R.id.customer_list);
        assertEquals(2, rv.getAdapter().getItemCount());
        CustomerSelection where = new CustomerSelection();
        where.barberContains("TEST_BARBER");
        mainActivity.getContentResolver().delete(CustomerColumns.CONTENT_URI, where.sel(), where.args());
        assertEquals(0,rv.getAdapter().getItemCount());

    }

    @Test
    public void testCustomerDeletionReflects()
    {
        fail();
    }

}