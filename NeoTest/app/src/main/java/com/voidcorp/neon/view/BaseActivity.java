package com.voidcorp.neon.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.voidcorp.neon.NeoTestApp;
import com.voidcorp.neon.inject.component.ActivityComponent;
import com.voidcorp.neon.inject.component.DaggerConfigPersistentComponent;
import com.voidcorp.neon.inject.module.ActivityModule;
import com.voidcorp.neon.inject.module.DataModule;

public class BaseActivity extends AppCompatActivity {

    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final NeoTestApp application = (NeoTestApp) getApplication();
        mActivityComponent = DaggerConfigPersistentComponent.builder()
                .applicationComponent(application.getComponent())
                .dataModule(new DataModule(this))
                .build()
                .activityComponent(new ActivityModule(this));
    }

    protected final ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

}
