package com.zzivi.sodexo.login.datasource.imp;

import com.zzivi.sodexo.base.datasource.sharedpreferences.imp.SessionDataSourceSharedPreferences;
import com.zzivi.sodexo.login.datasource.HomeDataSource;
import com.zzivi.sodexo.login.datasource.api.HomeApi;
import com.zzivi.sodexo.login.datasource.api.model.CookiesResultModel;
import com.zzivi.sodexo.login.domain.mapper.LoginMapper;

import java.io.IOException;

import javax.inject.Inject;

/**
 * Created by daniel on 27/09/14.
 */
public class HomeDataSourceFromApiAndSharedPreferences implements HomeDataSource {
    public final LoginMapper loginMapper;
    public final HomeApi homeApi;
    public final SessionDataSourceSharedPreferences storeCookies;

    @Inject
    public HomeDataSourceFromApiAndSharedPreferences(LoginMapper loginMapper, HomeApi homeApi,
                                                     SessionDataSourceSharedPreferences storeCookies) {
        this.loginMapper = loginMapper;
        this.homeApi = homeApi;
        this.storeCookies = storeCookies;
    }

    public boolean getCookies() throws IOException {

        //get cookies
        CookiesResultModel cookiesResultModel = homeApi.obtainCookies();

        //store cookies
        this.storeCookies.storeLoginCookie(cookiesResultModel);

        return true;
    }
}
