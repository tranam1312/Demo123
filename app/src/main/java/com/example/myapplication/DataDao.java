package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DataDao {
    @Query("Select* from data")
    List<DataEntity> dataEntities();
    @Insert
    void insertData(DataEntity dataEntity);

    @Query("Select  * from data  Where name =:name And latitude =:kd And longitude=:vt ")
    List<DataEntity> searchData(String name , double kd , double vt);



}
