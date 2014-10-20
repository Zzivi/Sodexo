package com.zzivi.sodexo.login.datasource.api.retrofit;

import com.zzivi.sodexo.base.datasource.api.retrofit.ApiRetrofit;
import com.zzivi.sodexo.base.datasource.api.retrofit.RestApi;
import com.zzivi.sodexo.login.datasource.api.HomeApi;
import com.zzivi.sodexo.login.datasource.api.model.CookiesResultModel;

import java.util.List;

import javax.inject.Inject;

import retrofit.client.Header;
import retrofit.client.Response;

/**
 * Created by daniel on 11/10/14.
 */
public class HomeApiRetrofit implements HomeApi {

    private final ApiRetrofit apiRetrofit;

    @Inject
    public HomeApiRetrofit(ApiRetrofit apiRetrofit) {
        this.apiRetrofit = apiRetrofit;
    }

    @Override
    public CookiesResultModel obtainCookies() {
        CookiesResultModel cookiesResultModel = new CookiesResultModel();

        RestApi restApi = apiRetrofit.buildRestApi();

        Response responseHome = restApi.getHome();

        List<Header> headers = responseHome.getHeaders();
        for(Header header : headers) {
            if ("Set-Cookie".equals(header.getName())) {
                cookiesResultModel.setCookie(header.getValue());
            }
        }

        return cookiesResultModel;
    }
}
