package com.zzivi.sodexo.cardsbalance.domain.callback;

import com.zzivi.sodexo.cardsbalance.datasource.model.CardBalanceResultModel;
import com.zzivi.sodexo.cardsbalance.domain.usercase.CardsBalance;

import java.util.List;

/**
 * Created by daniel on 28/09/14.
 */
public interface CardsBalanceCallback {
    void showCards(List<CardBalanceResultModel> cards);
}
