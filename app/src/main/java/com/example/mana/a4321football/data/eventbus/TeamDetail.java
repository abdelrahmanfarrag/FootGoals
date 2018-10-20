package com.example.mana.a4321football.data.eventbus;

public class TeamDetail {
  private int id;
  private String name;
  private String url;

  public TeamDetail(int id, String name, String url) {
    this.id = id;
    this.name = name;
    this.url = url;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
