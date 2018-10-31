package com.example.mana.a4321football.ui.screens.mainscreen.screenContents.news;

import android.os.Handler;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.OnClick;
import com.example.mana.a4321football.R;
import com.example.mana.a4321football.data.eventbus.Details;
import com.example.mana.a4321football.data.model.News;
import com.example.mana.a4321football.ui.base.BaseFragment;
import com.example.mana.a4321football.utility.RecyclerConfigs;
import com.pnikosis.materialishprogress.ProgressWheel;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class NewsTab extends BaseFragment implements NewsResponse {

  @BindView(R.id.news_loader) ProgressWheel loader;
  @BindView(R.id.news_list) RecyclerView list;
  @BindView(R.id.technical_error) ImageView technicalImage;
  @BindView(R.id.technical_error_btn) Button technicalErrorButton;

  NewsPresenter presenter;

  public static NewsTab getInstance() {
    return new NewsTab();
  }

  @Override public int fragmentLayout() {
    return R.layout.fragment_news;
  }

  @Override public void init() {
    instantiateRecycler();
    Handler h = new Handler();
    h.postDelayed(this::instantiatePresenter, 1200);
  }

  @Override public void onStart() {
    super.onStart();
    EventBus.getDefault().register(this);
  }

  @Override public void onStop() {
    super.onStop();
    EventBus.getDefault().unregister(this);
  }

  @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
  public void busData(Details details) {
  }

  @OnClick({ R.id.technical_error_btn })
  public void onViewClicked(View v) {
    switch (v.getId()) {
      case R.id.technical_error_btn:
        instantiatePresenter();
        break;
    }
  }

  private void instantiatePresenter() {
    View[] views = { technicalErrorButton, technicalImage };
    presenter = new NewsPresenter(getContext(), disposables, this);
    presenter.loadNews(loader, views);
  }

  private void instantiateRecycler() {
    RecyclerConfigs.setupRecyclerSettings(list, getContext(), LinearLayoutManager.VERTICAL,
        DividerItemDecoration.HORIZONTAL);
  }

  @Override public void newsResponse(News data) {
    list.setAdapter(new NewsAdapter(data.getArticles()));
  }
}
