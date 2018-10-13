package com.example.mana.a4321football.data.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class News {
  @SerializedName("articles") private List<Articles> articles;

  public List<Articles> getArticles() {
    return articles;
  }

  public class Articles {
    @SerializedName("title") private String title;
    @SerializedName("description") private String desc;
    @SerializedName("url") private String url;
    @SerializedName("urlToImage") private String img;
    @SerializedName("publishedAt") private String published;
    @SerializedName("content") private String content;
    @SerializedName("source") private Source source;

    public String getTitle() {
      return title;
    }

    public String getDesc() {
      return desc;
    }

    public String getUrl() {
      return url;
    }

    public String getImg() {
      return img;
    }

    public String getPublished() {
      return published;
    }

    public String getContent() {
      return content;
    }

    public Source getSource() {
      return source;
    }

    public class Source {
      @SerializedName("name") private String name;
      @SerializedName("id") private String id;

      public String getName() {
        return name;
      }

      public String getId() {
        return id;
      }
    }
  }
}
