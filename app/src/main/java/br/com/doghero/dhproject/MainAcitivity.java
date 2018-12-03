package br.com.doghero.dhproject;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.FrameLayout;

import br.com.doghero.dhproject.myHeroes.MyHeroesListFragment;
import br.com.doghero.dhproject.util.BottomNavigationMenuUtil;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * https://www.truiton.com/2017/01/android-bottom-navigation-bar-example/
 */
public class MainAcitivity extends FragmentActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String MY_HEROES_FRAGMENT = "MY_HEROES_FRAGMENT";
    private static final String SELECTED_FRAGMENT = "SELECTED_FRAGMENT";

    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;

    @BindView(R.id.frameLayout)
    FrameLayout mFrameLayout;

    private MyHeroesListFragment mMyHeroesListFragment = null;

    private DHViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

        BottomNavigationMenuUtil.removePaddingFromActiveItem(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        mViewModel = ViewModelProviders.of(this).get(DHViewModel.class);

        int selectedFragment = R.id.action_search;

        if (savedInstanceState != null) {
            FragmentManager fm = getSupportFragmentManager();
            mMyHeroesListFragment = (MyHeroesListFragment) fm.findFragmentByTag(MY_HEROES_FRAGMENT);

            selectedFragment = savedInstanceState.getInt(SELECTED_FRAGMENT);
        }
        this.replaceFragment(selectedFragment);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTED_FRAGMENT, bottomNavigationView.getSelectedItemId());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        this.replaceFragment(item.getItemId());
        return true;
    }

    private void replaceFragment(int id) {
        Fragment selectedFragment = null;
        String tag = null;

        switch (id) {
            case R.id.action_search:
                selectedFragment = this.getMyHeroesListFragment();
                tag = MY_HEROES_FRAGMENT;
                break;
            case R.id.action_messages:
            case R.id.action_heroes:
            case R.id.action_bookings:
            case R.id.action_menu:
                selectedFragment = new EmptyFragment();
                break;
        }

        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, selectedFragment, tag);
        transaction.commit();
    }

    private MyHeroesListFragment getMyHeroesListFragment() {
        if (this.mMyHeroesListFragment == null) {
            this.mMyHeroesListFragment = new MyHeroesListFragment();
            this.mMyHeroesListFragment.setViewModel(this.mViewModel);
            this.mMyHeroesListFragment.setRetainInstance(true);
        }

        return this.mMyHeroesListFragment;
    }
}
