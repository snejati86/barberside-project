package com.inja.barberside.adapters;

/**
 * Created by sinasix on 10/12/15.
 */
import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inja.barberside.R;
import com.inja.barberside.provider.barber.BarberColumns;
import com.inja.barberside.provider.customer.CustomerColumns;

/**
 * Created by skyfishjy on 10/31/14.
 */
public class MyListCursorAdapter extends CursorRecyclerViewAdapter<MyListCursorAdapter.ViewHolder>{

    public MyListCursorAdapter(Context context,Cursor cursor){
        super(context,cursor);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.customer_view, parent, false);
        ViewHolder vh = new ViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {
        int nameIndex = cursor.getColumnIndex(CustomerColumns.NAME);
        int barberIndex = cursor.getColumnIndex(BarberColumns.NAME);
        viewHolder.customerName.setText(cursor.getString(nameIndex));

        viewHolder.customerBarber.setText(cursor.getString(barberIndex));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView customerName;
        public TextView customerBarber;
        public ViewHolder(View view) {
            super(view);
            customerBarber = (TextView) view.findViewById(R.id.customer_barber);
            customerName = (TextView) view.findViewById(R.id.customer_name);
        }
    }
}
