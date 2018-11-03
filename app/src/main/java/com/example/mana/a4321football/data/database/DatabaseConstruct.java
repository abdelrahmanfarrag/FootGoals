package com.example.mana.a4321football.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = Favorite.class, version = 1, exportSchema = false)
public abstract class DatabaseConstruct extends RoomDatabase {
  private static DatabaseConstruct instance;

  public static DatabaseConstruct getInstance(Context context) {
    if (instance == null) {
      instance = Room.databaseBuilder(context, DatabaseConstruct.class, "favorite.db")
          .fallbackToDestructiveMigration()
          .build();
    }
    return instance;
  }

  public abstract DbAccessPoint accessPoint();
}
