package com.example.mana.a4321football.ui.screens.mainscreen.screenContents.favorite;

import android.content.Context;
import android.util.Log;
import android.view.View;
import com.example.mana.a4321football.data.database.DatabaseConstruct;
import com.example.mana.a4321football.data.database.Favorite;
import com.example.mana.a4321football.ui.base.BasePresenter;
import com.example.mana.a4321football.utility.SharedPreferencesManager;
import com.example.mana.a4321football.utility.ToastMessages;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;


@SuppressWarnings({ "LambdaParameterTypeCanBeSpecified", "unchecked" })
public class FavoritePresenter extends BasePresenter {

  private presenterResponse response;
  private CompositeDisposable disposable;

  public FavoritePresenter(Context context,
      CompositeDisposable disposable, presenterResponse response) {
    super(context, disposable);
    this.response = response;
    this.disposable=disposable;
  }

  public void getShared(View noFavCont) {
    if (SharedPreferencesManager.retrievePref(context) == 0) {
      noFavCont.setVisibility(View.VISIBLE);
    } else {
      //TODO ADD SERVICE TO GET FAVORITE TEAM DATA !
      noFavCont.setVisibility(View.GONE);
    }
  }
  private Single getFavoriteListObservable() {

    DatabaseConstruct db = DatabaseConstruct.getInstance(context);
    return Single.create((SingleOnSubscribe) emitter -> {
      if (!emitter.isDisposed()) {
        List<Favorite> favorite = db.accessPoint().getFavoriteTeams();
        emitter.onSuccess(favorite);
      }
    });
  }
  private SingleObserver getFavoriteListObserver() {
    return new SingleObserver() {
      @Override public void onSubscribe(Disposable d) {
        disposable.add(d);
      }

      @Override public void onSuccess(Object o) {
        List<Favorite> favorites = (List<Favorite>) o;
        response.favoritedTeams(favorites);
      }

      @Override public void onError(Throwable e) {
        Log.d("edini", "onError: "+e.getMessage());      }
    };
  }
  public void getFavoriteList() {
    getFavoriteListObservable()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(getFavoriteListObserver());
  }

  @Override public void loadServiceData(Object model) {

  }
}
