package com.voidcorp.neon.data.local;

import com.securepreferences.SecurePreferences;
import com.voidcorp.neon.data.source.AuthenticationDataSource;

public class AuthenticationLocalDataSource implements AuthenticationDataSource {

    public static final String TOKEN = "TOKEN";

    private final SecurePreferences mDatabase;

    public AuthenticationLocalDataSource(final SecurePreferences database) {
        mDatabase = database;
    }

    @Override
    public void login(final String name, final String email, final OnSignInListener listener) {
        final String value = mDatabase.getString(TOKEN, TOKEN);
        if (TOKEN.equals(value)) {
            listener.onFail();
        } else {
            listener.onSignIn(value);
        }
    }

    @Override
    public void save(final String token) {
        final SecurePreferences.Editor editor = mDatabase.edit();
        editor.putString(TOKEN, token);
        editor.apply();
    }

}
