package com.inja.barberside.provider.customer;

import android.net.Uri;
import android.provider.BaseColumns;

import com.inja.barberside.provider.CustomerProvider;

/**
 * This class represents a customer
 */
public class CustomerColumns implements BaseColumns {
    public static final String TABLE_NAME = "customer";
    public static final Uri CONTENT_URI = Uri.parse(CustomerProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    /**
     * Name
     */
    public static final String NAME = "Name";

    /**
     * Barber
     */
    public static final String BARBER = "Barber";

    /**
     * Phone
     */
    public static final String PHONE = "Phone";

    /**
     * Time signed up
     */
    public static final String SIGNED = "Signed";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            NAME,
            BARBER,
            PHONE,
            SIGNED
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(NAME) || c.contains("." + NAME)) return true;
            if (c.equals(BARBER) || c.contains("." + BARBER)) return true;
            if (c.equals(PHONE) || c.contains("." + PHONE)) return true;
            if (c.equals(SIGNED) || c.contains("." + SIGNED)) return true;
        }
        return false;
    }

}
