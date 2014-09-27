package com.zzivi.sodexo.login.datasource.imp;

import com.zzivi.sodexo.login.datasource.LoginDataSource;
import com.zzivi.sodexo.login.datasource.model.LoginRequestUrlModel;
import com.zzivi.sodexo.login.domain.mapper.LoginMapper;
import com.zzivi.sodexo.login.domain.model.LoginCredentials;

import javax.inject.Inject;

/**
 * Created by daniel on 27/09/14.
 */
public class LoginDataSourceFromUrl implements LoginDataSource {
    public final LoginMapper loginMapper;

    @Inject
    public LoginDataSourceFromUrl(LoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }

    public boolean getLegalNumber(LoginCredentials loginCredentials){

        String legalNumber = getLegalNumber(loginMapper.transform(loginCredentials));
        System.out.println("Legal number: " + legalNumber);
        //this.storeOauthAuthorize.storeOauthAuthorize(oauthAuthorizeResultApiModel);

        return true;
    }


    public String getLegalNumber(LoginRequestUrlModel loginRequestUrl){

        return "2abHcER4W3Y2abW3Y2abH3Y2abHcrCsXAwBbHcERd0sXAwByzgOhijklfgOhijk";

        //this.storeOauthAuthorize.storeOauthAuthorize(oauthAuthorizeResultApiModel);
    }
}
