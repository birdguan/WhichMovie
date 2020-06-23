package com.birdguan.whichmovie.model;

import org.litepal.crud.LitePalSupport;


/**
 * @Author: birdguan
 * @Date: 2020/6/18 19:14
 */

/**
 * 用户收藏的电影bean
 */
public class CollectedFilm extends LitePalSupport {

    private int id;
    private String title;
    private String image;
    private String year;
    private Float rating;

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
