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
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void sendOkHttpRequestToGetId(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
