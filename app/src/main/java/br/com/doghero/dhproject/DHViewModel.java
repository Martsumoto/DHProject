package br.com.doghero.dhproject;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import br.com.doghero.dhproject.api.ApiAnswer;
import br.com.doghero.dhproject.model.Hero;

public class DHViewModel extends ViewModel {

    private MutableLiveData<List<Hero>> heroListRecentsLive = new MutableLiveData<>();
    private MutableLiveData<List<Hero>> heroListFavoritesLive = new MutableLiveData<>();

    public DHViewModel() {
        this.fetchHeroes();
    }

    public MutableLiveData<List<Hero>> getHeroListRecentsLive() {
        return heroListRecentsLive;
    }

    public MutableLiveData<List<Hero>> getHeroListFavoritesLive() {
        return heroListFavoritesLive;
    }

    private void fetchHeroes() {
        Executor.getInstance().getExecutorService().execute(() -> {
            heroListRecentsLive.postValue(
                    MyHeroes.getHeroList(ApiAnswer.getMyHeroes(), MyHeroes.RECENTS_HERO_TYPE));

            heroListFavoritesLive.postValue(
                    MyHeroes.getHeroList(ApiAnswer.getMyHeroes(), MyHeroes.FAVORITES_HERO_TYPE));
        });
    }

}
