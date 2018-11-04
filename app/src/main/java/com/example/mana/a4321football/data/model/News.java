package com.example.mana.a4321football.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class News implements Parcelable {
  @SerializedName("articles") private List<Articles> articles;

  protected News(Parcel in) {
  }

  public static final Creator<News> CREATOR = new Creator<News>() {
    @Override
    public News createFromParcel(Parcel in) {
      return new News(in);
    }

    @Override
    public News[] newArray(int size) {
      return new News[size];
    }
  };

  public List<Articles> getArticles() {
    return articles;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
  }

  public class Articles implements Parcelable {
    @SerializedName("title") private String title;
    @SerializedName("description") private String desc;
    @SerializedName("url") private String url;
    @SerializedName("urlToImage") private String img;
    @SerializedName("publishedAt") private String published;
    @SerializedName("content") private String content;
    @SerializedName("source") private Source source;

    protected Articles(Parcel in) {
      title = in.readString();
      desc = in.readString();
      url = in.readString();
      img = in.readString();
      published = in.readString();
      content = in.readString();
    }

    public final Creator<Articles> CREATOR = new Creator<Articles>() {
      @Override
      public Articles createFromParcel(Parcel in) {
        return new Articles(in);
      }

      @Override
      public Articles[] newArray(int size) {
        return new Articles[size];
      }
    };

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

    @Override public int describeContents() {
      return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
      dest.writeString(title);
      dest.writeString(desc);
      dest.writeString(url);
      dest.writeString(img);
      dest.writeString(published);
      dest.writeString(content);
    }

    public class Source implements Parcelable {
      @SerializedName("name") private String name;
      @SerializedName("id") private String id;

      protected Source(Parcel in) {
        name = in.readString();
        id = in.readString();
      }

      public final Creator<Source> CREATOR = new Creator<Source>() {
        @Override
        public Source createFromParcel(Parcel in) {
          return new Source(in);
        }

        @Override
        public Source[] newArray(int size) {
          return new Source[size];
        }
      };

      public String getName() {
        return name;
      }

      public String getId() {
        return id;
      }

      @Override public int describeContents() {
        return 0;
      }

      @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(id);
      }
    }
  }
}
