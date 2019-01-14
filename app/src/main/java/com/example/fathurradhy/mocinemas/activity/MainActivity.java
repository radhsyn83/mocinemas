package com.example.fathurradhy.mocinemas.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import com.example.fathurradhy.mocinemas.R;
import com.example.fathurradhy.mocinemas.adapter.ViewPagerAdapter;
import com.example.fathurradhy.mocinemas.fragment.ComingSoonActivity;
import com.example.fathurradhy.mocinemas.fragment.NowPlayingFragment;
import com.example.fathurradhy.mocinemas.utils.BottomNavigationHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity{
    private MaterialSearchView mSearchView;
    private ViewPager mViewPager;

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

        BottomNavigationView mBottomNavigationView = findViewById(R.id.bottomNavigation);
        BottomNavigationHelper.removeShiftMode(mBottomNavigationView);
        mBottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.action_now_playing:
                    mViewPager.setCurrentItem(0);
                    return true;
                case R.id.action_coming_soon:
                    mViewPager.setCurrentItem(1);
                    return true;
            }
            return false;
        });

        mViewPager = findViewById(R.id.view_pager);
        setupViewPager(mViewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        NowPlayingFragment nowPlaying = new NowPlayingFragment();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_language){
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }

        return super.onOptionsItemSelected(item);
    }
}
