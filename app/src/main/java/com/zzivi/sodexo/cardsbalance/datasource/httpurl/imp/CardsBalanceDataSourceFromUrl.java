package com.zzivi.sodexo.cardsbalance.datasource.httpurl.imp;

import com.zzivi.sodexo.base.datasource.httpurl.request.HttpRequestDataSource;
import com.zzivi.sodexo.base.datasource.httpurl.request.imp.HttpRequestDataSourceImpl;
import com.zzivi.sodexo.base.datasource.httpurl.sharedpreferences.LoginCookieDataSource;
import com.zzivi.sodexo.base.datasource.httpurl.sharedpreferences.imp.LoginCookieDataSourceSharedPreferences;
import com.zzivi.sodexo.cardsbalance.datasource.httpurl.CardsBalanceDataSource;
import com.zzivi.sodexo.cardsbalance.datasource.model.CardBalanceResultModel;
import com.zzivi.sodexo.login.datasource.httpurl.model.HttpUrlResultModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public List<CardBalanceResultModel> getCardBalances() throws IOException {
        HttpUrlResultModel httpUrlResultModel = storeCookies.obtainLoginCookie();
        String cardName = httpRequestDataSource.getPageContent("http://www.mysodexo.es/includes/modulo-saldos-tarjeta.php",
                httpUrlResultModel);


        //TODO: dumcardNamemy result
        List<CardBalanceResultModel> cardsBalance= new ArrayList<CardBalanceResultModel>();
        CardBalanceResultModel cardBalance = new CardBalanceResultModel();
        cardBalance.setCardBalance("99 euros");
        cardBalance.setCardName(cardName);
        cardsBalance.add(cardBalance);
        return cardsBalance;
    }
}
