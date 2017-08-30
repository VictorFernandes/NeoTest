package com.voidcorp.neon.inject.component;

import com.voidcorp.neon.inject.module.FragmentModule;
import com.voidcorp.neon.inject.scope.PerFragment;
import com.voidcorp.neon.view.FragHistory;
import com.voidcorp.neon.view.FragProfile;
import com.voidcorp.neon.view.FragSendTo;

import dagger.Subcomponent;

@PerFragment
@Subcomponent(modules = {FragmentModule.class})
public interface FragmentComponent {

    void inject(final FragProfile fragment);

    void inject(final FragSendTo fragment);

    void inject(final FragHistory fragment);
}
