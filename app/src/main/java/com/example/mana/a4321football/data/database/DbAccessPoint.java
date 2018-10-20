package com.example.mana.a4321football.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import java.util.List;

@Dao
public interface DbAccessPoint {

  @Insert(onConflict = OnConflictStrategy.REPLACE) void insertMovieToFavorite(Favorite fav);

  @Query("SELECT * FROM Favorite") List<Favorite> getFavoriteTeams();

  @Query("DELETE FROM Favorite WHERE team_id=:id") void deleteSelectedItem(int id);


}
