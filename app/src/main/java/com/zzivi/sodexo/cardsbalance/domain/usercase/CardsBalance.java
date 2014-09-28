package com.zzivi.sodexo.cardsbalance.domain.usercase;

import com.zzivi.sodexo.cardsbalance.domain.callback.CardsBalanceCallback;

/**
 * Created by daniel on 28/09/14.
 */
public interface CardsBalance {
    void showCardsBalance(CardsBalanceCallback callback);
}
