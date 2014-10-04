package com.zzivi.sodexo.cardsbalance.datasource.httpurl;

import com.zzivi.sodexo.cardsbalance.datasource.model.CardBalanceResultModel;

import java.io.IOException;
import java.util.List;

/**
 * Created by daniel on 28/09/14.
 */
public interface CardsBalanceDataSource {
    List<CardBalanceResultModel> getCardBalances() throws IOException;
}
