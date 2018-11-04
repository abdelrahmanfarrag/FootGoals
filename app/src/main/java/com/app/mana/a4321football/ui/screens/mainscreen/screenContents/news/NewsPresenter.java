package com.app.mana.a4321football.ui.screens.mainscreen.screenContents.news;

import android.content.Context;

import com.app.mana.a4321football.data.model.News;
import com.app.mana.a4321football.ui.base.BasePresenter;
import com.app.mana.a4321football.utility.Constants;
import com.pnikosis.materialishprogress.ProgressWheel;
import io.reactivex.disposables.CompositeDisposable;
import java.util.HashMap;
import java.util.Map;

public class NewsPresenter extends BasePresenter {

  private NewsResponse response;

  NewsPresenter(Context context, CompositeDisposable disposable, NewsResponse response) {
    super(context, disposable);
    this.response = response;
  }

  private Map<String, String> newsMap() {
    Map<String, String> data = new HashMap<>();
    data.put("category", "sports");
    data.put("country", "gb");
    data.put(Constants.NEWS_KEY, Constants.NEWS_API_KEEY);
    return data;
  }

  public void loadNews(ProgressWheel wheel) {

    services.getLeagueNews(wheel, newsMap());
  }

  @Override public void loadServiceData(Object model) {
    News resp = (News) model;
    response.newsResponse(resp);
  }
}
