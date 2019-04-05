package com.droids.ffs.smd_project.SelectCourse;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.droids.ffs.smd_project.R;
import com.droids.ffs.smd_project.SQLite.Class;

import java.util.List;

public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.MyViewHolder> {


    private List<Class> classlist;
    private Context context;

    //
    public CardListAdapter(Context context, List<Class> classlist) {

        this.classlist = classlist;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlist_select_course, parent, false);
//
//        RecyclerViewsHolder rcv = new RecyclerViewsHolder(layoutView);
        return new MyViewHolder(itemView);
    }

    //
//
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Class class1 = classlist.get(position);
        holder.name.setText(class1.getCourseName());
        holder.section.setText(class1.getCourseSection());

    }

    @Override
    public int getItemCount() {
        return this.classlist.size();
    }

    public void removeItem(int position) {

        classlist.remove(position);
        notifyItemRemoved(position);
    }


    public void restoreItem(Class i, int position) {
        classlist.add(position, i);
        notifyItemInserted(position);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name, section;
        public ImageView thumbnail;
        public RelativeLayout viewBackground, viewForeground;

        public MyViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            section = itemView.findViewById(R.id.description);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            viewBackground = itemView.findViewById(R.id.view_background);
            viewForeground = itemView.findViewById(R.id.view_foreground);


        }
    }
}
