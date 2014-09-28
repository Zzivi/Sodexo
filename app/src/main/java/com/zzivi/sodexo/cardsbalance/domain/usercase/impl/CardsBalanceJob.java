package com.zzivi.sodexo.cardsbalance.domain.usercase.impl;

import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.Params;
import com.zzivi.sodexo.base.domain.interactor.MainThread;
import com.zzivi.sodexo.base.domain.interactor.imp.UserCaseJob;
import com.zzivi.sodexo.cardsbalance.datasource.CardsBalanceDataSource;
import com.zzivi.sodexo.cardsbalance.datasource.model.CardBalanceResultModel;
import com.zzivi.sodexo.cardsbalance.domain.callback.CardsBalanceCallback;
import com.zzivi.sodexo.cardsbalance.domain.usercase.CardsBalance;
import com.zzivi.sodexo.login.datasource.LoginDataSource;
import com.zzivi.sodexo.login.domain.callback.LoginCallback;
import com.zzivi.sodexo.login.domain.model.LoginCredentials;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by daniel on 28/09/14.
 */
public class CardsBalanceJob extends UserCaseJob implements CardsBalance{
    private CardsBalanceDataSource cardsBalanceDataSource;
    private CardsBalanceCallback callback;

    @Inject
    protected CardsBalanceJob(JobManager jobManager, MainThread mainThread,
                           CardsBalanceDataSource cardsBalanceDataSource) {
        super(jobManager, mainThread, new Params(UserCaseJob.DEFAULT_PRIORITY));
        this.cardsBalanceDataSource = cardsBalanceDataSource;
    }

    private void notifyCardsLoaded(final List<CardBalanceResultModel> cardsBalance) {
        sendCallback(new Runnable() {
            @Override public void run() {
                callback.showCards(cardsBalance);
            }
        });
    }
    @Override public void doRun() throws Throwable {
        List<CardBalanceResultModel> cardsBalance = cardsBalanceDataSource.getCardBalances();
        notifyCardsLoaded(cardsBalance);
    }

    @Override
    public void showCardsBalance(CardsBalanceCallback callback) {
        this.callback = callback;
        jobManager.addJob(this);
    }
}
