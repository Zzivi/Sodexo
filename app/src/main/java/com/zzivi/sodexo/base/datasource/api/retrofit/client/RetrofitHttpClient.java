package com.zzivi.sodexo.base.datasource.api.retrofit.client;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import retrofit.client.Request;
import retrofit.client.UrlConnectionClient;

/**
 * Created by daniel on 11/10/14.
 */
public class RetrofitHttpClient extends UrlConnectionClient {

    private static final int CONNECT_TIMEOUT_MILLIS = 60 * 1000; // 30s
    private static final int READ_TIMEOUT_MILLIS = 85 * 1000; // 45s
    private static final CookieManager cookieManager = new CookieManager();

    private static OkUrlFactory generateDefaultOkUrlFactory() {
        OkHttpClient client = new com.squareup.okhttp.OkHttpClient();
        client.setConnectTimeout(CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
        client.setReadTimeout(READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        client.setCookieHandler(cookieManager);
        return new OkUrlFactory(client);
    }

    private final OkUrlFactory factory;

    public RetrofitHttpClient() {
        factory = generateDefaultOkUrlFactory();
    }

    public void removeCookies() {
        cookieManager.getCookieStore().removeAll();
    }


    @Override protected HttpURLConnection openConnection(Request request) throws IOException {
        HttpURLConnection httpURLConnection = factory.open(new URL(request.getUrl()));
        //httpURLConnection.setUseCaches(false);
        return httpURLConnection;
    }
}
