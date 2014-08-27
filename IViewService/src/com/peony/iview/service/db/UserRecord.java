package com.peony.iview.service.db;

/**
 * Created by Shining100 on 2014-08-24.
 */
public class UserRecord {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    long id;
    String name;
    String password;
}
