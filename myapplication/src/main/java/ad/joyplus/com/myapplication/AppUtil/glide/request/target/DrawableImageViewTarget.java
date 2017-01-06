package ad.joyplus.com.myapplication.AppUtil.glide.request.target;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import ad.joyplus.com.myapplication.AppUtil.glide.request.target.*;

/**
 * A target for display {@link Drawable} objects in {@link ImageView}s.
 */
public class DrawableImageViewTarget extends ad.joyplus.com.myapplication.AppUtil.glide.request.target.ImageViewTarget<Drawable> {
    public DrawableImageViewTarget(ImageView view) {
        super(view);
    }

    @Override
    protected void setResource(Drawable resource) {
       view.setImageDrawable(resource);
    }
}
