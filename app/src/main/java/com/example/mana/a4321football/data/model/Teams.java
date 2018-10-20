package com.example.mana.a4321football.data.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Teams {

  @SerializedName("id") private int id;
  @SerializedName("name") private String name;
  @SerializedName("crestUrl") private String imgUrl;
  @SerializedName("venue") private String stadium;
  @SerializedName("shortName") private String shortName;
  @SerializedName("squad") private List<Squad> squads;
  @SerializedName("area") private Area area;
  @SerializedName("founded") private int founded;

  public int getFounded() {
    return founded;
  }

  public int getId() {
    return id;
  }

  public Area getArea() {
    return area;
  }

  public String getName() {
    return name;
  }

  public String getImgUrl() {
    return imgUrl;
  }

  public String getStadium() {
    return stadium;
  }

  public String getShortName() {
    return shortName;
  }

  public List<Squad> getSquads() {
    return squads;
  }

  public class Squad {
    @SerializedName("id") private int id;
    @SerializedName("name") private String name;
    @SerializedName("dateOfBirth") String dob;
    @SerializedName("countryOfBirth") private String nationality;
    @SerializedName("position") private String position;
    @SerializedName("shirtNumber") private int number;
    @SerializedName("role") private String role;

    public int getId() {
      return id;
    }

    public String getName() {
      return name;
    }

    public String getDob() {
      return dob;
    }

    public String getNationality() {
      return nationality;
    }

    public String getPosition() {
      return position;
    }

    public int getNumber() {
      return number;
    }

    public String getRole() {
      return role;
    }
  }

  public class Area {
    @SerializedName("name") private String name;

    public String getName() {
      return name;
    }
  }
}
