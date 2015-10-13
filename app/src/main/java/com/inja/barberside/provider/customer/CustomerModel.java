package com.inja.barberside.provider.customer;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.inja.barberside.provider.base.BaseModel;

/**
 * This class represents a customer
 */
public interface CustomerModel extends BaseModel {

    /**
     * Name
     * Cannot be {@code null}.
     */
    @NonNull
    String getName();

    /**
     * Barber
     * Cannot be {@code null}.
     */
    @NonNull
    String getBarber();

    /**
     * Phone
     * Can be {@code null}.
     */
    @Nullable
    Long getPhone();

    /**
     * Time signed up
     */
    long getSigned();
}
