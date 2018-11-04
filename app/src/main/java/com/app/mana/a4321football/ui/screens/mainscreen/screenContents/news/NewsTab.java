package com.app.mana.a4321football.ui.screens.mainscreen.screenContents.news;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import com.app.mana.a4321football.R;
import com.app.mana.a4321football.data.model.News;
import com.app.mana.a4321football.ui.base.BaseFragment;
import com.app.mana.a4321football.utility.RecyclerConfigs;
import com.pnikosis.materialishprogress.ProgressWheel;
import java.util.List;

public class NewsTab extends BaseFragment implements NewsResponse, NewsEvent {

  @BindView(R.id.news_loader) ProgressWheel loader;
  @BindView(R.id.news_list) RecyclerView list;

  NewsPresenter presenter;

  public static NewsTab getInstance() {
    return new NewsTab();
  }

  @Override public int fragmentLayout() {
    return R.layout.fragment_news;
  }

  @Override public void init() {
    instantiateRecycler();
    instantiatePresenter();
  }

  private void instantiatePresenter() {
    presenter = new NewsPresenter(getContext(), disposables, this);
    presenter.loadNews(loader);
  }

  private void instantiateRecycler() {
    RecyclerConfigs.setupRecyclerSettings(list, getContext(), LinearLayoutManager.VERTICAL,
        DividerItemDecoration.HORIZONTAL);
  }

  @Override public void newsResponse(News data) {
    List<News.Articles> news = data.getArticles();
    list.setAdapter(new NewsAdapter(news, this));
  }

  @Override public void newsWeb(String url) {
    Intent i = new Intent(Intent.ACTION_VIEW);
    i.setData(Uri.parse(url));
    startActivity(i);
  }
}
