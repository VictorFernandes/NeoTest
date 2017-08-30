package com.voidcorp.neon.view.widget.binding;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.voidcorp.neon.view.image.CropCircleTransformation;
import com.voidcorp.neon.view.image.GlideApp;

public class GlideAdapter {

    @BindingAdapter(value = {"url", "placeholder"}, requireAll = false)
    public static void setImageUrl(final ImageView imageView, String url, final Drawable placeholder) {
        if (url == null) {
            imageView.setImageDrawable(null);
        } else {
            GlideApp
                    .with(imageView)
                    .load(Uri.parse(url))
                    .transform(new CropCircleTransformation(imageView.getContext()))
                    .placeholder(placeholder)
                    .into(imageView);
        }
    }

}
