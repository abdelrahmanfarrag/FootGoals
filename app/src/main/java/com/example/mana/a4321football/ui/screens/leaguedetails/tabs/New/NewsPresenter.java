package com.example.mana.a4321football.ui.screens.leaguedetails.tabs.New;

import android.content.Context;
import android.view.View;
import com.example.mana.a4321football.data.eventbus.LeagueBus;
import com.example.mana.a4321football.data.model.News;
import com.example.mana.a4321football.data.network.RetrofitServices;
import com.example.mana.a4321football.ui.base.BasePresenter;
import com.example.mana.a4321football.utility.AppUtils;
import com.example.mana.a4321football.utility.Constants;
import com.example.mana.a4321football.utility.ToastMessages;
import com.pnikosis.materialishprogress.ProgressWheel;
import io.reactivex.disposables.CompositeDisposable;
import java.util.HashMap;
import java.util.Map;

public class NewsPresenter extends BasePresenter {

  private NewsResponse response;

  public NewsPresenter(Context context, CompositeDisposable disposable, NewsResponse response) {
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

  public void loadNews(ProgressWheel wheel, View[] views) {
    if (!AppUtils.isOnline(context) || LeagueBus.getLeagueID() == null) {
      views[0].setVisibility(View.VISIBLE);
      views[1].setVisibility(View.VISIBLE);
    } else {
      services.getLeagueNews(wheel, newsMap());
      views[0].setVisibility(View.GONE);
      views[1].setVisibility(View.GONE);
    }
  }

  @Override public void loadServiceData(Object model) {
    News resp = (News) model;
    response.newsResponse(resp);
  }
}
