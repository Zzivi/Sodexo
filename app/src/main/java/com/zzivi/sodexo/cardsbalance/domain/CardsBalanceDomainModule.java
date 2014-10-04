package com.zzivi.sodexo.cardsbalance.domain;

import com.zzivi.sodexo.cardsbalance.domain.mapper.CardBalanceMapper;
import com.zzivi.sodexo.cardsbalance.domain.usercase.CardsBalance;
import com.zzivi.sodexo.cardsbalance.domain.usercase.impl.CardsBalanceJob;

import dagger.Module;
import dagger.Provides;

/**
 * Created by daniel on 28/09/14.
 */
@Module(complete = false,
        library = true)
public class CardsBalanceDomainModule {
    @Provides
    public CardsBalance provideCardsBalance(CardsBalanceJob cardsBalanceJob) {
        return cardsBalanceJob;
    }

    @Provides
    public CardBalanceMapper proveCardBalanceMapper() {
        return new CardBalanceMapper();
    }
}
