package com.inja.barberside.provider.barber;

import android.support.annotation.NonNull;

import com.inja.barberside.provider.base.BaseModel;

/**
 * This class represents a barber
 */
public interface BarberModel extends BaseModel {

    /**
     * Name
     * Cannot be {@code null}.
     */
    @NonNull
    String getName();

    /**
     * Work average
     */
    long getAveragetime();

    /**
     * Password
     */
    int getPassword();
}
