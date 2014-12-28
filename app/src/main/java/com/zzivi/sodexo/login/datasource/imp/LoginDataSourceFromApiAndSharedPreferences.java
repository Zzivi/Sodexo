package com.zzivi.sodexo.login.datasource.imp;

import com.zzivi.sodexo.base.datasource.sharedpreferences.imp.SessionDataSourceSharedPreferences;
import com.zzivi.sodexo.login.datasource.LoginDataSource;
import com.zzivi.sodexo.login.datasource.api.LoginApi;
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
    public final SessionDataSourceSharedPreferences storeCookies;

    @Inject
    public LoginDataSourceFromApiAndSharedPreferences(LoginMapper loginMapper, LoginApi loginApi,
                                                      SessionDataSourceSharedPreferences storeCookies) {
        this.loginMapper = loginMapper;
        this.loginApi = loginApi;
        this.storeCookies = storeCookies;
    }

    public boolean getCookies(LoginCredentials loginCredentials) throws IOException {

        //do login
        LoginRequestApiModel loginRequestApiModel = loginMapper.transform(loginCredentials);
        loginApi.doLogin(loginRequestApiModel);
        //set logged true
        storeCookies.setLoggedIn(true);

        return true;
    }
}
