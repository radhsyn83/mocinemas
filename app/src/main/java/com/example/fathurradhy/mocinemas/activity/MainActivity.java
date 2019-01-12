package com.example.fathurradhy.mocinemas.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.fathurradhy.mocinemas.R;
import com.example.fathurradhy.mocinemas.adapter.ViewPagerAdapter;
import com.example.fathurradhy.mocinemas.fragment.ComingSoonActivity;
import com.example.fathurradhy.mocinemas.fragment.NoewPlayingFragment;
import com.example.fathurradhy.mocinemas.utils.BottomNavigationHelper;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

public class MainActivity extends AppCompatActivity{
    private MaterialSearchView mSearchView;
    private ViewPager mViewPager;
    private BottomNavigationView mBottomNavigationView;
    private ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSearchView = findViewById(R.id.sv);

        mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent i = new Intent(MainActivity.this, SearchActivity.class);
                i.putExtra("query", query);
                startActivity(i);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        mBottomNavigationView = findViewById(R.id.bottomNavigation);
        BottomNavigationHelper.removeShiftMode(mBottomNavigationView);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_now_playing:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.action_coming_soon:
                        mViewPager.setCurrentItem(1);
                        break;
                }
                return false;
            }
        });

        mViewPager = findViewById(R.id.view_pager);
        setupViewPager(mViewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        NoewPlayingFragment nowPlaying = new NoewPlayingFragment();
        ComingSoonActivity comingSoon = new ComingSoonActivity();
        mViewPagerAdapter.addFragment(nowPlaying);
        mViewPagerAdapter.addFragment(comingSoon);
        viewPager.setAdapter(mViewPagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        mSearchView.setMenuItem(item);

        return true;
    }
}
