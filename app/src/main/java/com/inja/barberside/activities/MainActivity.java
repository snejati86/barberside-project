package com.inja.barberside.activities;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.inja.barberside.R;
import com.inja.barberside.adapters.DividerItemDecoration;
import com.inja.barberside.adapters.MyListCursorAdapter;
import com.inja.barberside.provider.customer.CustomerColumns;


public class MainActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private MyListCursorAdapter adapter;

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getLoaderManager().destroyLoader(1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getLoaderManager().initLoader(1, null, this);
/*        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                CustomerContentValues customerContentValues = new CustomerContentValues();
                customerContentValues.putName("SINA 2");
                customerContentValues.putSigned(new Date().getTime());
                customerContentValues.putPhone(854544L);
                customerContentValues.putBarber("Barberera");
                getContentResolver().insert(CustomerColumns.CONTENT_URI, customerContentValues.values());
                handler.postDelayed(this, 5000);
            }
        }, 1000);*/

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.customer_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String[] selection = new String[4];
        selection[0] = CustomerColumns.NAME;
        selection[1] = CustomerColumns.BARBER;
        selection[2] = CustomerColumns.SIGNED;
        selection[3] = CustomerColumns._ID;
        final Cursor customerCursor = this.getContentResolver().query(CustomerColumns.CONTENT_URI,selection,null,null,null);
        adapter = new MyListCursorAdapter(this, customerCursor);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerDialog customerDialog = new CustomerDialog();
                customerDialog.show(getFragmentManager(), "C");
            }
        });

    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] selection = new String[4];

        selection[0] = CustomerColumns.NAME;
        selection[1] = CustomerColumns.BARBER;
        selection[2] = CustomerColumns.SIGNED;
        selection[3] = CustomerColumns._ID;
        return new CursorLoader(this,CustomerColumns.CONTENT_URI,selection,null,null,null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);

    }

}
