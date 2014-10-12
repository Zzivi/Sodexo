package com.zzivi.sodexo.cardsbalance.datasource;

import com.zzivi.sodexo.cardsbalance.datasource.api.CardsBalanceApi;
import com.zzivi.sodexo.cardsbalance.datasource.api.retrofit.CardsBalanceApiRetrofit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by daniel on 28/09/14.
 */
@Module(complete = false, library = true)
public class CardsBalanceDataSourceModule {

    @Provides
    @Singleton
    public CardsBalanceApi provideCardsBalanceApi(CardsBalanceApiRetrofit cardsBalanceApiRetrofit) {
        return cardsBalanceApiRetrofit;
    }
}
