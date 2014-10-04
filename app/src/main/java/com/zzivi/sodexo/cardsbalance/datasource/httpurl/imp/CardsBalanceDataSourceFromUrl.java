package com.zzivi.sodexo.cardsbalance.datasource.httpurl.imp;

import com.zzivi.sodexo.base.datasource.httpurl.request.HttpRequestDataSource;
import com.zzivi.sodexo.base.datasource.httpurl.sharedpreferences.imp.LoginCookieDataSourceSharedPreferences;
import com.zzivi.sodexo.cardsbalance.datasource.httpurl.CardsBalanceDataSource;
import com.zzivi.sodexo.login.datasource.httpurl.model.HttpUrlResultModel;

import java.io.IOException;

import javax.inject.Inject;

/**
 * Created by daniel on 28/09/14.
 */
public class CardsBalanceDataSourceFromUrl implements CardsBalanceDataSource {

    HttpRequestDataSource httpRequestDataSource;
    LoginCookieDataSourceSharedPreferences storeCookies;

    @Inject
    public CardsBalanceDataSourceFromUrl(HttpRequestDataSource httpRequestDataSource,
                                         LoginCookieDataSourceSharedPreferences storeCookies){
        this.storeCookies = storeCookies;
        this.httpRequestDataSource = httpRequestDataSource;
    }

    public String getCardBalances() throws IOException {
        HttpUrlResultModel httpUrlResultModel = storeCookies.obtainLoginCookie();
        String response = httpRequestDataSource.getPageContent("http://www.mysodexo.es/includes/modulo-saldos-tarjeta.php",
                httpUrlResultModel);
        return response;
    }
}
