package com.oakraw.indoorlog.model;


/**
 * Created by Rawipol on 3/20/15 AD.
 */
public class DetailProfile {
    int pid;
    String time;
    String node;

    public DetailProfile(int pid, String time, String node) {
        this.pid = pid;
        this.time = time;
        this.node = node;
    }

    public int getPid() {
        return pid;
    }

    public String getTime() {
        return time;
    }

    public String getNode() {
        return node;
    }
}
