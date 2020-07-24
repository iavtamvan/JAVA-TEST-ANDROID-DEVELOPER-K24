package com.ariavgroup.k24.util;

import android.app.Application;

import androidx.room.Room;

import com.ariavgroup.k24.room.AppDatabase;

public class MyApp extends Application {

    public static AppDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,"akun").allowMainThreadQueries().build();
    }
}
