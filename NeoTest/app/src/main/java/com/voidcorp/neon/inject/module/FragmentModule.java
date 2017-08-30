package com.voidcorp.neon.inject.module;

import android.support.v4.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {

    private final Fragment mFragment;
    private RequestManager mGlide;

    public FragmentModule(final Fragment fragment) {
        mFragment = fragment;
        mGlide = Glide.with(fragment);
    }

    @Provides
    Fragment provideFragment() {
        return mFragment;
    }

    @Provides
    RequestManager providesGlide() {
        return mGlide;
    }

}
