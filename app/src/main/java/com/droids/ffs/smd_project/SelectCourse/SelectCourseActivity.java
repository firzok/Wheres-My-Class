package com.droids.ffs.smd_project.SelectCourse;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.droids.ffs.smd_project.Notifications.AlarmReceiver;
import com.droids.ffs.smd_project.R;
import com.droids.ffs.smd_project.SQLite.Class;
import com.droids.ffs.smd_project.SQLite.DBHandler;
import com.droids.ffs.smd_project.TimeTable;
import com.droids.ffs.smd_project.ViewWeeklySchedule.ViewScheduleActivity;
import com.droids.ffs.smd_project.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SelectCourseActivity extends AppCompatActivity implements RecyclerItemTouchHelperListener {


    private static List<Class> classList;
    private static Class acceptedItem;
    private static int acceptedIndex;
    DBHandler db;
    private RecyclerView recyclerView;
    private List<Class> acceptedList;
    private CardListAdapter adapter;
    private CoordinatorLayout rootlayout;
    private Button doneButton;
    private int alarmReminderTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_course);

        doneButton = findViewById(R.id.done);
        acceptedList = new ArrayList<>();
        db = new DBHandler(this);


        Intent i = getIntent();
        classList = TimeTable.getAllCourses();

        //Get reminder time from intent or set default to 10
        alarmReminderTime = i.getIntExtra("alarmReminderTime", 10);

        //Sets select course view
        SelectCoursesUI();

        Toast.makeText(getApplicationContext(), "Swipe Right to Select Courses", Toast.LENGTH_LONG).show();
    }

    public void SelectCoursesUI() {

//        Sets select course UI
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Select Courses");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recycler_view);
        rootlayout = findViewById(R.id.rootLayout);

        adapter = new CardListAdapter(this, classList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

        ItemTouchHelper.SimpleCallback itemtouchhelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(itemtouchhelperCallback).attachToRecyclerView(recyclerView);

        adapter.notifyDataSetChanged();

    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        //On swipe, course is deleting

        if (viewHolder instanceof CardListAdapter.MyViewHolder) {
//            String name = classList.get(viewHolder.getAdapterPosition()).getCourseName();

            acceptedItem = classList.get(position);


            acceptedIndex = position;
            adapter.removeItem(acceptedIndex);

            //adds accepted courses to list
            acceptedList.add(acceptedItem);


            final List<Class> oldClassList = classList;

            //Remove Same course with different sections
            classList = util.removeSameCoursesDifferentSections(oldClassList, acceptedItem);

            //refresh changes
            adapter = new CardListAdapter(this, classList);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();


            //Shows UNDO bar at the bottom of the screen
            Snackbar snackbar = Snackbar.make(rootlayout, acceptedItem.getCourseName() + ":" + acceptedItem.getCourseSection() + " selected from Timetable ", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {

                //On clicking Undo, restore the deleted item
                @Override
                public void onClick(View view) {
                    acceptedList.remove(acceptedIndex);
                    adapter.restoreItem(acceptedItem, acceptedIndex);

                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();

        }
    }

    public void onClickDone(View view) {

        Log.v("Functions", "onClickDone");

        InputStream myInput = getResources().openRawResource(R.raw.classes);

        TimeTable.getAllClasses(myInput);

        for (int i = 0; i < acceptedList.size(); i++) {


            List<Class> classes = TimeTable.getClassObjects(acceptedList.get(i).getCourseName(), acceptedList.get(i).getCourseSection(), acceptedList.get(i).getCourseShortname());


            Log.v("SelectCourseActivity", "Going to add class: " + String.valueOf(classes.size()));
            for (int j = 0; j < classes.size(); j++) {
                Log.d("ADDING", classes.get(j).getCourseName());

                classes.get(j).setClassReminderTime(String.valueOf(alarmReminderTime));
                db.addClass(classes.get(j));

                int h = Integer.valueOf(classes.get(j).getClassStartTime().split(":")[0]);
                int m = Integer.valueOf(classes.get(j).getClassStartTime().split(":")[1]);

                Log.v("TimeLog", String.valueOf(h) + ":" + String.valueOf(m));

                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                Intent intent = new Intent(this, AlarmReceiver.class);
                intent.putExtra("className", classes.get(j).getCourseName());
                intent.putExtra("classroom", classes.get(j).getClassRoom());

                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                if (h < 8) {
                    h += 12;
                }
                int alarmId;
                if (classes.get(j).getAlarmId() == 0) {
                    alarmId = Integer.valueOf(String.valueOf(h) + String.valueOf(classes.get(j).getDayOfWeek()));

                    classes.get(j).setAlarmId(alarmId);
                } else {
                    alarmId = classes.get(j).getAlarmId();
                }

                PendingIntent alarmIntent = PendingIntent.getBroadcast(this, alarmId, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                calendar.set(Calendar.HOUR_OF_DAY, h);
                calendar.set(Calendar.MINUTE, m);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                calendar.setFirstDayOfWeek(Calendar.SUNDAY);
//                SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
                calendar.set(Calendar.DAY_OF_WEEK, classes.get(j).getDayOfWeek());

                calendar.add(Calendar.MINUTE, -1 * alarmReminderTime);

                //Repeat after each week
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 7 * AlarmManager.INTERVAL_DAY, alarmIntent);

                Log.v("AlarmLog", "Alarm set for " + classes.get(j).getCourseName() + "#" + calendar.getTime());

            }

        }

        Intent i = new Intent(this, ViewScheduleActivity.class);
        startActivity(i);

    }


}
