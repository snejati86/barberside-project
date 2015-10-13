package com.inja.barberside.provider.customer;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.inja.barberside.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code customer} table.
 */
public class CustomerCursor extends AbstractCursor implements CustomerModel {
    public CustomerCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(CustomerColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Name
     * Cannot be {@code null}.
     */
    @NonNull
    public String getName() {
        String res = getStringOrNull(CustomerColumns.NAME);
        if (res == null)
            throw new NullPointerException("The value of 'name' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Barber
     * Cannot be {@code null}.
     */
    @NonNull
    public String getBarber() {
        String res = getStringOrNull(CustomerColumns.BARBER);
        if (res == null)
            throw new NullPointerException("The value of 'barber' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Phone
     * Can be {@code null}.
     */
    @Nullable
    public Long getPhone() {
        Long res = getLongOrNull(CustomerColumns.PHONE);
        return res;
    }

    /**
     * Time signed up
     */
    public long getSigned() {
        Long res = getLongOrNull(CustomerColumns.SIGNED);
        if (res == null)
            throw new NullPointerException("The value of 'signed' in the database was null, which is not allowed according to the model definition");
        return res;
    }
}
