package com.example.mana.a4321football.ui.screens.leaguedetails.tabs.Teams.team_info;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import com.example.mana.a4321football.R;
import com.example.mana.a4321football.data.database.DatabaseConstruct;
import com.example.mana.a4321football.data.database.DbAccessPoint;
import com.example.mana.a4321football.data.database.Favorite;
import com.example.mana.a4321football.data.model.Teams;
import com.example.mana.a4321football.ui.base.BasePresenter;
import com.example.mana.a4321football.utility.ToastMessages;
import com.pnikosis.materialishprogress.ProgressWheel;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import java.util.Observable;

@SuppressWarnings("ALL") public class InfoPresenter extends BasePresenter {

  private InfoResponse response;
  private CompositeDisposable disposables = new CompositeDisposable();
  private boolean isFound;
  DatabaseConstruct db;

  public InfoPresenter(Context context,
      CompositeDisposable disposable, InfoResponse response) {
    super(context, disposable);
    this.response = response;
    db = DatabaseConstruct.getInstance(context);
  }

  public void loadTeamsSquad(int id, ProgressWheel wheel) {
    services.getTeamInfo(wheel, id);
  }

  @Override public void loadServiceData(Object model) {

    Teams squad = (Teams) model;
    response.squad(squad.getSquads());
  }

  private Single addToFavoriteObservable(int id, String name, String path) {
    return Single.create((SingleOnSubscribe) (SingleEmitter emitter) -> {
      if (!emitter.isDisposed()) {
        db.accessPoint().insertMovieToFavorite(new Favorite(id, path, name));
        emitter.onSuccess("added");
      }
    });
  }

  private SingleObserver addToFavoriteObserver() {
    DatabaseConstruct db = DatabaseConstruct.getInstance(context);
    return new SingleObserver() {
      @Override public void onSubscribe(Disposable d) {
        disposables.add(d);
      }

      @Override public void onSuccess(Object o) {
        ToastMessages.ShortToastMessage(context,
            context.getResources().getString(R.string.added_msg));
      }

      @Override public void onError(Throwable e) {
        e.getMessage();
      }
    };
  }

  @SuppressWarnings("unchecked") public void insertNewTeam(final int id, String name, String path) {
    addToFavoriteObservable(id, name, path)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(addToFavoriteObserver());
  }

}
