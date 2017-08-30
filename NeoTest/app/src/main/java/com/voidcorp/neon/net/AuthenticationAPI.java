package com.voidcorp.neon.net;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AuthenticationAPI {

    @GET("GenerateToken")
    Call<String> getToken(@Query("nome") final String name, @Query("email") final String email);

}
