package ad.joyplus.com.myapplication.AppUtil.glide.request.target;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.drawable.GlideDrawable;
import ad.joyplus.com.myapplication.AppUtil.glide.request.target.*;
import ad.joyplus.com.myapplication.AppUtil.glide.request.target.Target;

/**
 * A factory responsible for producing the correct type of {@link ad.joyplus.com.myapplication.AppUtil.glide.request.target.Target} for a given
 * {@link android.view.View} subclass.
 */
public class ImageViewTargetFactory {

    @SuppressWarnings("unchecked")
    public <Z> ad.joyplus.com.myapplication.AppUtil.glide.request.target.Target<Z> buildTarget(ImageView view, Class<Z> clazz) {
        if (GlideDrawable.class.isAssignableFrom(clazz)) {
            return (ad.joyplus.com.myapplication.AppUtil.glide.request.target.Target<Z>) new GlideDrawableImageViewTarget(view);
        } else if (Bitmap.class.equals(clazz)) {
            return (ad.joyplus.com.myapplication.AppUtil.glide.request.target.Target<Z>) new ad.joyplus.com.myapplication.AppUtil.glide.request.target.BitmapImageViewTarget(view);
        } else if (Drawable.class.isAssignableFrom(clazz)) {
            return (Target<Z>) new DrawableImageViewTarget(view);
        } else {
            throw new IllegalArgumentException("Unhandled class: " + clazz
                    + ", try .as*(Class).transcode(ResourceTranscoder)");
        }
    }
}
