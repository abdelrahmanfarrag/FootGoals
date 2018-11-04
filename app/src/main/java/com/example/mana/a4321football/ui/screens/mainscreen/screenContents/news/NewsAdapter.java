package com.example.mana.a4321football.ui.screens.mainscreen.screenContents.news;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.example.mana.a4321football.R;
import com.example.mana.a4321football.data.model.News;
import com.example.mana.a4321football.ui.base.BaseRecyclerAdapter;
import com.example.mana.a4321football.ui.base.BaseRecyclerHolder;
import com.example.mana.a4321football.utility.AppUtils;
import com.squareup.picasso.Picasso;
import java.util.List;

public class NewsAdapter extends BaseRecyclerAdapter<News.Articles, BaseRecyclerHolder> {
  private NewsEvent event;

  NewsAdapter(
      List<News.Articles> recyclerItems, NewsEvent event) {
    super(recyclerItems);
    this.event = event;
  }

  @NonNull @Override
  public BaseRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = setRecyclerItemLayout(parent.getContext()).inflate(R.layout.single_news_item, parent,
        false);
    return new NewsHolder(v);
  }

  @RequiresApi(api = Build.VERSION_CODES.N) @Override
  public void onBindViewHolder(@NonNull BaseRecyclerHolder holder, int position) {
    NewsHolder hold = (NewsHolder) holder;
    int layoutPosition = hold.getLayoutPosition();
    String date = getItemAtPosition(layoutPosition).getPublished();
    Picasso.with(hold.itemView.getContext())
        .load(getItemAtPosition(layoutPosition).getImg())
        .into(hold.newsImage);
    hold.title.setText(getItemAtPosition(layoutPosition).getTitle());
    hold.source.setText(getItemAtPosition(layoutPosition).getSource().getName());
    hold.content.setText(getItemAtPosition(layoutPosition).getContent());
    hold.date.setText(AppUtils.transformTime(date));
  }

  class NewsHolder extends BaseRecyclerHolder {
    @BindView(R.id.news_img) ImageView newsImage;
    @BindView(R.id.news_title) TextView title;
    @BindView(R.id.news_source_name) TextView source;
    @BindView(R.id.news_source_content) TextView content;
    @BindView(R.id.news_date) TextView date;

    NewsHolder(View itemView) {
      super(itemView);
    }

    @OnClick()
    public void onClick() {
      event.newsWeb(getItemAtPosition(getLayoutPosition()).getUrl());
    }
  }
}
