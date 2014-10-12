package com.zzivi.sodexo.cardsbalance.datasource.api.retrofit;

import com.zzivi.sodexo.base.datasource.api.retrofit.ApiRetrofit;
import com.zzivi.sodexo.base.datasource.api.retrofit.RestApi;
import com.zzivi.sodexo.cardsbalance.datasource.api.CardsBalanceApi;

import javax.inject.Inject;

import retrofit.client.Response;

/**
 * Created by daniel on 12/10/14.
 */
public class CardsBalanceApiRetrofit implements CardsBalanceApi {

    private final ApiRetrofit apiRetrofit;

    @Inject
    public CardsBalanceApiRetrofit(ApiRetrofit apiRetrofit) {
        this.apiRetrofit = apiRetrofit;
    }

    public Response getCardBalances() {
        RestApi restApi = apiRetrofit.buildRestApi();
        return restApi.getCardsBalance();
    }
}
