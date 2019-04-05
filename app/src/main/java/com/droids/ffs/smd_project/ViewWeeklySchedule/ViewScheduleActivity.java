package com.droids.ffs.smd_project.ViewWeeklySchedule;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.droids.ffs.smd_project.MainActivity;
import com.droids.ffs.smd_project.R;
import com.droids.ffs.smd_project.SQLite.DBHandler;
import com.droids.ffs.smd_project.SelectCourse.SelectCourseActivity;
import com.droids.ffs.smd_project.SetReminderTime;


public class ViewScheduleActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DBHandler db;
    private CollapsingToolbarLayout collapse_toolbar;
    private ViewPager viewPager;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout; // The Nav-Drawer Layout
    private SwitchCompat switcher;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Running Fullscreen Mode
        MainActivity.runFullScreenMode(this);
        setContentView(R.layout.navigation_drawer);

        navigationView = findViewById(R.id.nav_view);



        Menu menu = navigationView.getMenu();
        MenuItem menuItem = menu.findItem(R.id.nav_alarm_toggle);
        View actionView = MenuItemCompat.getActionView(menuItem);

        switcher = actionView.findViewById(R.id.reminderswitch);
        switcher.setChecked(true);
        switcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getApplicationContext().getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putBoolean("runAlarm", switcher.isChecked());
                editor.commit();
                Toast.makeText(getApplicationContext(), (switcher.isChecked()) ? "Reminders enabled" : "Reminders disabled", Toast.LENGTH_SHORT).show();
            }
        });


        //View weekly Schedule
        ViewSchdedule();
        setDrawerAndToolBar(this);

        TabLayout tabLayout = findViewById(R.id.htab_tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setTabRippleColor(ColorStateList.valueOf(Color.WHITE));

        tabLayout.getTabAt(0).setText("Monday");
        tabLayout.getTabAt(1).setText("Tuesday");
        tabLayout.getTabAt(2).setText("Wednesday");
        tabLayout.getTabAt(3).setText("Thursday");
        tabLayout.getTabAt(4).setText("Friday");

    }

    public void ViewSchdedule() {

        viewPager = findViewById(R.id.htab_viewpager);
        collapse_toolbar = findViewById(R.id.htab_collapse_toolbar);
        Toolbar toolbar = findViewById(R.id.htab_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setSubtitle("Weekly Schedule");


        ViewSchedulePagerAdapter adapter = new ViewSchedulePagerAdapter(getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);
        //For direct clicking between pages
        viewPager.setOffscreenPageLimit(5);

    }

    // Nav-Drawer and ToolBar Creation
    @NonNull
    protected void setDrawerAndToolBar(Activity act) {

        Toolbar toolbar = findViewById(R.id.htab_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();

        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionbar.setTitle(null);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) act);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // If Back Button Pressed
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    // If the any options from the drawer menu is selected
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.selectTimeTable) {
            Intent filepicker = new Intent(Intent.ACTION_GET_CONTENT);
            filepicker.setType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            startActivityForResult(filepicker, 1);

        } else if (id == R.id.selectCourses) {
            Intent i = new Intent(this, SelectCourseActivity.class);
            startActivity(i);


        } else if (id == R.id.setReminder) {
            Intent i = new Intent(this, SetReminderTime.class);
            startActivity(i);

        } else if (id == R.id.nav_alarm_toggle) {
            Log.v("CHECKED", "inside if condition");
            switcher.setChecked(!switcher.isChecked());


            SharedPreferences pref = getApplicationContext().getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("runAlarm", switcher.isChecked());
            editor.commit();

            Toast.makeText(getApplicationContext(), (switcher.isChecked()) ? "Reminders enabled" : "Reminders disabled", Toast.LENGTH_SHORT).show();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }


}

