package com.zzivi.sodexo.login.datasource.api.retrofit;

import com.zzivi.sodexo.base.datasource.api.retrofit.ApiRetrofit;
import com.zzivi.sodexo.base.datasource.api.retrofit.RestApi;
import com.zzivi.sodexo.login.datasource.api.LoginApi;
import com.zzivi.sodexo.login.datasource.api.model.CookiesResultModel;
import com.zzivi.sodexo.login.datasource.api.model.LoginRequestApiModel;

import java.util.List;

import javax.inject.Inject;

import retrofit.client.Header;
import retrofit.client.Response;

/**
 * Created by daniel on 11/10/14.
 */
public class LoginApiRetrofit implements LoginApi {

    private final ApiRetrofit apiRetrofit;

    @Inject
    public LoginApiRetrofit(ApiRetrofit apiRetrofit) {
        this.apiRetrofit = apiRetrofit;
    }

    @Override
    public CookiesResultModel obtainCookies(LoginRequestApiModel credentials) {
        CookiesResultModel cookiesResultModel = new CookiesResultModel();

        RestApi restApi = apiRetrofit.buildRestApi();
        Response response =  restApi.login(credentials.getUsername(), credentials.getPassword(),
                credentials.getPwdaux(), credentials.getMantenerSesion());

        List<Header> headers = response.getHeaders();
        for(Header header : headers) {
            if ("Set-Cookie".equals(header.getName())) {
                cookiesResultModel.setCookie(header.getValue());
            }
        }
        return cookiesResultModel;
    }
}
