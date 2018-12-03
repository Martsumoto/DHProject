package br.com.doghero.dhproject;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.FrameLayout;

import br.com.doghero.dhproject.myHeroes.MyHeroesListFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * https://www.truiton.com/2017/01/android-bottom-navigation-bar-example/
 */
public class MainAcitivity extends FragmentActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

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

        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        mViewModel = ViewModelProviders.of(this).get(DHViewModel.class);

        this.setupFirstFragment();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;

        switch (item.getItemId()) {
            case R.id.action_search:
                selectedFragment = this.getMyHeroesListFragment();
                break;
            case R.id.action_messages:
            case R.id.action_heroes:
            case R.id.action_bookings:
            case R.id.action_menu:
                selectedFragment = new EmptyFragment();
                break;
        }

        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, selectedFragment);
        transaction.commit();

        return true;
    }

    private void setupFirstFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, this.getMyHeroesListFragment());
        transaction.commit();
    }


    private MyHeroesListFragment getMyHeroesListFragment() {
        if (this.mMyHeroesListFragment == null) {
            this.mMyHeroesListFragment = new MyHeroesListFragment();
            this.mMyHeroesListFragment.setViewModel(this.mViewModel);
        }

        return this.mMyHeroesListFragment;
    }
}
