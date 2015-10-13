package com.inja.barberside.provider.customer;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.inja.barberside.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code customer} table.
 */
public class CustomerContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return CustomerColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable CustomerSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable CustomerSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Name
     */
    public CustomerContentValues putName(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("name must not be null");
        mContentValues.put(CustomerColumns.NAME, value);
        return this;
    }


    /**
     * Barber
     */
    public CustomerContentValues putBarber(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("barber must not be null");
        mContentValues.put(CustomerColumns.BARBER, value);
        return this;
    }


    /**
     * Phone
     */
    public CustomerContentValues putPhone(@Nullable Long value) {
        mContentValues.put(CustomerColumns.PHONE, value);
        return this;
    }

    public CustomerContentValues putPhoneNull() {
        mContentValues.putNull(CustomerColumns.PHONE);
        return this;
    }
}
