package com.zzivi.sodexo.cardsbalance.view.controller;

import com.zzivi.sodexo.cardsbalance.domain.model.CardBalanceResultModel;
import com.zzivi.sodexo.cardsbalance.domain.callback.CardsBalanceCallback;
import com.zzivi.sodexo.cardsbalance.domain.usercase.CardsBalance;

import java.util.List;

/**
 * Created by daniel on 28/09/14.
 */
public class CardsBalanceController {
    private View view;
    private CardsBalance cardsBalance;
    private List<CardBalanceResultModel> listCardsBalance;

    public List<CardBalanceResultModel> getListCardsBalance() {
        return listCardsBalance;
    }



    public CardsBalanceController(CardsBalance cardsBalance){
        this.cardsBalance = cardsBalance;

    }

    public void setView(View view) {
        this.view = view;
    }

    private CardsBalanceCallback cardsBalanceCallback = new CardsBalanceCallback() {
        @Override
        public void showCards(List<CardBalanceResultModel> cardsBalance) {
            listCardsBalance = cardsBalance;
            view.showComplete();
        }
    };

    public void showCardsBalance() {
        cardsBalance.showCardsBalance(cardsBalanceCallback);
    }

    public interface View {
        void showComplete();
    }
}
