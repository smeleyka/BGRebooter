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
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.smeleyka.bgrebooter.R;
import ru.smeleyka.bgrebooter.presenter.RebootPresenter;
import ru.smeleyka.bgrebooter.view.fragments.HostgroupFragment;

public class MainActivity extends AppCompatActivity implements MainActivityView {
    RebootPresenter rebootPresenter;

    @BindView(R.id.toolbar)
     Toolbar toolbar;

    @BindView(R.id.drawer_layout)
     DrawerLayout drawerLayout;

    @BindView(R.id.nav_view)
     NavigationView navigationView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        navigationView.setOnClickListener(new NavigationViewOnclick());
        navigationView.setNavigationItemSelectedListener(new NavigationItemSelectedListener());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        startFragment();

        //drawerLayout.closeDrawers();

        //rebootPresenter = new RebootPresenter(this, AndroidSchedulers.mainThread());
        //startFragment();
    }

    void startFragment() {
        HostgroupFragment fragment = new HostgroupFragment();
        android.app.FragmentManager fm = getFragmentManager();
        fm.beginTransaction().add(R.id.fragment_container, fragment, "TAG").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
    }


        @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
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

    class NavigationViewOnclick implements NavigationView.OnClickListener {

        @Override
        public void onClick(View view) {
            Toast toast = Toast.makeText(getApplicationContext(), "Button pressed " + view.getId(), Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    class NavigationItemSelectedListener implements NavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.show_hosts_group: {
                    item.setChecked(true);
                    Toast toast = Toast.makeText(getApplicationContext(), "Item pressed " + item.toString()+" "+item.getOrder(), Toast.LENGTH_SHORT);
                    toast.show();
                }

                case R.id.show_test: {
                    item.setChecked(true);

                    Toast toast = Toast.makeText(getApplicationContext(), "Item pressed " + item.toString()+" "+item.getOrder(), Toast.LENGTH_SHORT);
                    toast.show();
                }
                case R.id.show_triggers: {
                    item.setChecked(true);

                    Toast toast = Toast.makeText(getApplicationContext(), "Item pressed " + item.toString()+" "+item.getOrder(), Toast.LENGTH_SHORT);
                    toast.show();
                }
                case R.id.logout: {
                    item.setChecked(true);

                    Toast toast = Toast.makeText(getApplicationContext(), "Item pressed " + item.toString()+" "+item.getOrder(), Toast.LENGTH_SHORT);
                    toast.show();
                }
                default: {
                    item.setChecked(true);

                    Toast toast = Toast.makeText(getApplicationContext(), "Item pressed " + item.toString()+" "+item.getOrder(), Toast.LENGTH_SHORT);
                    toast.show();
                }
            }


//            Toast toast = Toast.makeText(getApplicationContext(), "Item pressed " + item.toString(), Toast.LENGTH_SHORT);
//            toast.show();
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }
    }

}
