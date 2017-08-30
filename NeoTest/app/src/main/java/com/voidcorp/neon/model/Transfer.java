package com.voidcorp.neon.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Transfer extends RealmObject {

    @SerializedName("ClienteId")
    private String client;

    @SerializedName("Token")
    private String token;

    @SerializedName("Valor")
    private double amount;

    public String getClient() {
        return client;
    }

    public void setClient(final String client) {
        this.client = client;
    }

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(final double amount) {
        this.amount = amount;
    }

    public Transfer() {

    }

    public Transfer(final String client, final double amount) {
        this.client = client;
        this.amount = amount;
    }
}
