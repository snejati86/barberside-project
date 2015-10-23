package com.inja.barberside.provider.barber;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.inja.barberside.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code barber} table.
 */
public class BarberContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return BarberColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable BarberSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable BarberSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Name
     */
    public BarberContentValues putName(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("name must not be null");
        mContentValues.put(BarberColumns.NAME, value);
        return this;
    }


    /**
     * Work average
     */
    public BarberContentValues putAveragetime(long value) {
        mContentValues.put(BarberColumns.AVERAGETIME, value);
        return this;
    }


    /**
     * Password
     */
    public BarberContentValues putPassword(int value) {
        mContentValues.put(BarberColumns.PASSWORD, value);
        return this;
    }

}
