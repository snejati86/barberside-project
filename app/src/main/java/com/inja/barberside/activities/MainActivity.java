package com.inja.barberside.activities;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.inja.barberside.R;
import com.inja.barberside.adapters.DividerItemDecoration;
import com.inja.barberside.adapters.MyListCursorAdapter;
import com.inja.barberside.provider.customer.CustomerColumns;

import java.lang.reflect.Field;


public class MainActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int LOADER_IDENTITY = 1;
    private RecyclerView recyclerView;
    private MyListCursorAdapter adapter;
    private TextView marquee;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"Destoryed");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Resumed");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Stopped");
        getLoaderManager().destroyLoader(LOADER_IDENTITY);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "Started");
        getLoaderManager().initLoader(LOADER_IDENTITY, null, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.customer_list);
        marquee = (TextView) findViewById(R.id.marquee_text);
        marquee.setSelected(true);
        setMarqueeSpeed(marquee, 1000f, true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final Cursor customerCursor = this.getContentResolver().query(CustomerColumns.CONTENT_URI, customerSelection(), null, null, null);
        adapter = new MyListCursorAdapter(this, customerCursor);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        FloatingActionButton addCustomerButton = (FloatingActionButton) findViewById(R.id.fab);
        addCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerDialog customerDialog = new CustomerDialog();
                customerDialog.show(getFragmentManager(), customerDialog.getClass().getSimpleName());
            }
        });
        FloatingActionButton barberButton = (FloatingActionButton )findViewById(R.id.keypad);
        barberButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                KeypadDialog keypadDialog = new KeypadDialog();
                keypadDialog.show(getFragmentManager(),keypadDialog.getClass().getSimpleName());
            }
        });

    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this, CustomerColumns.CONTENT_URI, customerSelection(), null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);

    }

    private String[] customerSelection() {
        String[] selection = new String[4];

        selection[0] = CustomerColumns.NAME;
        selection[1] = CustomerColumns.BARBER;
        selection[2] = CustomerColumns.SIGNED;
        selection[3] = CustomerColumns._ID;
        return selection;
    }


    //FIXME : Doesn't work.
    private void setMarqueeSpeed(TextView tv, float speed, boolean speedIsMultiplier) {

        try {
            Field f = tv.getClass().getDeclaredField("mMarquee");
            f.setAccessible(true);

            Object marquee = f.get(tv);
            if (marquee != null) {

                String scrollSpeedFieldName = "mScrollUnit";
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    scrollSpeedFieldName = "mPixelsPerSecond";

                Field mf = marquee.getClass().getDeclaredField(scrollSpeedFieldName);
                mf.setAccessible(true);

                float newSpeed = speed;
                if (speedIsMultiplier)
                    newSpeed = mf.getFloat(marquee) * speed;

                mf.setFloat(marquee, newSpeed);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateMarquee()
    {
        //TODO : Update the marquee text with new wait times.
    }


}
