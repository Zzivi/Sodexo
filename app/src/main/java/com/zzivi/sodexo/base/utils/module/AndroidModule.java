package com.zzivi.sodexo.base.utils.module;

import android.content.Context;

import com.squareup.otto.Bus;
import com.zzivi.sodexo.base.application.BaseApplication;
import com.zzivi.sodexo.base.daggerutils.ForApplication;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * A module for Android-specific dependencies which require a {@link android.content.Context} or
 * {@link android.app.Application} to create.
 */
@Module(library = true)
public class AndroidModule {
    private final BaseApplication application;

    public AndroidModule(BaseApplication application) {
        this.application = application;
    }

    /**
     * Allow the application context to be injected but require that it be annotated with
     * {@link ForApplication @Annotation} to explicitly differentiate it from an activity context.
     */
    @Provides
    @Singleton
    @ForApplication
    Context provideApplicationContext() {
        return application;
    }
    //@Provides @Singleton
    //Bus provideBusProvider() {	return new MainThreadBus(); }
}