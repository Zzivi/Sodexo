package com.zzivi.sodexo.login.datasource.imp;

import com.zzivi.sodexo.base.datasource.sharedpreferences.imp.LoginCookieDataSourceSharedPreferences;
import com.zzivi.sodexo.login.datasource.LoginDataSource;
import com.zzivi.sodexo.login.datasource.api.LoginApi;
import com.zzivi.sodexo.login.datasource.api.model.CookiesResultModel;
import com.zzivi.sodexo.login.datasource.api.model.LoginRequestApiModel;
import com.zzivi.sodexo.login.domain.mapper.LoginMapper;
import com.zzivi.sodexo.login.domain.model.LoginCredentials;

import java.io.IOException;

import javax.inject.Inject;

/**
 * Created by daniel on 27/09/14.
 */
public class LoginDataSourceFromApiAndSharedPreferences implements LoginDataSource {
    public final LoginMapper loginMapper;
    public final LoginApi loginApi;
    public final LoginCookieDataSourceSharedPreferences storeCookies;

    @Inject
    public LoginDataSourceFromApiAndSharedPreferences(LoginMapper loginMapper, LoginApi loginApi,
                                                      LoginCookieDataSourceSharedPreferences storeCookies) {
        this.loginMapper = loginMapper;
        this.loginApi = loginApi;
        this.storeCookies = storeCookies;
    }

    public boolean getCookies(LoginCredentials loginCredentials) throws IOException {

        LoginRequestApiModel loginRequestApiModel = loginMapper.transform(loginCredentials);
        CookiesResultModel cookiesResultModel = loginApi.obtainCookies(loginRequestApiModel);
        //HttpUrlResultModel httpUrlResultModel = loginHttpUrl.obtainCookies(loginRequestUrlModel);
        //store cookies
        this.storeCookies.storeLoginCookie(cookiesResultModel);
        return true;
    }



}
