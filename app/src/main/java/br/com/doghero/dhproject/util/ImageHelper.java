package br.com.doghero.dhproject.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageHelper {
    public static void loadImage(Context context, String imageUrl, int placeHolderResourceId, ImageView imageView) {
        Picasso.with(context)
                .load(imageUrl)
                .placeholder(placeHolderResourceId)
                .into(imageView);
    }

    public static Drawable getDrawableFromId(Context context, int drawableId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getResources().getDrawable(drawableId, context.getTheme());
        } else {
            return context.getDrawable(drawableId);
        }
    }
}
