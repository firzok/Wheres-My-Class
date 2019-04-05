package com.droids.ffs.smd_project.ViewWeeklySchedule;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.droids.ffs.smd_project.R;
import com.droids.ffs.smd_project.SQLite.Class;

import java.util.List;

public class ViewScheduleRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewsHolder> {

    private List<Class> classList;
    private Context context;

    public ViewScheduleRecyclerViewAdapter(Context context, List<Class> itemList) {

        this.classList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerViewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_schedule_rowstyle, null);

        RecyclerViewsHolder rcv = new RecyclerViewsHolder(layoutView);
        return rcv;
    }


    @Override
    public void onBindViewHolder(RecyclerViewsHolder holder, int position) {

        holder.Name.setText(classList.get(position).getCourseName());
        holder.Section.setText(classList.get(position).getCourseSection());
        holder.Room.setText(classList.get(position).getClassRoom());
        holder.Time.setText(classList.get(position).getClassStartTime() + " - " + classList.get(position).getClassEndTime());
        holder.Remindertime.setText(classList.get(position).getClassReminderTime() + " minutes before class time");

//        holder.setItem(classList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.classList.size();
    }

}