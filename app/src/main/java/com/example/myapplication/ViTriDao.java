package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ViTriDao {
    @Query("Select * from vitri")
    List<ViTri> getAll();
    @Insert
    void insert(ViTri viTri);

}
