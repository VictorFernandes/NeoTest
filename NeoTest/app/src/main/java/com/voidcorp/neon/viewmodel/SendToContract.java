package com.voidcorp.neon.viewmodel;

public interface SendToContract {

    interface ViewModelContract {

        void send(final int position, final double amount);

        String getImage(final int position);

        boolean canSend(final int position);

    }

}
