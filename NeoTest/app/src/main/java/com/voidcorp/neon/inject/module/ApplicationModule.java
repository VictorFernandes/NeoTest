package com.voidcorp.neon.inject.module;

import android.app.Application;

import com.voidcorp.neon.NeoTestApp;

import dagger.Module;
import dagger.Provides;

/**
 * Provide application-level dependencies.
 */
@Module(includes = {DataModule.class})
public class ApplicationModule {

    protected final NeoTestApp application;

    public ApplicationModule(NeoTestApp application) {
        this.application = application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

}