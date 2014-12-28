package com.zzivi.sodexo.base.datasource.api.retrofit;

import com.zzivi.sodexo.base.datasource.api.retrofit.client.RetrofitHttpClient;
import com.zzivi.sodexo.base.datasource.sharedpreferences.SessionDataSource;
import com.zzivi.sodexo.login.datasource.api.model.CookiesResultModel;

import javax.inject.Inject;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;


/**
 * Created by daniel on 11/10/14.
 */
public class ApiRetrofit {

    public static final String API_URL = "http://www.mysodexo.es";

    private SessionDataSource loginCookieDataSource;

    @Inject
    public ApiRetrofit(SessionDataSource loginCookieDataSource){
        this.loginCookieDataSource = loginCookieDataSource;
    }

    RequestInterceptor addHeaders = new RequestInterceptor() {
        @Override
        public void intercept(RequestInterceptor.RequestFacade request) {
            request.addHeader("Content-Type", "application/x-www-form-urlencoded");
            request.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            request.addHeader("Accept-Language", "en-US,en;q=0.5");
            request.addHeader("Referer", "http://www.mysodexo.es/");
            request.addHeader("Host", "www.mysodexo.es");
            request.addHeader("Connection", "keep-alive");
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
                        //.setLogLevel(RestAdapter.LogLevel.FULL)
                        .setRequestInterceptor(addHeaders);

        RestAdapter restAdapter = restAdapterBuilder.build();
        return restAdapter.create(RestApi.class);
    }
}
