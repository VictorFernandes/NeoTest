package com.voidcorp.neon.view.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import com.voidcorp.neon.BR;
import com.voidcorp.neon.databinding.DialogInputAmountBinding;


public class DialogInputAmount {

    private static final String DEFAULT_VALUE = "0.0";

    private final View.OnClickListener mOnSendClickListener = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            double value;
            if (TextUtils.isEmpty(amount.get())) {
                value = 0;
            } else {
                value = Double.valueOf(amount.get());
            }
            if (mOnAmountChosenListener != null) {
                mOnAmountChosenListener.onAmountChosen(value, mPosition);
                dismiss();
                amount.set(DEFAULT_VALUE);
                mPosition = -1;
            }
        }
    };

    public ObservableField<String> image = new ObservableField<>();
    public ObservableField<String> amount = new ObservableField<>(DEFAULT_VALUE);

    private DialogInputAmountBinding mBinding;
    private AlertDialog mDialog;

    private int mPosition;

    private OnAmountChosenListener mOnAmountChosenListener;

    public DialogInputAmount(final Context context) {
        mPosition = -1;
        init(context);
    }

    private void init(final Context context) {
        mBinding = DialogInputAmountBinding.inflate(LayoutInflater.from(context));
        mBinding.setVariable(BR.model, this);
        mBinding.dialogSend.setOnClickListener(mOnSendClickListener);

        mDialog = new AlertDialog.Builder(context)
                .setView(mBinding.getRoot())
                .create();
    }

    public void setOnAmountChoosenListener(final OnAmountChosenListener listener) {
        mOnAmountChosenListener = listener;
    }

    public void setOnCancelListener(final DialogInterface.OnCancelListener listener) {
        mDialog.setOnCancelListener(listener);
    }

    public void setOnDismissListener(final DialogInterface.OnDismissListener listener) {
        mDialog.setOnDismissListener(listener);
    }

    public void show(final String image, final int position) {
        this.image.set(image);
        mPosition = position;
        mDialog.show();
    }

    public void dismiss() {
        mDialog.dismiss();
    }

    public interface OnAmountChosenListener {

        void onAmountChosen(final double amount, final int position);

    }

}
