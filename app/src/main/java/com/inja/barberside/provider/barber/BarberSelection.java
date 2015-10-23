package com.inja.barberside.provider.barber;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.inja.barberside.provider.base.AbstractSelection;

/**
 * Selection for the {@code barber} table.
 */
public class BarberSelection extends AbstractSelection<BarberSelection> {
    @Override
    protected Uri baseUri() {
        return BarberColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code BarberCursor} object, which is positioned before the first entry, or null.
     */
    public BarberCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new BarberCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public BarberCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code BarberCursor} object, which is positioned before the first entry, or null.
     */
    public BarberCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new BarberCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public BarberCursor query(Context context) {
        return query(context, null);
    }


    public BarberSelection id(long... value) {
        addEquals("barber." + BarberColumns._ID, toObjectArray(value));
        return this;
    }

    public BarberSelection idNot(long... value) {
        addNotEquals("barber." + BarberColumns._ID, toObjectArray(value));
        return this;
    }

    public BarberSelection orderById(boolean desc) {
        orderBy("barber." + BarberColumns._ID, desc);
        return this;
    }

    public BarberSelection orderById() {
        return orderById(false);
    }

    public BarberSelection name(String... value) {
        addEquals(BarberColumns.NAME, value);
        return this;
    }

    public BarberSelection nameNot(String... value) {
        addNotEquals(BarberColumns.NAME, value);
        return this;
    }

    public BarberSelection nameLike(String... value) {
        addLike(BarberColumns.NAME, value);
        return this;
    }

    public BarberSelection nameContains(String... value) {
        addContains(BarberColumns.NAME, value);
        return this;
    }

    public BarberSelection nameStartsWith(String... value) {
        addStartsWith(BarberColumns.NAME, value);
        return this;
    }

    public BarberSelection nameEndsWith(String... value) {
        addEndsWith(BarberColumns.NAME, value);
        return this;
    }

    public BarberSelection orderByName(boolean desc) {
        orderBy(BarberColumns.NAME, desc);
        return this;
    }

    public BarberSelection orderByName() {
        orderBy(BarberColumns.NAME, false);
        return this;
    }

    public BarberSelection averagetime(long... value) {
        addEquals(BarberColumns.AVERAGETIME, toObjectArray(value));
        return this;
    }

    public BarberSelection averagetimeNot(long... value) {
        addNotEquals(BarberColumns.AVERAGETIME, toObjectArray(value));
        return this;
    }

    public BarberSelection averagetimeGt(long value) {
        addGreaterThan(BarberColumns.AVERAGETIME, value);
        return this;
    }

    public BarberSelection averagetimeGtEq(long value) {
        addGreaterThanOrEquals(BarberColumns.AVERAGETIME, value);
        return this;
    }

    public BarberSelection averagetimeLt(long value) {
        addLessThan(BarberColumns.AVERAGETIME, value);
        return this;
    }

    public BarberSelection averagetimeLtEq(long value) {
        addLessThanOrEquals(BarberColumns.AVERAGETIME, value);
        return this;
    }

    public BarberSelection orderByAveragetime(boolean desc) {
        orderBy(BarberColumns.AVERAGETIME, desc);
        return this;
    }

    public BarberSelection orderByAveragetime() {
        orderBy(BarberColumns.AVERAGETIME, false);
        return this;
    }

    public BarberSelection password(int... value) {
        addEquals(BarberColumns.PASSWORD, toObjectArray(value));
        return this;
    }

    public BarberSelection passwordNot(int... value) {
        addNotEquals(BarberColumns.PASSWORD, toObjectArray(value));
        return this;
    }

    public BarberSelection passwordGt(int value) {
        addGreaterThan(BarberColumns.PASSWORD, value);
        return this;
    }

    public BarberSelection passwordGtEq(int value) {
        addGreaterThanOrEquals(BarberColumns.PASSWORD, value);
        return this;
    }

    public BarberSelection passwordLt(int value) {
        addLessThan(BarberColumns.PASSWORD, value);
        return this;
    }

    public BarberSelection passwordLtEq(int value) {
        addLessThanOrEquals(BarberColumns.PASSWORD, value);
        return this;
    }

    public BarberSelection orderByPassword(boolean desc) {
        orderBy(BarberColumns.PASSWORD, desc);
        return this;
    }

    public BarberSelection orderByPassword() {
        orderBy(BarberColumns.PASSWORD, false);
        return this;
    }
}
