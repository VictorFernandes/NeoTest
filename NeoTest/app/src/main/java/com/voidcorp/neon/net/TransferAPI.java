package com.voidcorp.neon.net;

import com.voidcorp.neon.model.Transfer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TransferAPI {

    @POST("SendMoney")
    Call<Boolean> send(@Body final Transfer transfer);

    @FormUrlEncoded
    @POST("SendMoney")
    Call<Boolean> sendMoney(@Field("ClienteId") final String clientID, @Field("valor") final double value, @Field("token") final String token);

    @GET("GetTransfers")
    Call<List<Transfer>> getTransfers(@Query("token") final String token);

}
