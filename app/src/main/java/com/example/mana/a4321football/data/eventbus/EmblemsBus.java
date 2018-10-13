package com.example.mana.a4321football.data.eventbus;

import java.util.Map;

public class EmblemsBus {
  private static Map<String,String> emblems;

  public static Map<String, String> getEmblems() {
    return emblems;
  }

  public static void setEmblems(Map<String, String> emblems) {
    EmblemsBus.emblems = emblems;
  }
}
