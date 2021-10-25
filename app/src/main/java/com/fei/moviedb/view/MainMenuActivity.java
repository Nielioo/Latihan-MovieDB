package com.fei.moviedb.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.fei.moviedb.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainMenuActivity extends AppCompatActivity {

    BottomNavigationView mainMenu_bottomNavigation;
    NavHostFragment mainMenu_navFragment;
    Toolbar mainMenu_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        initialize();

    }

    private void initialize() {
        mainMenu_bottomNavigation = findViewById(R.id.mainMenu_bottomNavigation);
        mainMenu_navFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.mainMenu_navFragment);

        mainMenu_toolbar = findViewById(R.id.mainMenu_toolbar);
        setSupportActionBar(mainMenu_toolbar);

        //Dipakai kalau pakai action bar
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.nowPlayingFragment, R.id.upcomingFragment).build();
        NavigationUI.setupActionBarWithNavController(MainMenuActivity.this, mainMenu_navFragment.getNavController(), appBarConfiguration);

        NavigationUI.setupWithNavController(mainMenu_bottomNavigation, mainMenu_navFragment.getNavController());

    }

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }
}