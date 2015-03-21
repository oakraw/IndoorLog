package com.oakraw.indoorlog.model;


/**
 * Created by Rawipol on 3/20/15 AD.
 */
public class Profile {
    int pid;
    int uid;
    String date;

    public Profile(int pid, int uid, String date) {
        this.pid = pid;
        this.uid = uid;
        this.date = date;
    }

    public int getPid() {
        return pid;
    }

    public int getUid() {
        return uid;
    }

    public String getDate() {
        return date;
    }
}
