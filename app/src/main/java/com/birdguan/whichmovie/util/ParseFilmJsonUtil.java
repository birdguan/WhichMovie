package com.birdguan.whichmovie.util;

import android.content.Intent;
import android.util.Log;

import com.birdguan.whichmovie.model.Film;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: birdguan
 * @Date: 2020/6/20 15:25
 */
public class ParseFilmJsonUtil {
    private static final String TAG = "ParseFilmJsonUtil";

    public static Film getFilm(String json) {
        Film film = Film.getFilmInstance();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(json);
        if (element.isJsonObject()) {
            JsonObject object = element.getAsJsonObject();
            // 电影不存在
            if ("movie_not_found".equals(object.get("msg").getAsString())) {
                film.setTitle("Not found");
                film.setImages("https://img9.doubanio.com\\/view\\/photo\\/s_ratio_poster\\/public\\/p468924505.webp");
                film.setRating(0.0f);
                film.setDirectors(new ArrayList<String>());
                film.setCasts(new ArrayList<String>());
                return film;
            }
            // 电影标题
            String title = object.get("title").getAsString();
            film.setTitle(title);
            // 电影年代
            String year = object.get("year").getAsString();
            film.setYear(year);
            // 电影海报地址
            JsonObject imagesObject = object.getAsJsonObject("images");
            String images = imagesObject.get("large").getAsString();
            film.setImages(images);
            // 电影评分
            JsonObject rating = object.getAsJsonObject("rating");
            String average = rating.get("average").getAsString();
            film.setRating(Float.parseFloat(average));
            Log.d(TAG, film.getRating().toString());
            // 电影导演
            JsonArray directors = object.getAsJsonArray("directors");
            List<String> directList = new ArrayList<>();
            for (int i = 0; i < directors.size(); i++) {
                String directorName = directors.get(i).getAsJsonObject().get("name_en").getAsString();
                directList.add(directorName);
            }
            film.setDirectors(directList);
            // 电影演员
            JsonArray casts = object.getAsJsonArray("casts");
            List<String> castList = new ArrayList<>();
            for (int i = 0; i < casts.size(); i++) {
                String castName = casts.get(i).getAsJsonObject().get("name_en").getAsString();
                castList.add(castName);
            }
            film.setCasts(castList);
        }
        return film;
    }
}
