package com.zzivi.sodexo.cardsbalance.view.model;

import com.zzivi.sodexo.cardsbalance.domain.usercase.CardsBalance;
import com.zzivi.sodexo.cardsbalance.view.activity.phone.CardsBalanceActivity;
import com.zzivi.sodexo.cardsbalance.view.controller.CardsBalanceController;
import com.zzivi.sodexo.cardsbalance.view.fragment.CardsBalanceFragment;


import dagger.Module;
import dagger.Provides;

/**
 * Created by daniel on 28/09/14.
 */
@Module(injects = { CardsBalanceActivity.class, CardsBalanceFragment.class}, complete = false, library = true)

public class CardsBalanceViewModule {
    @Provides
    public CardsBalanceController provideCardsBalanceController(CardsBalance cardsBalance){
        return new CardsBalanceController(cardsBalance);
    }
}
