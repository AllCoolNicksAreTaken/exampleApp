package com.reservation.table.tablereservation.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.reservation.table.tablereservation.model.Table;

import java.util.List;

/**
 * Created on 16/07/17.
 */
@Dao
public interface TableDAO {

    @Query("SELECT * FROM tables")
    List<Table> getAll();

    @Query("SELECT * FROM tables WHERE number == :id")
    Table getById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Table> tables);

    @Update
    void update(Table table);

    @Query("UPDATE tables SET isbooked = 0 WHERE isbooked == 1")
    void removeAllReservations();
}
