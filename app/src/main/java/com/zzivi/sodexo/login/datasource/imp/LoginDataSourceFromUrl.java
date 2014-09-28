package com.zzivi.sodexo.login.datasource.imp;

import com.zzivi.sodexo.login.datasource.LoginDataSource;
import com.zzivi.sodexo.login.datasource.httpurl.LoginHttpUrl;
import com.zzivi.sodexo.login.datasource.httpurl.model.HttpUrlResultModel;
import com.zzivi.sodexo.login.datasource.httpurl.model.LoginRequestUrlModel;
import com.zzivi.sodexo.login.domain.mapper.LoginMapper;
import com.zzivi.sodexo.login.domain.model.LoginCredentials;

import java.io.IOException;

import javax.inject.Inject;

/**
 * Created by daniel on 27/09/14.
 */
public class LoginDataSourceFromUrl implements LoginDataSource {
    public final LoginMapper loginMapper;
    public final LoginHttpUrl loginHttpUrl;

    @Inject
    public LoginDataSourceFromUrl(LoginMapper loginMapper, LoginHttpUrl loginHttpUrl) {
        this.loginMapper = loginMapper;
        this.loginHttpUrl = loginHttpUrl;
    }

    public boolean getCookies(LoginCredentials loginCredentials) throws IOException {

        HttpUrlResultModel httpUrlResultModel = loginHttpUrl.obtainCookies(loginMapper.transform(loginCredentials));
        //store cookies

        return true;
    }



}
