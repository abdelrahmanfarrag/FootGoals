package com.example.mana.a4321football.utility;

import android.content.Context;
import android.net.Uri;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import java.util.Collections;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;

public class PicassoConfigs {

  public static Picasso picassoInstance(Context context) {
    final OkHttpClient client = new OkHttpClient.Builder()
        .protocols(Collections.singletonList(Protocol.HTTP_1_1))
        .build();

    //Picasso.setSingletonInstance(picasso);
    return new Picasso.Builder(context)
        .loggingEnabled(true)
        .listener((picasso, uri, exception) -> ToastMessages.ShortToastMessage(context,
            exception.getMessage()))
        .downloader(new OkHttp3Downloader(client))
        .build();
  }
}
