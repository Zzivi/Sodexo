package com.zzivi.sodexo.cardsbalance.domain.callback;

import com.zzivi.sodexo.cardsbalance.domain.model.CardBalanceResultModel;

import java.util.List;

/**
 * Created by daniel on 28/09/14.
 */
public interface CardsBalanceCallback {
    void showCards(List<CardBalanceResultModel> cards);
}
