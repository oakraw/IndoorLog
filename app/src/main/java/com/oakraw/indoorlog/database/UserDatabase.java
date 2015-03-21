package com.oakraw.indoorlog.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.oakraw.indoorlog.model.DetailTimeNode;
import com.oakraw.indoorlog.model.Profile;
import com.oakraw.indoorlog.model.User;

import java.util.ArrayList;

/**
 * Created by Rawipol on 9/26/14 AD.
 */
public class UserDatabase extends SQLiteOpenHelper {

    public static final String TABLE_USER = "User";
    public static final String TABLE_PROFILE = "Profile";
    public static final String TABLE_DETAILPROFILE = "DetailProfile";
    public static final String TABLE_NODE = "Node";

    public static final String COLUMN_UID = "uid";
    public static final String COLUMN_PID = "pid";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SURNAME = "surname";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_NID = "nid";
    public static final String COLUMN_NODE = "node";
    public static final String COLUMN_TIME = "time";

    private static final String DATABASE_NAME = "indoorlog.db";
    private static final int DATABASE_VERSION = 1;
    // Database creation sql statement
    private static final String USER_CREATE = "create table "
            + TABLE_USER + "(" + COLUMN_UID
            + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null,"
            + COLUMN_SURNAME + " text not null,"
            + COLUMN_USERNAME + " text not null,"
            + COLUMN_PASSWORD + " text not null, " +
            "UNIQUE ("+ COLUMN_USERNAME +")" +
            ");";

    private static final String PROFILE_CREATE = "create table "
            + TABLE_PROFILE + "("
            + COLUMN_PID + " integer primary key autoincrement, "
            + COLUMN_UID + " integer NOT NULL,"
            + COLUMN_DATE + " varchar(50) not null, " +
            "UNIQUE(" + COLUMN_UID + ", " + COLUMN_DATE + ")" +
            ");";


    private static final String DETAILPROFILE_CREATE = "create table "
            + TABLE_DETAILPROFILE + "(" + COLUMN_PID
            + " integer, "
            + COLUMN_TIME + " text not null,"
            + COLUMN_NID + " text not null," +
            "primary key(" + COLUMN_PID + ", " + COLUMN_TIME + ")" +
            ");";

    private static final String NODE_CREATE = "create table "
            + TABLE_NODE + "(" + COLUMN_NID
            + " text not null primary key, "
            + COLUMN_NODE + " text not null," +
            "UNIQUE(" + COLUMN_NID + ") ON CONFLICT REPLACE" +
            ");";

    private ArrayList<User> records_list = new ArrayList<User>();
    private ArrayList<Profile> profile_list = new ArrayList<Profile>();

    public UserDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(USER_CREATE);
        db.execSQL(PROFILE_CREATE);
        db.execSQL(DETAILPROFILE_CREATE);
        db.execSQL(NODE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Add new record
    public int addUserRecord(String name, String surname, String username, String password) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_SURNAME, surname);
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);

        long insertID = db.insert(TABLE_USER, null, values);
        db.close();

        return (int) insertID;
    }

    public int addProfileRecord(int uid, String date) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_UID, uid);
        values.put(COLUMN_DATE, date);

        long insertID = db.insert(TABLE_PROFILE, null, values);
        db.close();

        return (int) insertID;
    }

    //Add new record
    public int addDetailProfileRecord(int pid, String time, String nid) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PID, pid);
        values.put(COLUMN_TIME, time);
        values.put(COLUMN_NID, nid);

        long insertID = db.insert(TABLE_DETAILPROFILE, null, values);
        db.close();

        return (int) insertID;
    }

    //Add new record
    public int addNodeRecord(String nid, String node) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NID, nid);
        values.put(COLUMN_NODE, node);

        long insertID = db.insert(TABLE_NODE, null, values);
        db.close();

        return (int) insertID;
    }

    //get each record
    public User getUserRecord(String username) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, new String[]{COLUMN_UID,
                        COLUMN_NAME, COLUMN_SURNAME, COLUMN_USERNAME, COLUMN_PASSWORD}, COLUMN_USERNAME + "=?",
                new String[]{username}, null, null, null, null);
        if (cursor.getCount() == 0) {
            return null;
        }
        if (cursor != null)
            cursor.moveToFirst();
        User record = new User(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        // return contact
        cursor.close();
        db.close();

        return record;
    }

    //get each record
    public Profile getProfileRecord(int uid, String date) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_PROFILE, new String[]{COLUMN_PID,
                        COLUMN_UID, COLUMN_DATE}, COLUMN_UID + "=? AND " + COLUMN_DATE + "=?",
                new String[]{String.valueOf(uid), date}, null, null, null, null);
        if (cursor.getCount() == 0) {
            return null;
        }
        if (cursor != null)
            cursor.moveToFirst();
        Profile record = new Profile(Integer.parseInt(cursor.getString(0)),
                Integer.parseInt(cursor.getString(1)), cursor.getString(2));
        // return contact
        cursor.close();
        db.close();

        return record;
    }

    // Getting All Profile Records
    public ArrayList<User> getAllUserRecords() {
        try {
            //records_list.clear();
            // Select All Query
            String selectQuery = "SELECT * FROM " + TABLE_USER;
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    User user = new User(
                            Integer.parseInt(cursor.getString(0)),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4)
                    );
                    records_list.add(user);
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
            return records_list;
        } catch (Exception e) {
            // TODO: handle exception
            Log.e("all_records", "" + e);
        }
        return records_list;
    }

    // Getting All Profile Records
    public ArrayList<Profile> getAllProfileRecords(int uid) {
        try {
            //records_list.clear();
            // Select All Query
            String selectQuery = "SELECT * FROM " + TABLE_PROFILE + " WHERE " + COLUMN_UID + " = " + uid;
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    Profile profile = new Profile(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1)),cursor.getString(2));
                    profile_list.add(profile);
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
            return profile_list;
        } catch (Exception e) {
            // TODO: handle exception
            Log.e("all_records", "" + e);
        }
        return profile_list;
    }

    public ArrayList<DetailTimeNode> getAllDetailRecords(int pid) {
        ArrayList<DetailTimeNode> arrayDetail = new ArrayList<>();
        try {
            //records_list.clear();
            // Select All Query
            String selectQuery = "SELECT * FROM " + TABLE_DETAILPROFILE + " WHERE " + COLUMN_PID + " = " + pid;
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    DetailTimeNode timeNode = new DetailTimeNode();
                    timeNode.setTime(cursor.getString(1));
                    timeNode.setNid(cursor.getString(2));
                    String select2Query = "SELECT * FROM " + TABLE_NODE + " WHERE " + COLUMN_NID + " = '" + timeNode.getNid() +"'";
                    Cursor cursor2 = db.rawQuery(select2Query, null);
                    // looping through all rows and adding to list
                    if (cursor2.moveToFirst()) {
                        timeNode.setNode(cursor2.getString(1));
                    }
                    arrayDetail.add(timeNode);
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
            return arrayDetail;
        } catch (Exception e) {
            // TODO: handle exception
            Log.e("all_records", "" + e);
        }
        return arrayDetail;
    }

    /*
    public void deleteRecord(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_RECORDS, COLUMN_ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }*/
}