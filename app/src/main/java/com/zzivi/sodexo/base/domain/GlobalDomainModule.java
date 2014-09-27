package com.zzivi.sodexo.base.domain;

import android.content.Context;

import com.path.android.jobqueue.JobManager;
import com.zzivi.sodexo.base.daggerutils.ForApplication;
import com.zzivi.sodexo.base.domain.interactor.MainThread;
import com.zzivi.sodexo.base.domain.interactor.imp.MainThreadHandler;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by daniel on 27/09/14.
 */
@Module(complete = false,
        library = true)
public class GlobalDomainModule {
    @Provides
    @Singleton
    JobManager provideJobManager(@ForApplication Context context) {
        return new JobManager(context);
    }

    @Provides @Singleton
    MainThread provideMainThread(MainThreadHandler mainThreadHandler) {
        return mainThreadHandler;
    }
}