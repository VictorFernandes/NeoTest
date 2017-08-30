package com.voidcorp.neon.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import com.voidcorp.neon.BR;

import java.util.Objects;

public class Contact extends BaseObservable implements Parcelable {

    public static final int ERROR = -2;
    public static final int NONE = -1;
    public static final int TRANSFERRING = 0;
    public static final int TRANSFERRED = 1;

    private long id;

    @Bindable
    private String name;

    @Bindable
    private String image;

    @Bindable
    private String phone;

    @Bindable
    private int transferStatus;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
        notifyPropertyChanged(com.voidcorp.neon.BR.name);
    }

    public String getImage() {
        return image;
    }

    public void setImage(final String image) {
        this.image = image;
        notifyPropertyChanged(com.voidcorp.neon.BR.image);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeString(this.image);
    }

    public Contact() {
        setTransferStatus(NONE);
    }

    public Contact(final String id) {
        this(Long.valueOf(id));
    }

    public Contact(final Long id) {
        setId(id);
    }

    protected Contact(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.image = in.readString();
    }

    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    public int getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(final int transferStatus) {
        this.transferStatus = transferStatus;
        notifyPropertyChanged(BR.transferStatus);
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null || !(obj instanceof Contact)) {
            return false;
        } else {
            return Objects.equals(id, ((Contact) obj).id);
        }
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
        notifyPropertyChanged(BR.phone);
    }
}
