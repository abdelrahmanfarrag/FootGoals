package com.app.mana.a4321football.ui.base;

import android.content.Context;
import com.app.mana.a4321football.data.network.RetrofitServices;
import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter implements RetrofitServices.transformServiceResponse {

  protected RetrofitServices services;
  protected Context context;

  public BasePresenter(Context context, CompositeDisposable disposable) {
    this.context = context;
    services = new RetrofitServices(context, disposable, this);
  }

  public abstract void loadServiceData(Object model);

  @Override public void responseReceived(Object model) {
    loadServiceData(model);
  }
}
