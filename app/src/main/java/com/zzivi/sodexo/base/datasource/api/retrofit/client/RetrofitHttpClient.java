package com.zzivi.sodexo.base.datasource.api.retrofit.client;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import retrofit.client.Request;
import retrofit.client.UrlConnectionClient;

/**
 * Created by daniel on 11/10/14.
 */
public class RetrofitHttpClient extends UrlConnectionClient {

    private static final int CONNECT_TIMEOUT_MILLIS = 60 * 1000;
    private static final int READ_TIMEOUT_MILLIS = 85 * 1000;

    private static OkUrlFactory generateDefaultOkUrlFactory() {
        OkHttpClient client = new com.squareup.okhttp.OkHttpClient();
        client.setConnectTimeout(CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
        client.setReadTimeout(READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
        return new OkUrlFactory(client);
    }

    private final OkUrlFactory factory;

    public RetrofitHttpClient() {
        factory = generateDefaultOkUrlFactory();
    }


    @Override protected HttpURLConnection openConnection(Request request) throws IOException {
        HttpURLConnection httpURLConnection = factory.open(new URL(request.getUrl()));
        return httpURLConnection;
    }
}
