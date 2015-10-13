package com.inja.barberside.activities;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;

import com.inja.barberside.R;
import com.inja.barberside.provider.customer.CustomerColumns;
import com.inja.barberside.provider.customer.CustomerContentValues;

import java.util.Date;


public class MainActivity extends Activity implements LoaderManager.LoaderCallbacks<Object> {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getLoaderManager().initLoader(0, null, this);
        CustomerContentValues values = new CustomerContentValues();
        values.putBarber("Barber 1");
        values.putName("Mine");
        values.putPhone(8589994421L);
        values.putSigned(new Date().getTime());
        this.getContentResolver().insert(CustomerColumns.CONTENT_URI, values.values());

    }

    @Override
    public Loader<Object> onCreateLoader(int i, Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Object> loader, Object o) {

    }

    @Override
    public void onLoaderReset(Loader<Object> loader) {

    }
}
