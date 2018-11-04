package com.app.mana.a4321football.ui.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.ButterKnife;

public class BaseRecyclerHolder extends RecyclerView.ViewHolder {
  public BaseRecyclerHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this,itemView);
  }
}
