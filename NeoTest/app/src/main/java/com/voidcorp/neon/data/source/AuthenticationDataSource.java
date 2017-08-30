package com.voidcorp.neon.data.source;

public interface AuthenticationDataSource {

    interface OnSignInListener {

        void onSignIn(final String token);

        void onFail();

    }

    void login(final String name, final String email, final OnSignInListener listener);

    void save(final String token);

}
