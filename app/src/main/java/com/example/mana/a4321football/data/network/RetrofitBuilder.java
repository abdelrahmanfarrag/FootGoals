package com.example.mana.a4321football.data.network;

import android.content.Context;
import butterknife.internal.Utils;
import com.example.mana.a4321football.BuildConfig;
import com.example.mana.a4321football.utility.AppUtils;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.mana.a4321football.utility.Constants.API_KEEY;
import static com.example.mana.a4321football.utility.Constants.AUTH_HEADER;
import static com.example.mana.a4321football.utility.Constants.BASE_URL;
import static com.example.mana.a4321football.utility.Constants.CACHE_SIZE;
import static com.example.mana.a4321football.utility.Constants.NEWS_API_BASE_URL;

public class RetrofitBuilder {

  public RetrofitApi getApi(Context context) {
    return new RetrofitBuilder().initRetrofitServices(context);
  }

  public RetrofitApi getNewsApi() {
    return new RetrofitBuilder().initNewsAPiService();
  }

  private RetrofitApi initRetrofitServices(Context context) {
    Retrofit retroInstance = new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(retrofiClient(context))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
        .build();
    return retroInstance.create(RetrofitApi.class);
  }

  private RetrofitApi initNewsAPiService() {
    Retrofit retroInstance = new Retrofit.Builder()
        .baseUrl(NEWS_API_BASE_URL)
        .client(newsRetroClient())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
        .build();
    return retroInstance.create(RetrofitApi.class);
  }

  private OkHttpClient newsRetroClient() {
    return new OkHttpClient.Builder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .addInterceptor(logging())
        .build();
  }

  private OkHttpClient retrofiClient(Context context) {
    int cacheSize = 10 * 1024 * 1024; // 10 MB
    Cache cache = new Cache(context.getCacheDir(), cacheSize);

    return new OkHttpClient.Builder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .cache(cache)
        .addInterceptor(chain -> chain.proceed(retroHeader(chain)))
        .addInterceptor(cachingInterceptor(context))
        .addInterceptor(logging())
        .build();
  }

  private Interceptor cachingInterceptor(Context context) {
    Interceptor cache_intercepter = chain -> {
      Request request = chain.request();
      if (AppUtils.isOnline(context)) {
        request = request.newBuilder().header("Cache-Control", "public, max-age=" + 60).build();
      } else {
        request = request.newBuilder()
            .header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7)
            .build();
      }
      return chain.proceed(request);
    };

    return cache_intercepter;
  }

  private HttpLoggingInterceptor logging() {
    return new HttpLoggingInterceptor()
        .setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
            : HttpLoggingInterceptor.Level.NONE)
        ;
  }

  private Request retroHeader(Interceptor.Chain chain) {
    Request.Builder builder = chain.request().newBuilder()
        .addHeader(AUTH_HEADER, API_KEEY);

    return builder.build();
  }
}
