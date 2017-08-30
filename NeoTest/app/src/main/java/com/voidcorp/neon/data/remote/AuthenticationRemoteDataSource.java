package com.voidcorp.neon.data.remote;

import com.voidcorp.neon.data.source.AuthenticationDataSource;
import com.voidcorp.neon.net.AuthenticationAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AuthenticationRemoteDataSource implements AuthenticationDataSource {

    private final AuthenticationAPI mAuthAPI;

    public AuthenticationRemoteDataSource(final AuthenticationAPI api) {
        mAuthAPI = api;
    }

    @Override
    public void login(final String name, final String email, final OnSignInListener listener) {
        final Call<String> call = mAuthAPI.getToken(name, email);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(final Call<String> call, final Response<String> response) {
                if(response.isSuccessful()) {
                    listener.onSignIn(response.body());
                } else {
                    listener.onFail();
                }
            }

            @Override
            public void onFailure(final Call<String> call, final Throwable t) {
                listener.onFail();
            }
        });
    }

    @Override
    public void save(final String token) {
        //TBD
    }

}
