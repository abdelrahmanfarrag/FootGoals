package com.example.mana.a4321football.ui.screens.leaguedetails.tabs.Teams.team_info;

import android.content.Context;
import com.example.mana.a4321football.R;
import com.example.mana.a4321football.data.database.DatabaseConstruct;
import com.example.mana.a4321football.data.database.Favorite;
import com.example.mana.a4321football.data.model.Teams;
import com.example.mana.a4321football.ui.base.BasePresenter;
import com.example.mana.a4321football.utility.ToastMessages;
import com.pnikosis.materialishprogress.ProgressWheel;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@SuppressWarnings("ALL") public class InfoPresenter extends BasePresenter {

  private InfoResponse response;
  private CompositeDisposable disposables = new CompositeDisposable();

  public InfoPresenter(Context context,
      CompositeDisposable disposable, InfoResponse response) {
    super(context, disposable);
    this.response = response;
  }

  public void loadTeamsSquad(int id, ProgressWheel wheel) {
    services.getTeamInfo(wheel, id);
  }

  @Override public void loadServiceData(Object model) {

    Teams squad = (Teams) model;
    response.squad(squad.getSquads());
  }

  private Single addToFavoriteObservable(int id, String name, String path) {
    DatabaseConstruct db = DatabaseConstruct.getInstance(context);
    return Single.create((SingleOnSubscribe) (SingleEmitter emitter) -> {
      if (!emitter.isDisposed()) {
        Favorite favorite = new Favorite(id, path
            , name);
        db.accessPoint().insertMovieToFavorite(favorite);
        emitter.onSuccess(favorite);
      }
    });
  }

  private SingleObserver addToFavoriteObserver() {
    return new SingleObserver() {
      @Override public void onSubscribe(Disposable d) {
        disposables.add(d);
      }

      @Override public void onSuccess(Object o) {
        ToastMessages.ShortToastMessage(context,
            context.getResources().getString(R.string.added_msg));
      }

      @Override public void onError(Throwable e) {

      }
    };
  }

  @SuppressWarnings("unchecked") public void insertNewTeam(int id, String name, String path) {
    addToFavoriteObservable(id, name, path)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(addToFavoriteObserver());
  }
}
