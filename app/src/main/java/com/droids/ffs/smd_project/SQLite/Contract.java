package com.droids.ffs.smd_project.SQLite;

import android.provider.BaseColumns;

public final class Contract {

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + FeedEntry.TABLE_COURSE +
            " (" + FeedEntry._ID + " INTEGER PRIMARY KEY," +
            FeedEntry.COL_COURSE_NAME + " TEXT," + FeedEntry.COL_SECTION + " TEXT," + FeedEntry.COL_DAY + " TEXT," + FeedEntry.COL_START_TIME + " TEXT," +
            FeedEntry.COL_END_TIME + " TEXT," + FeedEntry.COL_REMINDER_TIME + " TEXT," + FeedEntry.COL_CLASSROOM + " TEXT)";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_COURSE;

    protected Contract() {
    }

    public static String getSqlCreateEntries() {
        return SQL_CREATE_ENTRIES;
    }

    public static String getSqlDeleteEntries() {
        return SQL_DELETE_ENTRIES;
    }

    public static class FeedEntry implements BaseColumns {

        public static final String TABLE_COURSE = "Schedule";
        public static final String COL_COURSE_NAME = "course_name";
        public static final String COL_SECTION = "section";
        public static final String COL_DAY = "day";
        public static final String COL_START_TIME = "start_time";
        public static final String COL_END_TIME = "end_time";
        public static final String COL_REMINDER_TIME = "reminder_time";
        public static final String COL_CLASSROOM = "classroom";

    }
}
