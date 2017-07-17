package com.reservation.table.tablereservation.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.support.annotation.WorkerThread;

import com.reservation.table.tablereservation.model.Customer;

import java.util.List;

/**
 * Created on 16/07/17.
 */

@Dao
public interface CustomerDAO {

    @Query("SELECT * FROM customers")
    List<Customer> getAll();

    @Query("SELECT * FROM customers WHERE lastName LIKE :last AND firstName LIKE :first LIMIT 1")
    Customer findByName(String first, String last);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Customer>customers);

    @Delete
    void delete(Customer customer);
}
