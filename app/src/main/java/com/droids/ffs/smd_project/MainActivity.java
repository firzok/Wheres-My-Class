package com.droids.ffs.smd_project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.droids.ffs.smd_project.SQLite.DBHandler;
import com.droids.ffs.smd_project.ViewWeeklySchedule.ViewScheduleActivity;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    public final static String PREFS_NAME = "wheresMyClass";    //This is the preference file that includes first time launch boolean
    public static Context context;

    DBHandler db;

    // Function that runs the calling activity in full Screen mode
    public static void runFullScreenMode(Activity act) {
        act.requestWindowFeature(Window.FEATURE_NO_TITLE);
        act.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        MainActivity.context = getApplicationContext();

        // Running Fullscreen Mode
        runFullScreenMode(this);

        if (Build.VERSION.SDK_INT >= 21)
            getWindow().setNavigationBarColor(Color.BLACK);

        InputStream classInput = getResources().openRawResource(R.raw.classes);
        InputStream courseInput = getResources().openRawResource(R.raw.courselist);
//
        AsyncTaskRunner runner = new AsyncTaskRunner();
        runner.execute(courseInput, classInput);
//
//

        // Setting View for layout
        setContentView(R.layout.activity_main);
        setTheme(R.style.AppTheme);


        // Run Splash Screen : See func for futher details
        runSplashScreenHandler();


    }

    // This function will run the splash screen after checking first time usage
    protected void runSplashScreenHandler() {
        //App used for the first time?
        if (appUsedForFirstTime()) {

            //Running the splash screen
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(MainActivity.this, Add_Table.class);
                    startActivity(i);
                    finish();
                }
            }, 1500);
        }

        // Not using for the first time
        else {

            //Running the splash screen
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(MainActivity.this, ViewScheduleActivity.class);
                    startActivity(i);
                    finish();
                }
            }, 1500);
        }
    }

    // First time usage checker funciton
    protected boolean appUsedForFirstTime() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        if (settings.getBoolean("first_time_use?", true)) {
            //the app is being launched for first time, do something
            Log.d("First Time?", "Yes");

            // first time task

            // record the fact that the app has been started at least once
            settings.edit().putBoolean("first_time_use?", false).commit();
            return true;
        }
        Log.d("First Time?", "NO");
        return false;
    }

}


class AsyncTaskRunner extends AsyncTask<Object, String, String> {


    @Override
    protected String doInBackground(Object... inputs) {
        TimeTable.getAllCourses((InputStream) inputs[0]);
        TimeTable.getAllClasses((InputStream) inputs[1]);

        return null;
    }
}
