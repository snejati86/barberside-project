package com.inja.barberside.provider;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.inja.barberside.BuildConfig;
import com.inja.barberside.provider.barber.BarberColumns;
import com.inja.barberside.provider.customer.CustomerColumns;

public class CustomerSQLiteOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_FILE_NAME = "customer.db";
    // @formatter:off
    public static final String SQL_CREATE_TABLE_BARBER = "CREATE TABLE IF NOT EXISTS "
            + BarberColumns.TABLE_NAME + " ( "
            + BarberColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + BarberColumns.NAME + " TEXT NOT NULL, "
            + BarberColumns.AVERAGETIME + " INTEGER NOT NULL, "
            + BarberColumns.PASSWORD + " INTEGER NOT NULL "
            + " );";
    public static final String SQL_CREATE_TABLE_CUSTOMER = "CREATE TABLE IF NOT EXISTS "
            + CustomerColumns.TABLE_NAME + " ( "
            + CustomerColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CustomerColumns.NAME + " TEXT NOT NULL, "
            + CustomerColumns.BARBER + " INTEGER NOT NULL, "
            + CustomerColumns.PHONE + " INTEGER, "
            + CustomerColumns.SIGNED + " INTEGER NOT NULL "
            + ", CONSTRAINT fk_barber FOREIGN KEY (" + CustomerColumns.BARBER + ") REFERENCES barber (_id) ON DELETE CASCADE"
            + " );";
    private static final String TAG = CustomerSQLiteOpenHelper.class.getSimpleName();
    private static final int DATABASE_VERSION = 1;
    private static CustomerSQLiteOpenHelper sInstance;
    private final Context mContext;
    private final CustomerSQLiteOpenHelperCallbacks mOpenHelperCallbacks;

    // @formatter:on

    private CustomerSQLiteOpenHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        mContext = context;
        mOpenHelperCallbacks = new CustomerSQLiteOpenHelperCallbacks();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private CustomerSQLiteOpenHelper(Context context, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, errorHandler);
        mContext = context;
        mOpenHelperCallbacks = new CustomerSQLiteOpenHelperCallbacks();
    }

    public static CustomerSQLiteOpenHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = newInstance(context.getApplicationContext());
        }
        return sInstance;
    }

    private static CustomerSQLiteOpenHelper newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }

    /*
     * Pre Honeycomb.
     */
    private static CustomerSQLiteOpenHelper newInstancePreHoneycomb(Context context) {
        return new CustomerSQLiteOpenHelper(context);
    }

    /*
     * Post Honeycomb.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static CustomerSQLiteOpenHelper newInstancePostHoneycomb(Context context) {
        return new CustomerSQLiteOpenHelper(context, new DefaultDatabaseErrorHandler());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if (BuildConfig.DEBUG) Log.d(TAG, "onCreate");
        mOpenHelperCallbacks.onPreCreate(mContext, db);
        db.execSQL(SQL_CREATE_TABLE_BARBER);
        db.execSQL(SQL_CREATE_TABLE_CUSTOMER);
        mOpenHelperCallbacks.onPostCreate(mContext, db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            setForeignKeyConstraintsEnabled(db);
        }
        mOpenHelperCallbacks.onOpen(mContext, db);
    }

    private void setForeignKeyConstraintsEnabled(SQLiteDatabase db) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            setForeignKeyConstraintsEnabledPreJellyBean(db);
        } else {
            setForeignKeyConstraintsEnabledPostJellyBean(db);
        }
    }

    private void setForeignKeyConstraintsEnabledPreJellyBean(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setForeignKeyConstraintsEnabledPostJellyBean(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mOpenHelperCallbacks.onUpgrade(mContext, db, oldVersion, newVersion);
    }
}
