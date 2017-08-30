package com.voidcorp.neon.inject.component;

import com.voidcorp.neon.inject.module.ActivityModule;
import com.voidcorp.neon.inject.module.DataModule;
import com.voidcorp.neon.inject.module.FragmentModule;
import com.voidcorp.neon.inject.scope.ConfigPersistent;

import dagger.Component;

/**
 * A dagger component that will live during the lifecycle of an Activity but it won't
 * be destroy during configuration changes. Check {@link BaseActivity} or {@link BaseFragment} to see how this components
 * survives configuration changes.
 * Use the {@link ConfigPersistent} scope to annotate dependencies that need to survive
 * configuration changes (for example Presenters).
 */
@ConfigPersistent
@Component(dependencies = {ApplicationComponent.class}, modules = {DataModule.class})
public interface ConfigPersistentComponent {

    ActivityComponent activityComponent(final ActivityModule activityModule);

    FragmentComponent fragmentComponent(final FragmentModule fragmentModule);

}