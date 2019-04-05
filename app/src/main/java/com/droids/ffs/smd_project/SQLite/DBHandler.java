package com.droids.ffs.smd_project.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
//    Contract contract = new Contract();

    private static final String DATABASE_NAME= "courseManager";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Contract.getSqlCreateEntries());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+Contract.FeedEntry.TABLE_COURSE);

    }

    public void addClass(Class _class){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Contract.FeedEntry.COL_COURSE_NAME,_class.getCourseName());
        values.put(Contract.FeedEntry.COL_SECTION,_class.getCourseSection());
        values.put(Contract.FeedEntry.COL_DAY,_class.getClassDay());
        values.put(Contract.FeedEntry.COL_START_TIME,_class.getClassStartTime());
        values.put(Contract.FeedEntry.COL_END_TIME,_class.getClassEndTime());
        values.put(Contract.FeedEntry.COL_REMINDER_TIME,_class.getClassReminderTime());
        values.put(Contract.FeedEntry.COL_CLASSROOM,_class.getClassRoom());

        float ret = db.insert(Contract.FeedEntry.TABLE_COURSE,null,values);

        Log.v("DBHandler",String.valueOf(ret));
        db.close();
    }


    public int removeAllClasses(){
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(Contract.FeedEntry.TABLE_COURSE, "1", null);

    }

    public List<Class> getAllClasses(){


        SQLiteDatabase db = this.getReadableDatabase();
        List<Class> classList = new ArrayList<Class>();
        String selectquery = "SELECT *FROM " + Contract.FeedEntry.TABLE_COURSE;
        Cursor cursor = db.rawQuery(selectquery,null);

        if (cursor.moveToFirst()){

            do{
                Class classInformation = new Class();
                classInformation.set_id(cursor.getString(0));
                classInformation.setCourseName(cursor.getString(1));
                classInformation.setCourseSection(cursor.getString(2));
                classInformation.setClassDay(cursor.getString(3));
                classInformation.setClassStartTime(cursor.getString(4));
                classInformation.setClassEndTime(cursor.getString(5));
                classInformation.setClassReminderTime(cursor.getString(6));
                classInformation.setClassRoom(cursor.getString(7));


                classList.add(classInformation);
                Log.d("class Information",classInformation.get_id());

            }while (cursor.moveToNext());
        }
        return classList;


    }

    public List<Class> getClasses(String day){

        SQLiteDatabase db = this.getReadableDatabase();
        List<Class> classList = new ArrayList<Class>();

//        String selectquery = "SELECT *FROM " + Contract.FeedEntry.TABLE_COURSE + " WHERE " + Contract.FeedEntry.COL_DAY + "=" + day ;

        Cursor cursor = db.query(Contract.FeedEntry.TABLE_COURSE,new String[]{Contract.FeedEntry._ID,Contract.FeedEntry.COL_COURSE_NAME,Contract.FeedEntry.COL_SECTION,Contract.FeedEntry.COL_DAY,Contract.FeedEntry.COL_START_TIME,Contract.FeedEntry.COL_END_TIME,Contract.FeedEntry.COL_REMINDER_TIME,Contract.FeedEntry.COL_CLASSROOM},Contract.FeedEntry.COL_DAY + "=?", new String[]{day},null,null,null,null);

//        Cursor cursor = db.rawQuery(selectquery,null);

        if (cursor.moveToFirst()){

            do{
                Class classInformation = new Class();
                classInformation.set_id(cursor.getString(0));
                classInformation.setCourseName(cursor.getString(1));
                classInformation.setCourseSection(cursor.getString(2));
                classInformation.setClassDay(cursor.getString(3));
                classInformation.setClassStartTime(cursor.getString(4));
                classInformation.setClassEndTime(cursor.getString(5));
                classInformation.setClassReminderTime(cursor.getString(6));
                classInformation.setClassRoom(cursor.getString(7));


                classList.add(classInformation);
                Log.d("class Information",classInformation.get_id());

            }while (cursor.moveToNext());
        }
        return classList;
    }

//    public Class getCourse(String id){
//
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.query(Contract.FeedEntry.TABLE_COURSE,new String[]{Contract.FeedEntry._ID,Contract.FeedEntry.COL_COURSE_NAME,Contract.FeedEntry.COL_CREDIT_HOURS},Contract.FeedEntry._ID + "=?", new String[]{id},null,null,null,null);
//
//        if(cursor!=null){
//            cursor.moveToFirst();
//        }
//        Class course = new Class(cursor.getString(0),cursor.getString(1), Integer.parseInt(cursor.getString(2)));
//        return course;
//
//    }




}
