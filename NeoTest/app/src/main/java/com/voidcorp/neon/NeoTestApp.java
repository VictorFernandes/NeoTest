package com.voidcorp.neon;

import android.app.Application;

import com.voidcorp.neon.inject.component.ApplicationComponent;
import com.voidcorp.neon.inject.component.DaggerApplicationComponent;
import com.voidcorp.neon.inject.module.ApplicationModule;

import io.realm.Realm;

public class NeoTestApp extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        getComponent().inject(this);

        Realm.init(this);

    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent
                    .builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

}
