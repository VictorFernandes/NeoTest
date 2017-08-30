package com.voidcorp.neon.inject.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.voidcorp.neon.inject.scope.ActivityContext;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private RequestManager mGlide;
    private AppCompatActivity mActivity;

    public ActivityModule(final AppCompatActivity activity) {
        mActivity = activity;
        mGlide = Glide.with(mActivity);
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    RequestManager providesGlide() {
        return mGlide;
    }

    @Provides
    @ActivityContext
    Context providesContext() {
        return mActivity;
    }
}
