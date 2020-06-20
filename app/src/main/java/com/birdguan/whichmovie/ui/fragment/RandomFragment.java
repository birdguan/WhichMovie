package com.birdguan.whichmovie.ui.fragment;

import android.media.Rating;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.birdguan.whichmovie.R;
import com.birdguan.whichmovie.model.Film;
import com.birdguan.whichmovie.util.HttpUtil;
import com.birdguan.whichmovie.util.ParseFilmJsonUtil;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Response;

/**
 * @Author: birdguan
 * @Date: 2020/6/19 16:49
 */
public class RandomFragment extends Fragment {
    private static final String TAG = "RandomFragment";
    private View view;
    private Button buttonWatch;
    private Button buttonRandomSelect;
    private Button buttonCollect;
    private ImageView imageViewMovieImage;
    private TextView textViewMovieTitle;
    private TextView textViewMovieYear;
    private TextView textViewMovieDirectors;
    private TextView textViewMovieCasts;
    private RatingBar ratingBarMovieRating;

    private Film film = Film.getFilmInstance();

    private static final int UPDATE_MOVIE = 1;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_MOVIE:
                    film = (Film)msg.obj;
                    Picasso.with(view.getContext()).load(film.getImages()).into(imageViewMovieImage);
                    textViewMovieTitle.setText(film.getTitle());
                    textViewMovieYear.setText(film.getYear());
                    ratingBarMovieRating.setRating(film.getRating() / 2);
                    List<String> directors = film.getDirectors();
                    StringBuilder directorBuilder = new StringBuilder();
                    for (String director : directors) {
                        directorBuilder.append(director).append("\n");
                    }
                    textViewMovieDirectors.setText(directorBuilder.toString());
                    List<String> casts = film.getCasts();
                    StringBuilder castBuilder = new StringBuilder();
                    for (String cast : casts) {
                        castBuilder.append(cast).append("\n");
                    }
                    textViewMovieCasts.setText(castBuilder.toString());
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_random, container, false);
        buttonWatch = (Button) view.findViewById(R.id.btn_watch);
        buttonRandomSelect = (Button) view.findViewById(R.id.btn_random_select);
        buttonCollect = (Button) view.findViewById(R.id.btn_collect);
        imageViewMovieImage = (ImageView) view.findViewById(R.id.movie_image);
        textViewMovieTitle = (TextView) view.findViewById(R.id.movie_title);
        textViewMovieYear = (TextView) view.findViewById(R.id.movie_year);
        ratingBarMovieRating = (RatingBar) view.findViewById(R.id.movie_rating);
        textViewMovieDirectors = (TextView) view.findViewById(R.id.movie_director);
        textViewMovieCasts = (TextView) view.findViewById(R.id.movie_casts);

        // 观看按钮监听
        // TODO: 点击监听按钮，发送广播给视频播放软件，如爱奇艺、优酷等，查找这类APP接受广播打开方式
        // 同时将已观看电影的信息保存到数据库
        buttonWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // 翻一部按钮监听
        buttonRandomSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int index = random.nextInt(138234);
                String getIdUrl = "http://39.101.204.206:18080/?index=" + index;
                Log.d(TAG, "getIdUrl: " + getIdUrl);
                HttpUtil.sendOkHttpRequestToGetId(getIdUrl, new okhttp3.Callback() {

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        String responseData = response.body().string();
                        Log.d(TAG, responseData);
                        String id = ParseFilmJsonUtil.getId(responseData);
                        Log.d(TAG,"获取到的电影id: " + id);
                        String getFilmUrl = "https://api.douban.com/v2/movie/subject/" + id + "?apikey=0df993c66c0c636e29ecbb5344252a4a";
                        HttpUtil.sendOkHttpRequest(getFilmUrl, new okhttp3.Callback() {

                            @Override
                            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                                String responseData = response.body().string();
                                Log.d(TAG, responseData);
                                // 解析responseData
                                film = ParseFilmJsonUtil.getFilm(responseData);

                                // 解析完的数据发送给UI线程
                                Message message = new Message();
                                message.what = UPDATE_MOVIE;
                                message.obj = film;
                                Message message2 = new Message();
//                              message2.what = 2;
//                              message2.obj = responseData.substring(0, 10);
                                handler.sendMessage(message);
//                              handler.sendMessage(message2);
                            }

                            @Override
                            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                                Log.d(TAG, "使用OkHttp从豆瓣api获取电影详情信息失败");
                            }
                        });
                    }

                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Log.d(TAG, e.toString());
                        Log.d(TAG, "获取id连接失败");
                    }
                });


            }
        });

        // 收藏按钮监听
        // 用户收藏时，将电影信息保存到SQlite数据库
        buttonCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }

}
