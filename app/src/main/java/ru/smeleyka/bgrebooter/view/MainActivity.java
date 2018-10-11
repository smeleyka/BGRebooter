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
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.smeleyka.bgrebooter.R;
import ru.smeleyka.bgrebooter.model.entity.HostgroupGetResponse;
import ru.smeleyka.bgrebooter.presenter.MainActivityPresenter;
import ru.smeleyka.bgrebooter.presenter.RebootPresenter;
import ru.smeleyka.bgrebooter.view.fragments.HostgroupFragment;

public class MainActivity extends AppCompatActivity implements MainActivityView {
    String TAG = "MainActivity.class";
    MainActivityPresenter mainActivityPresenter;
    HostgroupFragment hostgroupFragment;
    ArrayList<HostgroupGetResponse.Hostgroup> hostgroupArrayList = new ArrayList<>();

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
        animatedDrawerAppBarButton();
        startFragment();

        //createDrawerMenu();
        //drawerLayout.closeDrawers();
        this.mainActivityPresenter = new MainActivityPresenter(this, AndroidSchedulers.mainThread());
    }

    private void init() {
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationItemSelectedListener());
    }

    private void animatedDrawerAppBarButton() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void createDrawerMenu(String name) {
        Menu menu = navigationView.getMenu();
        menu.add(name).setIcon(R.drawable.ic_group);
    }

    @Override
    public void addMenuItem(String name) {
        createDrawerMenu(name);
    }

    public void addMenuItem(HostgroupGetResponse.Hostgroup hostgroup) {
        hostgroupArrayList.add(hostgroup);
        Log.d(TAG,""+hostgroupArrayList.size());
        //hostgroupFragment.addItemToRecyclerView(hostgroup);
    }

    void startFragment() {
        hostgroupFragment = new HostgroupFragment();
        android.app.FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.fragment_container, hostgroupFragment, "TAG").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
    }

    @Override
    public void showResult(String result) {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    //Close Drawer if opened on Back Press
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    class NavigationItemSelectedListener implements NavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            navigationView.setCheckedItem(item.getItemId());
            switch (item.getNumericShortcut()) {
                case '0': {
                    mainActivityPresenter.getHostsGroup();
                    Toast toast = Toast.makeText(getApplicationContext(), "Item pressed " + item.toString() + " " + item.getNumericShortcut(), Toast.LENGTH_SHORT);
                    toast.show();
                    break;
                }

                case '1': {
                   //hostgroupFragment.addItemToRecyclerView(hostgroupArrayList.get(0));
                    hostgroupFragment.inflateView();
                    Toast toast = Toast.makeText(getApplicationContext(), "Item pressed " + hostgroupArrayList.get(0).getName() + " " + item.getNumericShortcut(), Toast.LENGTH_SHORT);
                    toast.show();
                    break;

                }
                case '2': {
                    Toast toast = Toast.makeText(getApplicationContext(), "Item pressed " + item.toString() + " " + item.getNumericShortcut(), Toast.LENGTH_SHORT);
                    toast.show();
                    break;

                }
                case '3': {
                    Toast toast = Toast.makeText(getApplicationContext(), "Item pressed " + item.toString() + " " + item.getNumericShortcut(), Toast.LENGTH_SHORT);
                    toast.show();
                    break;

                }
                default: {
                    Log.d(TAG, "Default Item Pressed");
//                    item.setChecked(true);
//                    Toast toast = Toast.makeText(getApplicationContext(), "Item pressed " + item.toString() + " " + item.getNumericShortcut(), Toast.LENGTH_SHORT);
//                    toast.show();
                    break;

                }
            }


//            Toast toast = Toast.makeText(getApplicationContext(), "Item pressed " + item.toString(), Toast.LENGTH_SHORT);
//            toast.show();
            //drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }
    }

}
