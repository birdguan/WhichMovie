package com.birdguan.whichmovie.model;

import org.litepal.crud.LitePalSupport;

/**
 * @Author: birdguan
 * @Date: 2020/6/18 19:14
 */

/**
 * 已观看电影bean
 */
public class WatchedFilm extends LitePalSupport {
    private int id;
    private String title;           // 已观看电影标题
    private String image;           // 已观看电影海报（地址）
    private String year;            // 已观看电影年代
    private Float rating;           // 已观看电影评分

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
}
