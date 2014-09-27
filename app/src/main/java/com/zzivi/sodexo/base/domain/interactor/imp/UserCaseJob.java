package com.zzivi.sodexo.base.domain.interactor.imp;

import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.Params;
import com.zzivi.sodexo.base.domain.interactor.MainThread;

/**
 * Created by daniel on 27/09/14.
 */
public abstract class UserCaseJob extends Job {
    protected static final int DEFAULT_PRIORITY = 2;

    private final MainThread mainThread;
    protected JobManager jobManager;


    protected UserCaseJob(JobManager jobManager, MainThread mainThread, Params params) {
        super(params);
        this.jobManager = jobManager;
        this.mainThread = mainThread;
    }

    protected void sendCallback(Runnable callback) {
        mainThread.post(callback);
    }

    @Override
    public void onAdded() {

    }

    @Override
    public void onRun() throws Throwable {
        doRun();
    }

    public abstract void doRun() throws Throwable;

    @Override
    protected void onCancel() {}

    @Override
    protected boolean shouldReRunOnThrowable(Throwable throwable) {
        return false;
    }
}
