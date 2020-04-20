package com.example.pendulumtestjava.main.listFragment.shared;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pendulumtestjava.main.listFragment.singleP.SinglePendulumObject;

import java.util.List;

@Dao
public interface DbDao {

    @Insert
    void insertSinglePendulum(SinglePendulumObject pendulum);

    @Delete
    void deleteSinglePendulum(SinglePendulumObject pendulum);

    @Query("SELECT * FROM single_pendulum_table ORDER BY timeStamp DESC")
    LiveData<List<SinglePendulumObject>> getAllSinglePendulums();


}