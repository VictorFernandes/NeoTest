package com.voidcorp.neon.inject.module;

import android.content.Context;

import com.securepreferences.SecurePreferences;
import com.voidcorp.neon.BuildConfig;
import com.voidcorp.neon.data.AuthenticationRepository;
import com.voidcorp.neon.data.ContactRepository;
import com.voidcorp.neon.data.TransferRepository;
import com.voidcorp.neon.data.local.AuthenticationLocalDataSource;
import com.voidcorp.neon.data.local.ContactLocalDataSource;
import com.voidcorp.neon.data.local.TransferLocalDataSource;
import com.voidcorp.neon.data.remote.AuthenticationRemoteDataSource;
import com.voidcorp.neon.data.remote.ContactRemoteDataSource;
import com.voidcorp.neon.data.remote.TransferRemoteDataSource;
import com.voidcorp.neon.data.source.TransferDataSource;
import com.voidcorp.neon.net.AuthenticationAPI;
import com.voidcorp.neon.net.TransferAPI;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class DataModule {

    private Realm mLocalDatabase;
    private SecurePreferences mSharedPreferences;

    private AuthenticationAPI mAuthenticationAPI;
    private TransferAPI mTransferAPI;

    public DataModule() {

    }

    public DataModule(final Context context) {
        buildNetAPIs();
        mLocalDatabase = Realm.getDefaultInstance();
        mSharedPreferences = new SecurePreferences(context, "neotst", "neo_prefs.xml");
    }

    private void buildNetAPIs() {
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient.addInterceptor(loggingInterceptor);

        final Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(BuildConfig.SERVER_BASE_URL);
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.client(httpClient.build());

        final Retrofit retrofit = builder.build();
        mAuthenticationAPI = retrofit.create(AuthenticationAPI.class);
        mTransferAPI = retrofit.create(TransferAPI.class);
    }

    @Provides
    public AuthenticationRepository providesAuthenticationRepository() {
        final AuthenticationRemoteDataSource remote = new AuthenticationRemoteDataSource(mAuthenticationAPI);
        final AuthenticationLocalDataSource local = new AuthenticationLocalDataSource(mSharedPreferences);
        return new AuthenticationRepository(local, remote);
    }

    @Provides
    public TransferRepository providesTransferRepository() {
        final String token = mSharedPreferences.getString(AuthenticationLocalDataSource.TOKEN, AuthenticationLocalDataSource.TOKEN);
        final TransferDataSource local = new TransferLocalDataSource(mLocalDatabase);
        final TransferDataSource remote = new TransferRemoteDataSource(mTransferAPI, token);
        return new TransferRepository(local, remote);
    }

    @Provides
    public ContactRepository providesContactRepository() {
        final ContactLocalDataSource local = new ContactLocalDataSource();
        final ContactRemoteDataSource remote = new ContactRemoteDataSource();
        return new ContactRepository(local, remote);
    }

}
