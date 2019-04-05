package com.droids.ffs.smd_project.SQLite;

import java.io.Serializable;

public class Class implements Serializable {


    private String _id;
    private String courseName;
    private String courseShortname;
    private String courseSection;
    private String classDay;
    private int dayOfWeek;
    private String classStartTime;
    private String classEndTime;
    private String classReminderTime;
    private String classRoom;
    private int alarmId;
    public Class(String courseName, String courseSection) {
        this.courseName = courseName;
        this.courseSection = courseSection;
    }

    public Class() {
        this._id = "";
        this.courseName = "";
        this.courseSection = "";
        this.classDay = "";
        this.classStartTime = "";
        this.classEndTime = "";
        this.classReminderTime = "";
        this.classRoom = "";
        this.courseShortname = "";
        this.alarmId = 0;

    }

    public Class(String courseName, String courseSection, String classStartTime, String classEndTime, String classRoom, String courseShortname) {

        this.courseName = courseName;
        this.courseSection = courseSection;
        this.classStartTime = classStartTime;
        this.classEndTime = classEndTime;
        this.classRoom = classRoom;
        this.courseShortname = courseShortname;
    }

    public Class(String courseName, String courseShortname, String courseSection, String classDay, int dayOfWeek, String classStartTime, String classEndTime, String classReminderTime, String classRoom) {
        this.courseName = courseName;
        this.courseSection = courseSection;
        this.classDay = classDay;
        this.classStartTime = classStartTime;
        this.classEndTime = classEndTime;
        this.classReminderTime = classReminderTime;
        this.classRoom = classRoom;
        this.dayOfWeek = dayOfWeek;
        this.courseShortname = courseShortname;
    }


    public Class(String courseName, String courseSection, String classDay, String classStartTime, String classEndTime, String classReminderTime, String classRoom) {
        this.courseName = courseName;
        this.courseSection = courseSection;
        this.classDay = classDay;
        this.classStartTime = classStartTime;
        this.classEndTime = classEndTime;
        this.classReminderTime = classReminderTime;
        this.classRoom = classRoom;
    }

    public String getCourseShortname() {
        return courseShortname;
    }

    public void setCourseShortname(String courseShortname) {
        this.courseShortname = courseShortname;
    }

    public int getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(int alarmId) {
        this.alarmId = alarmId;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseSection() {
        return courseSection;
    }

    public void setCourseSection(String courseSection) {
        this.courseSection = courseSection;
    }

    public String getClassDay() {
        return classDay;
    }

    public void setClassDay(String classDay) {
        this.classDay = classDay;
    }

    public String getClassStartTime() {
        return classStartTime;
    }

    public void setClassStartTime(String classStartTime) {
        this.classStartTime = classStartTime;
    }

    public String getClassEndTime() {
        return classEndTime;
    }

    public void setClassEndTime(String classEndTime) {
        this.classEndTime = classEndTime;
    }

    public String getClassReminderTime() {
        return classReminderTime;
    }

    public void setClassReminderTime(String classReminderTime) {
        this.classReminderTime = classReminderTime;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }


}
