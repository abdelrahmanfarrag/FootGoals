package com.app.mana.a4321football.ui.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import java.util.List;

public abstract class BaseRecyclerAdapter<T, VH extends BaseRecyclerHolder>
    extends RecyclerView.Adapter<VH> {
  List<T> recyclerItems;

  public BaseRecyclerAdapter(List<T> recyclerItems) {
    this.recyclerItems = recyclerItems;
  }

  protected LayoutInflater setRecyclerItemLayout(Context context) {
    return LayoutInflater.from(context);
  }

  protected T getItemAtPosition(int position) {
    return recyclerItems.get(position);
  }

  @Override public int getItemCount() {
    return recyclerItems.size();
  }
}
