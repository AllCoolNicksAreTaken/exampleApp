package com.reservation.table.tablereservation.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

/**
 * Created on 14/07/17.
 */
@Entity(tableName = "tables",
        primaryKeys = {"number"},
        foreignKeys = @ForeignKey(entity = Customer.class,
        parentColumns = "id",
        childColumns = "customerId"))
public class Table {

    private int number;
    private boolean isBooked;
    private int customerId;

    public Table(int number, Boolean isBooked) {
        this.number = number;
        this.isBooked = isBooked;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }
}
