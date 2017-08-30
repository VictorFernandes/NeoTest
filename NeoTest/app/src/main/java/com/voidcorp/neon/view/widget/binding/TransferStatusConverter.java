package com.voidcorp.neon.view.widget.binding;

import android.databinding.BindingConversion;
import android.view.View;

public class TransferStatusConverter {

    @BindingConversion
    public static int convertBooleanToVisibility(final boolean value) {
        return value ? View.VISIBLE : View.GONE;
    }

}
