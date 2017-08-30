package com.voidcorp.neon.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

public class Settings extends RealmObject implements Parcelable {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.token);
    }

    public Settings() {
    }

    protected Settings(Parcel in) {
        this.token = in.readString();
    }

    public static final Parcelable.Creator<Settings> CREATOR = new Parcelable.Creator<Settings>() {
        @Override
        public Settings createFromParcel(Parcel source) {
            return new Settings(source);
        }

        @Override
        public Settings[] newArray(int size) {
            return new Settings[size];
        }
    };
}
