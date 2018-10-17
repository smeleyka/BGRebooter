package ru.smeleyka.bgrebooter.view;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.smeleyka.bgrebooter.R;
import ru.smeleyka.bgrebooter.model.entity.HostgroupGetResponse;
import ru.smeleyka.bgrebooter.presenter.MainActivityPresenter;
import ru.smeleyka.bgrebooter.view.fragments.HostgroupFragment;

public class MainActivity extends AppCompatActivity implements MainActivityView {
    String TAG = "MainActivity.class";
    MainActivityPresenter mainActivityPresenter;
    HostgroupFragment hostgroupFragment;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

        // showHostgroupFragment();

        this.mainActivityPresenter = new MainActivityPresenter(this, AndroidSchedulers.mainThread());
    }

    private void init() {
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

        //Init ActionBar
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        //Animated ActionBar Drawer toggle button
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //Drawer menu click listener
        navigationView.setNavigationItemSelectedListener(new NavigationItemSelectedListener());

//        if (hostgroupFragment == null) {
//            hostgroupFragment = new HostgroupFragment();
//        }
//        android.app.FragmentManager fm = getFragmentManager();
//        fm.beginTransaction().replace(R.id.fragment_container, hostgroupFragment, "TAG").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();

    }

    private void createDrawerMenu(String name) {
        Menu menu = navigationView.getMenu();
        menu.add(name).setIcon(R.drawable.ic_group);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainActivityPresenter.onStart();
    }

    @Override
    public void showHostgroupFragment(Observable<HostgroupGetResponse.Hostgroup> hostgroupObservable) {
        if (hostgroupFragment == null) {
            hostgroupFragment = new HostgroupFragment();
        }
        android.app.FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.fragment_container, hostgroupFragment, "TAG").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commitNow();
        hostgroupFragment.subscribeToHostsGroup(mainActivityPresenter.getHostGroupeObseravable());
    }

    @Override
    public void addMenuItem(String name) {
        createDrawerMenu(name);
    }

    @Override
    public void showResult(String result) {

    }

    @Override
    public void showError(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onBackPressed() {
        //Close Drawer if opened on Back Press
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void goBack() {
        super.onBackPressed();
    }

    class NavigationItemSelectedListener implements NavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            navigationView.setCheckedItem(item.getItemId());
            switch (item.getItemId()) {
                case R.id.show_hosts_group: {
                    showHostgroupFragment(mainActivityPresenter.getHostGroupeObseravable());
                    Toast toast = Toast.makeText(getApplicationContext(), "Item pressed " + item.toString(), Toast.LENGTH_SHORT);
                    toast.show();
                    break;
                }

                case R.id.show_triggers: {
                    Toast toast = Toast.makeText(getApplicationContext(), "Item pressed " + item.toString(), Toast.LENGTH_SHORT);
                    toast.show();
                    break;

                }
                case R.id.show_test: {
                    Toast toast = Toast.makeText(getApplicationContext(), "Item pressed " + item.toString(), Toast.LENGTH_SHORT);
                    toast.show();
                    break;

                }
                case R.id.logout: {
                    mainActivityPresenter.cleanAuth();
                    Toast toast = Toast.makeText(getApplicationContext(), "Item pressed " + item.toString(), Toast.LENGTH_SHORT);
                    toast.show();
                    break;

                }
                default: {
                    Log.d(TAG, "Default Item Pressed");
                    break;

                }
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }
    }

}


//drawerLayout.closeDrawers();         close Drawer