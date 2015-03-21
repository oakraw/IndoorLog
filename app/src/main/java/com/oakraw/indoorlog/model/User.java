package com.oakraw.indoorlog.model;

import android.content.Context;

import com.orm.SugarRecord;


/**
 * Created by Rawipol on 3/20/15 AD.
 */
public class User{
    int id;
    String name = "name";
    String lastname = "lastname";
    String username = "username";
    String password = "password";

    public User(int id,String name, String lastname, String username, String password) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
