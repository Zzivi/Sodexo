package com.zzivi.sodexo.login.domain.usercase.impl;

import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.Params;
import com.zzivi.sodexo.base.datasource.api.retrofit.exceptions.ApiUnauthorizedException;
import com.zzivi.sodexo.base.domain.interactor.MainThread;
import com.zzivi.sodexo.base.domain.interactor.imp.UserCaseJob;
import com.zzivi.sodexo.login.datasource.HomeDataSource;
import com.zzivi.sodexo.login.domain.callback.HomeCallback;
import com.zzivi.sodexo.login.domain.usercase.Home;

import javax.inject.Inject;

/**
 * Created by daniel on 27/09/14.
 */
public class HomeJob extends UserCaseJob implements Home {

    private HomeCallback callback;
    private HomeDataSource homeDataSource;

    @Inject
    protected HomeJob(JobManager jobManager, MainThread mainThread, HomeDataSource homeDataSource) {
        super(jobManager, mainThread, new Params(UserCaseJob.DEFAULT_PRIORITY));
        this.homeDataSource = homeDataSource;
    }

    @Override
    public void home(HomeCallback callback) {
        this.callback = callback;
        jobManager.addJob(this);
    }

    @Override
    public void doRun() throws Throwable {
        try {
            homeDataSource.getCookies();
            notifyLoginComplete(true, 0);
        } catch (ApiUnauthorizedException ex) {
            notifyLoginComplete(false, ex.getLocalizedMessageResource());
        }
    }

    private void notifyLoginComplete(final boolean loginOk, final int message) {
        sendCallback(new Runnable() {
            @Override public void run() {
                if(loginOk) {
                    callback.complete();
                } else {
                    //callback.onError(message);
                }
            }
        });
    }
}
