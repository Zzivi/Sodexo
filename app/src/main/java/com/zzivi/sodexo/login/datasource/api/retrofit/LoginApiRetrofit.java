package com.zzivi.sodexo.login.datasource.api.retrofit;

import com.zzivi.sodexo.base.datasource.api.retrofit.ApiRetrofit;
import com.zzivi.sodexo.base.datasource.api.retrofit.RestApi;
import com.zzivi.sodexo.base.datasource.api.retrofit.exceptions.ApiUnauthorizedException;
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
    public boolean doLogin(LoginRequestApiModel credentials) {
        RestApi restApi = apiRetrofit.buildRestApi();

        Response response =  restApi.login(credentials.getUsername(), credentials.getPassword(),
                credentials.getPwdaux(), credentials.getMantenerSesion());

        // throw login error depending on the location
        if (!"http://www.mysodexo.es/editar-mi-perfil".equals(response.getUrl())) {
            throw new ApiUnauthorizedException();
        }
        return true;
    }
}
