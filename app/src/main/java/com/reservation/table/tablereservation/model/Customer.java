package com.reservation.table.tablereservation.model;

import android.arch.persistence.room.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created on 12/07/17.
 */
@Entity(tableName = "customers",
        primaryKeys = {"id"})
public class Customer {

    @JsonProperty("customerFirstName")
    private String lastName;

    @JsonProperty("customerLastName")
    private String firstName;

    private int id;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
