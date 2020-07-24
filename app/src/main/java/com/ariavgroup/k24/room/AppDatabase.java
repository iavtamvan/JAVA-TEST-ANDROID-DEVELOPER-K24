package com.ariavgroup.k24.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.ariavgroup.k24.model.AkunModel;

@Database(entities = {AkunModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AkunDao akunDao();
}
