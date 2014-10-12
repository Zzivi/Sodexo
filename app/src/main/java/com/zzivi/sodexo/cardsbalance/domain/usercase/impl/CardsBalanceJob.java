package com.zzivi.sodexo.cardsbalance.domain.usercase.impl;

import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.Params;
import com.zzivi.sodexo.base.domain.interactor.MainThread;
import com.zzivi.sodexo.base.domain.interactor.imp.UserCaseJob;
import com.zzivi.sodexo.cardsbalance.datasource.api.CardsBalanceApi;
import com.zzivi.sodexo.cardsbalance.domain.mapper.CardBalanceMapper;
import com.zzivi.sodexo.cardsbalance.domain.model.CardBalanceResultModel;
import com.zzivi.sodexo.cardsbalance.domain.callback.CardsBalanceCallback;
import com.zzivi.sodexo.cardsbalance.domain.usercase.CardsBalance;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by daniel on 28/09/14.
 */
public class CardsBalanceJob extends UserCaseJob implements CardsBalance{

    private CardsBalanceApi cardsBalanceApi;
    private CardsBalanceCallback callback;
    private CardBalanceMapper cardBalanceMapper;

    @Inject
    protected CardsBalanceJob(JobManager jobManager, MainThread mainThread,
                              CardsBalanceApi cardsBalanceApi,
                           CardBalanceMapper cardBalanceMapper) {
        super(jobManager, mainThread, new Params(UserCaseJob.DEFAULT_PRIORITY));
        this.cardsBalanceApi = cardsBalanceApi;
        this.cardBalanceMapper = cardBalanceMapper;
    }

    private void notifyCardsLoaded(final List<CardBalanceResultModel> cardsBalance) {
        sendCallback(new Runnable() {
            @Override public void run() {
                callback.showCards(cardsBalance);
            }
        });
    }
    @Override public void doRun() throws Throwable {
        List<CardBalanceResultModel> cardsBalance = cardBalanceMapper.transform(cardsBalanceApi.getCardBalances());
        notifyCardsLoaded(cardsBalance);
    }

    @Override
    public void showCardsBalance(CardsBalanceCallback callback) {
        this.callback = callback;
        jobManager.addJob(this);
    }
}
