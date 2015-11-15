package com.inja.barberside.provider.customer;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.inja.barberside.provider.barber.BarberColumns;
import com.inja.barberside.provider.base.AbstractSelection;

/**
 * Selection for the {@code customer} table.
 */
public class CustomerSelection extends AbstractSelection<CustomerSelection> {
    @Override
    protected Uri baseUri() {
        return CustomerColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code CustomerCursor} object, which is positioned before the first entry, or null.
     */
    public CustomerCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new CustomerCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public CustomerCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code CustomerCursor} object, which is positioned before the first entry, or null.
     */
    public CustomerCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new CustomerCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public CustomerCursor query(Context context) {
        return query(context, null);
    }


    public CustomerSelection id(long... value) {
        addEquals("customer." + CustomerColumns._ID, toObjectArray(value));
        return this;
    }

    public CustomerSelection idNot(long... value) {
        addNotEquals("customer." + CustomerColumns._ID, toObjectArray(value));
        return this;
    }

    public CustomerSelection orderById(boolean desc) {
        orderBy("customer." + CustomerColumns._ID, desc);
        return this;
    }

    public CustomerSelection orderById() {
        return orderById(false);
    }

    public CustomerSelection name(String... value) {
        addEquals(CustomerColumns.NAME, value);
        return this;
    }

    public CustomerSelection nameNot(String... value) {
        addNotEquals(CustomerColumns.NAME, value);
        return this;
    }

    public CustomerSelection nameLike(String... value) {
        addLike(CustomerColumns.NAME, value);
        return this;
    }

    public CustomerSelection nameContains(String... value) {
        addContains(CustomerColumns.NAME, value);
        return this;
    }

    public CustomerSelection nameStartsWith(String... value) {
        addStartsWith(CustomerColumns.NAME, value);
        return this;
    }

    public CustomerSelection nameEndsWith(String... value) {
        addEndsWith(CustomerColumns.NAME, value);
        return this;
    }

    public CustomerSelection orderByName(boolean desc) {
        orderBy(CustomerColumns.NAME, desc);
        return this;
    }

    public CustomerSelection orderByName() {
        orderBy(CustomerColumns.NAME, false);
        return this;
    }

    public CustomerSelection barber(long... value) {
        addEquals(CustomerColumns.BARBER, toObjectArray(value));
        return this;
    }

    public CustomerSelection barberNot(long... value) {
        addNotEquals(CustomerColumns.BARBER, toObjectArray(value));
        return this;
    }

    public CustomerSelection barberGt(long value) {
        addGreaterThan(CustomerColumns.BARBER, value);
        return this;
    }

    public CustomerSelection barberGtEq(long value) {
        addGreaterThanOrEquals(CustomerColumns.BARBER, value);
        return this;
    }

    public CustomerSelection barberLt(long value) {
        addLessThan(CustomerColumns.BARBER, value);
        return this;
    }

    public CustomerSelection barberLtEq(long value) {
        addLessThanOrEquals(CustomerColumns.BARBER, value);
        return this;
    }

    public CustomerSelection orderByBarber(boolean desc) {
        orderBy(CustomerColumns.BARBER, desc);
        return this;
    }

    public CustomerSelection orderByBarber() {
        orderBy(CustomerColumns.BARBER, false);
        return this;
    }

    public CustomerSelection barberName(String... value) {
        addEquals(BarberColumns.NAME, value);
        return this;
    }

    public CustomerSelection barberNameNot(String... value) {
        addNotEquals(BarberColumns.NAME, value);
        return this;
    }

    public CustomerSelection barberNameLike(String... value) {
        addLike(BarberColumns.NAME, value);
        return this;
    }

    public CustomerSelection barberNameContains(String... value) {
        addContains(BarberColumns.NAME, value);
        return this;
    }

    public CustomerSelection barberNameStartsWith(String... value) {
        addStartsWith(BarberColumns.NAME, value);
        return this;
    }

    public CustomerSelection barberNameEndsWith(String... value) {
        addEndsWith(BarberColumns.NAME, value);
        return this;
    }

    public CustomerSelection orderByBarberName(boolean desc) {
        orderBy(BarberColumns.NAME, desc);
        return this;
    }

    public CustomerSelection orderByBarberName() {
        orderBy(BarberColumns.NAME, false);
        return this;
    }

    public CustomerSelection barberAveragetime(long... value) {
        addEquals(BarberColumns.AVERAGETIME, toObjectArray(value));
        return this;
    }

    public CustomerSelection barberAveragetimeNot(long... value) {
        addNotEquals(BarberColumns.AVERAGETIME, toObjectArray(value));
        return this;
    }

    public CustomerSelection barberAveragetimeGt(long value) {
        addGreaterThan(BarberColumns.AVERAGETIME, value);
        return this;
    }

    public CustomerSelection barberAveragetimeGtEq(long value) {
        addGreaterThanOrEquals(BarberColumns.AVERAGETIME, value);
        return this;
    }

    public CustomerSelection barberAveragetimeLt(long value) {
        addLessThan(BarberColumns.AVERAGETIME, value);
        return this;
    }

    public CustomerSelection barberAveragetimeLtEq(long value) {
        addLessThanOrEquals(BarberColumns.AVERAGETIME, value);
        return this;
    }

    public CustomerSelection orderByBarberAveragetime(boolean desc) {
        orderBy(BarberColumns.AVERAGETIME, desc);
        return this;
    }

    public CustomerSelection orderByBarberAveragetime() {
        orderBy(BarberColumns.AVERAGETIME, false);
        return this;
    }

    public CustomerSelection barberPassword(int... value) {
        addEquals(BarberColumns.PASSWORD, toObjectArray(value));
        return this;
    }

    public CustomerSelection barberPasswordNot(int... value) {
        addNotEquals(BarberColumns.PASSWORD, toObjectArray(value));
        return this;
    }

    public CustomerSelection barberPasswordGt(int value) {
        addGreaterThan(BarberColumns.PASSWORD, value);
        return this;
    }

    public CustomerSelection barberPasswordGtEq(int value) {
        addGreaterThanOrEquals(BarberColumns.PASSWORD, value);
        return this;
    }

    public CustomerSelection barberPasswordLt(int value) {
        addLessThan(BarberColumns.PASSWORD, value);
        return this;
    }

    public CustomerSelection barberPasswordLtEq(int value) {
        addLessThanOrEquals(BarberColumns.PASSWORD, value);
        return this;
    }

    public CustomerSelection orderByBarberPassword(boolean desc) {
        orderBy(BarberColumns.PASSWORD, desc);
        return this;
    }

    public CustomerSelection orderByBarberPassword() {
        orderBy(BarberColumns.PASSWORD, false);
        return this;
    }

    public CustomerSelection phone(Long... value) {
        addEquals(CustomerColumns.PHONE, value);
        return this;
    }

    public CustomerSelection phoneNot(Long... value) {
        addNotEquals(CustomerColumns.PHONE, value);
        return this;
    }

    public CustomerSelection phoneGt(long value) {
        addGreaterThan(CustomerColumns.PHONE, value);
        return this;
    }

    public CustomerSelection phoneGtEq(long value) {
        addGreaterThanOrEquals(CustomerColumns.PHONE, value);
        return this;
    }

    public CustomerSelection phoneLt(long value) {
        addLessThan(CustomerColumns.PHONE, value);
        return this;
    }

    public CustomerSelection phoneLtEq(long value) {
        addLessThanOrEquals(CustomerColumns.PHONE, value);
        return this;
    }

    public CustomerSelection orderByPhone(boolean desc) {
        orderBy(CustomerColumns.PHONE, desc);
        return this;
    }

    public CustomerSelection orderByPhone() {
        orderBy(CustomerColumns.PHONE, false);
        return this;
    }

    public CustomerSelection signed(long... value) {
        addEquals(CustomerColumns.SIGNED, toObjectArray(value));
        return this;
    }

    public CustomerSelection signedNot(long... value) {
        addNotEquals(CustomerColumns.SIGNED, toObjectArray(value));
        return this;
    }

    public CustomerSelection signedGt(long value) {
        addGreaterThan(CustomerColumns.SIGNED, value);
        return this;
    }

    public CustomerSelection signedGtEq(long value) {
        addGreaterThanOrEquals(CustomerColumns.SIGNED, value);
        return this;
    }

    public CustomerSelection signedLt(long value) {
        addLessThan(CustomerColumns.SIGNED, value);
        return this;
    }

    public CustomerSelection signedLtEq(long value) {
        addLessThanOrEquals(CustomerColumns.SIGNED, value);
        return this;
    }

    public CustomerSelection orderBySigned(boolean desc) {
        orderBy(CustomerColumns.SIGNED, desc);
        return this;
    }

    public CustomerSelection orderBySigned() {
        orderBy(CustomerColumns.SIGNED, false);
        return this;
    }
}
