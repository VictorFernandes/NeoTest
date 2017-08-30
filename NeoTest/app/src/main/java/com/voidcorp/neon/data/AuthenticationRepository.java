package com.voidcorp.neon.data;

import com.voidcorp.neon.data.source.AuthenticationDataSource;

public class AuthenticationRepository implements AuthenticationDataSource {

    private final AuthenticationDataSource mLocalDataSource;
    private final AuthenticationDataSource mRemoteDataSource;

    public AuthenticationRepository(final AuthenticationDataSource local, final AuthenticationDataSource remote) {
        mLocalDataSource = local;
        mRemoteDataSource = remote;
    }

    @Override
    public void login(final String name, final String email, final OnSignInListener listener) {
        final OnSignInListener _listener = new OnSignInListener() {
            boolean skipRemoteIfFails = false;
            @Override
            public void onSignIn(final String token) {
                listener.onSignIn(token);
                save(token);
            }
            @Override
            public void onFail() {
                if(skipRemoteIfFails) {
                    listener.onFail();
                } else {
                    skipRemoteIfFails = true;
                    mRemoteDataSource.login(name, email, this);
                }
            }
        };
        mLocalDataSource.login(name, email, _listener);
    }

    @Override
    public void save(final String token) {
        mLocalDataSource.save(token);
        mRemoteDataSource.save(token);
    }
}
