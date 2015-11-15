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
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.inja.barberside.R;
import com.inja.barberside.adapters.DividerItemDecoration;
import com.inja.barberside.adapters.MyListCursorAdapter;
import com.inja.barberside.provider.barber.BarberColumns;
import com.inja.barberside.provider.barber.BarberContentValues;
import com.inja.barberside.provider.barber.BarberCursor;
import com.inja.barberside.provider.barber.BarberSelection;
import com.inja.barberside.provider.customer.CustomerColumns;
import com.inja.barberside.provider.customer.CustomerCursor;
import com.inja.barberside.provider.customer.CustomerSelection;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> ,BarberList{

    /**
     * Tag used for logging.
     */
    private static final String TAG = MainActivity.class.getSimpleName();

    /**
     * Loader identity.
     */
    private static final int LOADER_IDENTITY = 1;

    /**
     * Recycler view.
     */
    @Bind(R.id.customer_list)
    RecyclerView recyclerView;

    /**
     * Marquee to display the name of barbers.
     */
    @Bind(R.id.marquee_text)
    TextView marquee;

    /**
     * Total number of people waiting.
     */
    @Bind(R.id.wait_count)
    TextView waitingCount;

    /**
     * Button to exit barber mode.
     */
    @Bind(R.id.close_barber)
    FloatingActionButton exitBarberModeButton;

    /**
     * Button to add customer;
     */
    @Bind(R.id.fab)
    FloatingActionButton addCustomerButton;

    /**
     * Key pad
     */
    @Bind(R.id.keypad)
    FloatingActionButton enterBarberModeButton;

    /**
     * Custom cursor adapter.
     */
    private MyListCursorAdapter adapter;

    /**
     * Item touch helper for swiping ,
     */
    private ItemTouchHelper itemTouchHelper;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"Destoryed");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //startLockTask();
        Log.d(TAG,"Resumed");
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
        getLoaderManager().initLoader(LOADER_IDENTITY, null, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initUIComponents();
        final Cursor customerCursor = this.getContentResolver().query(CustomerColumns.CONTENT_URI, customerSelection(), null, null, null);
        waitingCount.setText(String.valueOf(customerCursor.getCount())+" Souls waiting");
        adapter = new MyListCursorAdapter(this, customerCursor);
        recyclerView.setAdapter(adapter);
        updateBarbers();


    }

    private void initUIComponents() {
        marquee.setSelected(true);
        setMarqueeSpeed(marquee, 1000f, true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
;
        addCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerDialog customerDialog = new CustomerDialog();
                customerDialog.show(getFragmentManager(), customerDialog.getClass().getSimpleName());
            }
        });
        enterBarberModeButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                KeypadDialog keypadDialog = new KeypadDialog();
                keypadDialog.show(getFragmentManager(),keypadDialog.getClass().getSimpleName());
            }
        });

        exitBarberModeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                exitBarberMode();
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
        waitingCount.setText(String.valueOf(data.getCount()) + " Souls waiting");
        updateMarquee();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);

    }

    private String[] customerSelection() {
        String[] selection = new String[5];

        selection[0] = CustomerColumns.NAME;
        selection[1] = CustomerColumns.BARBER;
        selection[2] = CustomerColumns.SIGNED;
        selection[3] = CustomerColumns._ID;
        selection[4] = BarberColumns.NAME;
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
        marquee.setText("");
        BarberSelection barberSelection = new BarberSelection();
        BarberCursor barberCursor = barberSelection.query(getContentResolver());
        Log.d(TAG,"Size "+barberCursor.getCount());
        StringBuffer stringBuffer = new StringBuffer();
        while ( barberCursor.moveToNext())
        {
            String barberName = barberCursor.getName();
            Log.d(TAG,barberName);
            long waitTime = barberCursor.getAveragetime();
            CustomerSelection customerSelection = new CustomerSelection();
            customerSelection.barberName(barberName);
            int count = customerSelection.query(getContentResolver()).getCount();
            String time = String.format("%d min",
                    TimeUnit.MILLISECONDS.toMinutes(waitTime*count)
            );
            stringBuffer.append(barberName+" "+time+" | ");
            customerSelection.closeParen();
        }
        marquee.setText(stringBuffer);
        barberCursor.close();
    }


    /**
     * Adds harcoded barbers.
     * TODO : Remove this when ready.
     */
    private void updateBarbers()
    {
        getContentResolver().delete(BarberColumns.CONTENT_URI, null, null);
        BarberContentValues barberContentValues = new BarberContentValues();
        barberContentValues.putName("Jinan").putAveragetime(60*30*1000).putPassword(1234);
        this.getContentResolver().insert(BarberColumns.CONTENT_URI, barberContentValues.values());
        barberContentValues.putName("Marvin").putAveragetime(60 * 25 * 1000).putPassword(4321);
        this.getContentResolver().insert(BarberColumns.CONTENT_URI, barberContentValues.values());
        barberContentValues.putName("Roger").putAveragetime(60 * 35 * 1000).putPassword(5555);
        this.getContentResolver().insert(BarberColumns.CONTENT_URI, barberContentValues.values());
        barberContentValues.putName("Any barber").putAveragetime(60 * 35 * 1000).putPassword(9999);
        this.getContentResolver().insert(BarberColumns.CONTENT_URI, barberContentValues.values());
    }


    @Override
    public void barberMode(long barberId) {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction)
            {
                if ( direction == ItemTouchHelper.RIGHT  )
                {
                    int adapterPosition = viewHolder.getAdapterPosition();
                    long id = adapter.getItemId(adapterPosition);

                    CustomerSelection customerSelection = new CustomerSelection();
                    customerSelection.id(id);
                    int deleted = customerSelection.delete(getContentResolver());
                    Log.d(TAG," rows deleted "+deleted);
                    exitBarberMode();
                }
            }
        };
        itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        CustomerSelection customerSelection = new CustomerSelection();
        customerSelection.barber(barberId).or().barberNameLike("Any barber").orderBySigned(false);
        CustomerCursor customerCursor = customerSelection.query(getContentResolver());
        exitBarberModeButton.setVisibility(View.VISIBLE);
        adapter.swapCursor(customerCursor);
    }

    void exitBarberMode() {
        CustomerSelection customerSelection1 = new CustomerSelection().orderBySigned(false);
        adapter.swapCursor(customerSelection1.query(getContentResolver()));
        itemTouchHelper.attachToRecyclerView(null);
        exitBarberModeButton.setVisibility(View.INVISIBLE);
    }
}
