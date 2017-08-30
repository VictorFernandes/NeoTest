package com.voidcorp.neon.inject.component;


import com.voidcorp.neon.inject.module.ActivityModule;
import com.voidcorp.neon.inject.scope.PerActivity;
import com.voidcorp.neon.view.ActMain;

import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {

    void inject(final ActMain activity);

}
