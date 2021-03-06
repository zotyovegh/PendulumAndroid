package com.example.pendulumtestjava.fragments.savingsFragment.savedObject;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.pendulumtestjava.fragments.savingsFragment.savedObject.SavePendulumModel;

import java.util.List;


@Dao
public interface PendulumsDao {
    @Query("SELECT timeStamp, id, 'Single' as type FROM single_table UNION SELECT timeStamp, id, 'Double' as type FROM double_table ORDER by timeStamp DESC")
    LiveData<List<SavePendulumModel>> getAllPendulums();
}
