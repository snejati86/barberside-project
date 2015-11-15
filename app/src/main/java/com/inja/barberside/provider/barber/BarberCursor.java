package com.inja.barberside.provider.barber;

import android.database.Cursor;
import android.support.annotation.NonNull;

import com.inja.barberside.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code barber} table.
 */
public class BarberCursor extends AbstractCursor implements BarberModel {
    public BarberCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(BarberColumns._ID);
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
        String res = getStringOrNull(BarberColumns.NAME);
        if (res == null)
            throw new NullPointerException("The value of 'name' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Work average
     */
    public long getAveragetime() {
        Long res = getLongOrNull(BarberColumns.AVERAGETIME);
        if (res == null)
            throw new NullPointerException("The value of 'averagetime' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Password
     */
    public int getPassword() {
        Integer res = getIntegerOrNull(BarberColumns.PASSWORD);
        if (res == null)
            throw new NullPointerException("The value of 'password' in the database was null, which is not allowed according to the model definition");
        return res;
    }
}
