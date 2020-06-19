package com.birdguan.whichmovie.model;

import org.litepal.crud.LitePalSupport;

/**
 * @Author: birdguan
 * @Date: 2020/6/18 19:14
 */
public class User extends LitePalSupport {
    private int id;
    private String username;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
