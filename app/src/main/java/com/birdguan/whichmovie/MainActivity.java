package com.birdguan.whichmovie;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.birdguan.whichmovie.base.BaseActivity;
import com.birdguan.whichmovie.ui.fragment.FavouriteFragment;
import com.birdguan.whichmovie.ui.fragment.RandomFragment;
import com.birdguan.whichmovie.ui.fragment.WatchedFragment;
import com.google.android.material.navigation.NavigationView;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends BaseActivity {
    private DrawerLayout drawerLayout;
    private TextView textViewNavUsername;
    private BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerLayout = navigationView.getHeaderView(0);
        textViewNavUsername = headerLayout.findViewById(R.id.nav_username);
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        // 设置NavigationView中的username
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        textViewNavUsername.setText(username);
        // 设置ActionBar样式
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        // 监听BottomBar事件
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                switch (tabId) {
                    // BottomBar "已观看"按钮
                    case R.id.tab_watched:
                        replaceFragment(new WatchedFragment());
                        break;
                    // BottomBar "看缘分"按钮
                    case R.id.tab_random:
                        replaceFragment(new RandomFragment());
                        break;
                    // BottomBar "收藏夹"按钮
                    case R.id.tab_favourite:
                        replaceFragment(new FavouriteFragment());
                        break;
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

    /**
     * 替换Fragment
     * @param fragment 自定义的Fragment类
     */
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.commit();
    }
}
