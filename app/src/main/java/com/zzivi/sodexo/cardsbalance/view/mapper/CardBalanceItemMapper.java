package com.zzivi.sodexo.cardsbalance.view.mapper;

import com.zzivi.sodexo.cardsbalance.domain.model.CardBalanceResultModel;
import com.zzivi.sodexo.cardsbalance.view.model.CardBalanceItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 5/10/14.
 */
public class CardBalanceItemMapper {

    public List<CardBalanceItem> transform(List<CardBalanceResultModel> cardBalanceResuls) {
        List<CardBalanceItem> cardBalanceItems = new ArrayList<CardBalanceItem>();
        for(CardBalanceResultModel cardBalanceResultModel : cardBalanceResuls) {
            CardBalanceItem cardBalanceItem = new CardBalanceItem();
            cardBalanceItem.setCardName(cardBalanceResultModel.getCardName());
            cardBalanceItem.setCardBalance(cardBalanceResultModel.getCardBalance());
            cardBalanceItems.add(cardBalanceItem);
        }
        return cardBalanceItems;
    }

}
