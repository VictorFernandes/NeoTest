package com.voidcorp.neon.viewmodel;

import android.databinding.ObservableField;

import com.voidcorp.neon.data.AuthenticationRepository;
import com.voidcorp.neon.data.source.AuthenticationDataSource;
import com.voidcorp.neon.viewmodel.BaseViewModel;

import javax.inject.Inject;

public class ProfileViewModel extends BaseViewModel {

    private static final String NAME = "Victor Fernandes";
    private static final String EMAIL = "victorfernandes91@hotmail.com";
    private static final String IMAGE = "https://media.licdn.com/mpr/mpr/shrinknp_400_400/p/1/000/2cf/2ab/1d2dcd0.jpg";
    private static final String LINKED_IN = "https://www.linkedin.com/in/victorrfernandess/";

    private final AuthenticationDataSource.OnSignInListener mOnSignInListener = new AuthenticationDataSource.OnSignInListener() {
        @Override
        public void onSignIn(final String token) {

        }

        @Override
        public void onFail() {

        }
    };


    public final ObservableField<String> name = new ObservableField<>(NAME);
    public final ObservableField<String> email = new ObservableField<>(EMAIL);
    public final ObservableField<String> image = new ObservableField<>(IMAGE);
    public final ObservableField<String> linkedIn = new ObservableField<>(LINKED_IN);

    @Inject
    AuthenticationRepository mRepository;

    @Inject
    ProfileViewModel() {

    }

    @Override
    public void load() {
        mRepository.login(name.get(), email.get(), mOnSignInListener);
    }

}
