package com.example.myapplication;

import androidx.room.RoomDatabase;
@androidx.room.Database(entities ={ DataEntity.class,ViTri.class},exportSchema = false,version = 2)

public abstract class Database  extends RoomDatabase{
 public abstract DataDao dataDao();
 public abstract ViTriDao viTriDao();

};

