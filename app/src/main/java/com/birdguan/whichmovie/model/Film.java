package com.birdguan.whichmovie.model;

import org.litepal.crud.DataSupport;

import java.util.List;
import java.util.Map;

/**
 * @Author: birdguan
 * @Date: 2020/6/18 19:28
 */
public class Film extends DataSupport {
    private int id;
    private String title;           // 电影标题
    private String images;          // 电影海报地址
    private String year;            // 年代
    private String rating;          // 评分
    private List<String> directors; // 导演
    private List<String> casts;     // 演员

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

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }

    public List<String> getCasts() {
        return casts;
    }

    public void setCasts(List<String> casts) {
        this.casts = casts;
    }
}
