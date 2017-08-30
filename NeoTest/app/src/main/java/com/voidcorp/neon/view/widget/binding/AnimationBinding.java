package com.voidcorp.neon.view.widget.binding;

import android.databinding.BindingAdapter;
import android.view.View;

public class AnimationBinding {

    private static final long DEFAULT_DURATION = 250;

    @BindingAdapter("fade")
    public static void setFadeInOutVisibility(final View view, final boolean visible) {
        view.setVisibility(View.VISIBLE);
        if(visible) {
            view.animate()
                    .setDuration(DEFAULT_DURATION)
                    .alpha(1f)
                    .start();
        } else {
            view.animate()
                    .setDuration(DEFAULT_DURATION)
                    .alpha(0f)
                    .start();
        }
    }

}
