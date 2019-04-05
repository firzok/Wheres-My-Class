package com.droids.ffs.smd_project;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import com.droids.ffs.smd_project.Notifications.AlarmReceiver;
import com.droids.ffs.smd_project.SQLite.Class;
import com.droids.ffs.smd_project.SQLite.DBHandler;
import com.droids.ffs.smd_project.ViewWeeklySchedule.ViewScheduleActivity;

import java.util.Calendar;
import java.util.List;

public class SetReminderTime extends AppCompatActivity {


    DBHandler db;
    private Button saveBtn;
    private NumberPicker alarmTimePicker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivity.runFullScreenMode(this);
        setContentView(R.layout.set_reminder_time);

        db = new DBHandler(this);

        saveBtn = findViewById(R.id.save_btn);
        alarmTimePicker = findViewById(R.id.number_picker_wgt);
        alarmTimePicker.setMinValue(1);
        alarmTimePicker.setMaxValue(59);
        alarmTimePicker.setWrapSelectorWheel(true);

    }


    public void onClickSave(View view) {

        int reminderTime = alarmTimePicker.getValue();


        List<Class> selectedClasses = db.getAllClasses();

        db.removeAllClasses();
        for (int j = 0; j < selectedClasses.size(); j++) {


            Log.d("UpdatingAlarm", selectedClasses.get(j).getCourseName());

            selectedClasses.get(j).setClassReminderTime(String.valueOf(reminderTime));

            db.addClass(selectedClasses.get(j));

            int h = Integer.valueOf(selectedClasses.get(j).getClassStartTime().split(":")[0]);
            int m = Integer.valueOf(selectedClasses.get(j).getClassStartTime().split(":")[1]);

            Log.v("TimeLogUpdate", String.valueOf(h) + ":" + String.valueOf(m));

            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            Intent intent = new Intent(this, AlarmReceiver.class);
            intent.putExtra("classNameUpdate", selectedClasses.get(j).getCourseName());
            intent.putExtra("classroomUpdate", selectedClasses.get(j).getClassRoom());

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            if (h < 8) {
                h += 12;
            }
            int alarmId;
            if (selectedClasses.get(j).getAlarmId() == 0) {
                alarmId = Integer.valueOf(String.valueOf(h) + String.valueOf(selectedClasses.get(j).getDayOfWeek()));

                selectedClasses.get(j).setAlarmId(alarmId);
            } else {
                alarmId = selectedClasses.get(j).getAlarmId();
            }

            PendingIntent alarmIntent = PendingIntent.getBroadcast(this, alarmId, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            calendar.set(Calendar.HOUR_OF_DAY, h);
            calendar.set(Calendar.MINUTE, m);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            calendar.setFirstDayOfWeek(Calendar.SUNDAY);
//                SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
            calendar.set(Calendar.DAY_OF_WEEK, selectedClasses.get(j).getDayOfWeek());

            calendar.add(Calendar.MINUTE, -1 * reminderTime);

            //Repeat after each week
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 7 * AlarmManager.INTERVAL_DAY, alarmIntent);

            Log.v("AlarmLogUpdate", "Alarm set for " + selectedClasses.get(j).getCourseName() + "#" + calendar.getTime());

        }


        Intent i = new Intent(this, ViewScheduleActivity.class);
        startActivity(i);


    }
}
