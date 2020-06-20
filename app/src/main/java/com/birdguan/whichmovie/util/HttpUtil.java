package com.birdguan.whichmovie.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @Author: birdguan
 * @Date: 2020/6/20 14:47
 * 测试id: 1764795
 */
public class HttpUtil {
    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.douban.com/v2/movie/subject/1259604?apikey=0df993c66c0c636e29ecbb5344252a4a")
                .build();
        client.newCall(request).enqueue(callback);
    }
}
