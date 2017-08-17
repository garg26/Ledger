package com.example.kartikeya.ledger.activity;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.kartikeya.ledger.R;
import com.example.kartikeya.ledger.fragment.AccountInformation;
import com.example.kartikeya.ledger.fragment.DrawerFragment;
import com.example.kartikeya.ledger.fragment.HomeFragment;

import simplifii.framework.activity.BaseActivity;

public class HomeActivity extends BaseActivity implements DrawerLayout.DrawerListener {

    private boolean isDrawerOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setDrawer(toolbar);
        addFragment(new HomeFragment(), true);

    }

    public void setDrawer(Toolbar toolbar) {
        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawerOperation(drawerLayout);
                }
            });
        }

        drawerLayout.addDrawerListener(this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        addDrawerFragment(toolbar, drawerLayout, fragmentManager);
        initNavigationDrawer();
    }

    protected void initNavigationDrawer() {

        NavigationView navigationView = (NavigationView) findViewById(R.id.lay_drawer);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return false;
            }
        });

    }

    private void drawerOperation(DrawerLayout drawerLayout) {
        if (isDrawerOpen) {
            drawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            drawerLayout.openDrawer(Gravity.LEFT);
        }
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(View drawerView) {
        isDrawerOpen = true;
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        isDrawerOpen = false;
    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    protected void addDrawerFragment(Toolbar toolbar, DrawerLayout drawerLayout, FragmentManager fragmentManager) {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        DrawerFragment drawerFragment = DrawerFragment.getInstance(this, drawerLayout);
        fragmentManager.beginTransaction().replace(R.id.lay_drawer, drawerFragment).commit();

    }

    public void addFragment(Fragment fragment, boolean b) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        getSupportFragmentManager().popBackStack();
        if (b) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.fragment_container, fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_customer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_customer:
                startNextActivity(AccountInformation.class);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}

