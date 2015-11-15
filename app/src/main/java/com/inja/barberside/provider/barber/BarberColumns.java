package com.inja.barberside.provider.barber;

import android.net.Uri;
import android.provider.BaseColumns;

import com.inja.barberside.provider.CustomerProvider;

/**
 * This class represents a barber
 */
public class BarberColumns implements BaseColumns {
    public static final String TABLE_NAME = "barber";
    public static final Uri CONTENT_URI = Uri.parse(CustomerProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    /**
     * Name
     */
    public static final String NAME = "barber__name";

    /**
     * Work average
     */
    public static final String AVERAGETIME = "AverageTime";

    /**
     * Password
     */
    public static final String PASSWORD = "Password";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            NAME,
            AVERAGETIME,
            PASSWORD
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(NAME) || c.contains("." + NAME)) return true;
            if (c.equals(AVERAGETIME) || c.contains("." + AVERAGETIME)) return true;
            if (c.equals(PASSWORD) || c.contains("." + PASSWORD)) return true;
        }
        return false;
    }

}
