package br.com.doghero.dhproject.myHeroes;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import br.com.doghero.dhproject.DHViewModel;
import br.com.doghero.dhproject.R;
import br.com.doghero.dhproject.model.Hero;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MyHeroesListFragment extends Fragment implements  MyHeroesListView {

    @BindView(R.id.listHeroesRecents)
    RecyclerView mListHeroesRecents;
    @BindView(R.id.listHeroesFavorites)
    RecyclerView mListHeroesFavorites;

    @BindView(R.id.progressBarRecents)
    ProgressBar mProgressBarRecents;
    @BindView(R.id.progressBarFavorites)
    ProgressBar mProgressBarFavorites;

    private MyHeroesAdapter mAdapterRecents;
    private MyHeroesAdapter mAdapterFavorites;

    private DHViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_heroes_list_fragment, container, false);
        ButterKnife.bind(this,view);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.showProgressBar(true, true);
        this.showProgressBar(true, false);

        this.mViewModel.getHeroListRecentsLive().observe(this, heroes -> {
            this.showHeroes(heroes, true);
        });
        this.mViewModel.getHeroListFavoritesLive().observe(this, heroes -> {
            this.showHeroes(heroes, false);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private MyHeroesAdapter showHeroes(List<Hero> heroList, boolean isRecents) {
        MyHeroesAdapter adapter;
        RecyclerView list;

        if (isRecents) {
            list = this.mListHeroesRecents;
        } else {
            list = this.mListHeroesFavorites;
        }

        this.showProgressBar(false, isRecents);

        adapter = new MyHeroesAdapter(this, heroList);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return adapter;
    }

    private void showProgressBar(boolean showProgressBar, boolean isRecents) {
        RecyclerView list;
        ProgressBar progressBar;

        if (isRecents) {
            list = this.mListHeroesRecents;
            progressBar = this.mProgressBarRecents;
        } else {
            list = this.mListHeroesFavorites;
            progressBar = this.mProgressBarFavorites;
        }

        if (showProgressBar) {
            list.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            list.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onChatButtonClick(Hero hero) {
        //TODO open chat screen
        Toast.makeText(this.getContext(), "TODO mostrar conversa", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBookButtonClick(Hero hero) {
        // TODO open book screen
        Toast.makeText(this.getContext(), "TODO mostrar reserva", Toast.LENGTH_SHORT).show();
    }

    public void setViewModel(DHViewModel mViewModel) {
        this.mViewModel = mViewModel;
    }
}
