package com.zzivi.sodexo.cardsbalance.datasource.httpurl;

import java.io.IOException;

/**
 * Created by daniel on 28/09/14.
 */
public interface CardsBalanceDataSource {
    String getCardBalances() throws IOException;
}
