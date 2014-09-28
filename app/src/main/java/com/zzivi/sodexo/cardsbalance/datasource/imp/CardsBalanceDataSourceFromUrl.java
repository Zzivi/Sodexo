package com.zzivi.sodexo.cardsbalance.datasource.imp;

import com.zzivi.sodexo.cardsbalance.datasource.CardsBalanceDataSource;
import com.zzivi.sodexo.cardsbalance.datasource.model.CardBalanceResultModel;
import com.zzivi.sodexo.cardsbalance.domain.usercase.CardsBalance;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by daniel on 28/09/14.
 */
public class CardsBalanceDataSourceFromUrl implements CardsBalanceDataSource {

    @Inject
    public CardsBalanceDataSourceFromUrl(){}

    public List<CardBalanceResultModel> getCardBalances(){
        //TODO: dummy result
        List<CardBalanceResultModel> cardsBalance= new ArrayList<CardBalanceResultModel>();
        CardBalanceResultModel cardBalance = new CardBalanceResultModel();
        cardBalance.setCardBalance("99 euros");
        cardBalance.setCardName("Restaurante");
        cardsBalance.add(cardBalance);
        return cardsBalance;
    }
}
