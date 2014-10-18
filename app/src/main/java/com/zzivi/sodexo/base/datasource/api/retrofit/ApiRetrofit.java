package com.zzivi.sodexo.base.datasource.api.retrofit;

import com.zzivi.sodexo.base.datasource.api.retrofit.client.RetrofitHttpClient;
import com.zzivi.sodexo.base.datasource.sharedpreferences.LoginCookieDataSource;
import com.zzivi.sodexo.login.datasource.api.model.CookiesResultModel;

import javax.inject.Inject;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;


/**
 * Created by daniel on 11/10/14.
 */
public class ApiRetrofit {

    public static final String API_URL = "http://www.mysodexo.es";
    private LoginCookieDataSource loginCookieDataSource;

    @Inject
    public ApiRetrofit(LoginCookieDataSource loginCookieDataSource){
        this.loginCookieDataSource = loginCookieDataSource;
    }

    RequestInterceptor addHeaders = new RequestInterceptor() {
        @Override
        public void intercept(RequestInterceptor.RequestFacade request) {
            //request.addHeader("Accept-Encoding", "gzip-deflate");
            request.addHeader("Content-Type", "application/x-www-form-urlencoded");
            request.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            request.addHeader("Accept-Language", "en-US,en;q=0.5");
            request.addHeader("User-Agent", "Mozilla/5.0");
            request.addHeader("Referer", "http://www.mysodexo.es/");
            request.addHeader("Host", "www.mysodexo.es");
            CookiesResultModel cookiesResultModel = loginCookieDataSource.obtainLoginCookie();
            if (!"not found".equals(cookiesResultModel.getCookie())) {
                request.addHeader("Cookie", cookiesResultModel.getCookie());
            }

        }
    };

    public RestApi buildRestApi() {

        RestAdapter.Builder restAdapterBuilder =
                new RestAdapter.Builder()
                        .setEndpoint(API_URL)
                        .setClient(new RetrofitHttpClient())
                        .setLogLevel(RestAdapter.LogLevel.FULL)
                        .setRequestInterceptor(addHeaders);


        RestAdapter restAdapter = restAdapterBuilder.build();
        //restAdapterBuilder.setLogLevel(RestAdapter.LogLevel.FULL);
        return restAdapter.create(RestApi.class);
    }

    public RestApi buildRestApiLogin() {

        RetrofitHttpClient retrofitHttpClient = new RetrofitHttpClient();
        retrofitHttpClient.removeCookies();

        RestAdapter.Builder restAdapterBuilder =
                new RestAdapter.Builder()
                        .setEndpoint(API_URL)
                        .setClient(retrofitHttpClient)
                        .setLogLevel(RestAdapter.LogLevel.FULL)
                        .setRequestInterceptor(addHeaders);


        RestAdapter restAdapter = restAdapterBuilder.build();
        //restAdapterBuilder.setLogLevel(RestAdapter.LogLevel.FULL);
        return restAdapter.create(RestApi.class);
    }
}
