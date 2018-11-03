package com.example.mana.a4321football.data.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(indices = { @Index(value = { "team_id", "crest_url", "team_name" }, unique = true) })
public class Favorite {

  @PrimaryKey(autoGenerate = true)
  private int id;
  @ColumnInfo(name = "team_id")
  private int teamId;
  @ColumnInfo(name = "crest_url")
  private String crestUrl;
  @ColumnInfo(name = "team_name")
  private String teamName;

  public Favorite(int teamId, String crestUrl, String teamName) {
    this.teamId = teamId;
    this.crestUrl = crestUrl;
    this.teamName = teamName;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getTeamId() {
    return teamId;
  }

  public void setTeamId(int teamId) {
    this.teamId = teamId;
  }

  public String getCrestUrl() {
    return crestUrl;
  }

  public void setCrestUrl(String crestUrl) {
    this.crestUrl = crestUrl;
  }

  public String getTeamName() {
    return teamName;
  }

  public void setTeamName(String teamName) {
    this.teamName = teamName;
  }
}
