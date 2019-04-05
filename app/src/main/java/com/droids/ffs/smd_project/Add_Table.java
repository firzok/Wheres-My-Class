package com.droids.ffs.smd_project;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import com.droids.ffs.smd_project.SQLite.Class;
import com.droids.ffs.smd_project.SQLite.DBHandler;
import com.droids.ffs.smd_project.SelectCourse.SelectCourseActivity;
import com.droids.ffs.smd_project.ViewWeeklySchedule.ViewScheduleActivity;

import java.io.InputStream;
import java.util.List;

public class Add_Table extends AppCompatActivity {


    Button addTable, selectCourses, viewSchedule;
    NumberPicker alarmTimePicker;
    DBHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //No title bar
        MainActivity.runFullScreenMode(this);

        setContentView(R.layout.add_table);
        if (Build.VERSION.SDK_INT >= 21)
            getWindow().setNavigationBarColor(Color.BLACK);

        Button btn = findViewById(R.id.add_time_table_btn);


        //Initialize buttons and Picker
        init();


    }

    // Initializer
    protected void init() {
        //Buttons from the add_table layout
        addTable = findViewById(R.id.add_time_table_btn);
        selectCourses = findViewById(R.id.select_courses_btn);
        viewSchedule = findViewById(R.id.view_schedule_btn);

        //Alarm time Scroller picker
        alarmTimePicker = findViewById(R.id.number_picker_wgt);
        alarmTimePicker.setMinValue(1);
        alarmTimePicker.setMaxValue(59);
        alarmTimePicker.setWrapSelectorWheel(true);
    }

    public void onClickAddTable(View view) {
        Intent filepicker = new Intent(Intent.ACTION_GET_CONTENT);
        filepicker.setType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        startActivityForResult(filepicker, 1);
    }

    // Get Result from Intent
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Uri datas = data.getData();
//                    String ttPath = datas.getPath();
//                    String path = ttPath.split(":")[1];
//                    Log.v("test1", path);

                InputStream myInput = getResources().openRawResource(R.raw.courselist);
                List<Class> courses = TimeTable.getAllCourses(myInput);

                Intent i = new Intent(this, SelectCourseActivity.class);
//                    i.putExtra("courseList", (Serializable) courses);
                i.putExtra("alarmReminderTime", alarmTimePicker.getValue());
                startActivity(i);


            }
        }
    }
//
//    // Copy File From "Path" to Destination
//    public void copyFile(String path) {
////        String sourcePath = Environment.getExternalStorageDirectory().getAbsolutePath() ;
////        Log.d("test", path);
////        String sourcePath = path;
//        File source = new File(path);
//
////        String destinationPath = Environment.getExternalStorageDirectory().getAbsolutePath();
////        Log.d("Jarree", destinationPath);
//
//
////        String destinationPath = "/document/raw:/storage/emulated/0/Download/jarree";
////        File destination = new File(destinationPath);
//        try
//        {
//            FileUtils.copyFile(source, new File(getFilesDir()+"/timeTable.xlsx"));
//        }
//        catch (IOException e)
//        {
//            Log.d("test", "No can do!!!");
//            e.printStackTrace();
//        }
//    }


}

