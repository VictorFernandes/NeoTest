package com.voidcorp.neon.inject.component;


import android.app.Application;

import com.voidcorp.neon.NeoTestApp;
import com.voidcorp.neon.inject.module.ApplicationModule;

import dagger.Component;

@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    Application application();

    void inject(final NeoTestApp application);

}