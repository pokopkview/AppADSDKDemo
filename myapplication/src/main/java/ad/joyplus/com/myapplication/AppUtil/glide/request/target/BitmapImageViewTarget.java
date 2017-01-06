package ad.joyplus.com.myapplication.AppUtil.glide.request.target;

import android.graphics.Bitmap;
import android.widget.ImageView;

import ad.joyplus.com.myapplication.AppUtil.glide.request.target.*;

/**
 * A {@link ad.joyplus.com.myapplication.AppUtil.glide.request.target.Target} that can display an {@link Bitmap} in an
 * {@link ImageView}.
 *
 * @see GlideDrawableImageViewTarget
 */
public class BitmapImageViewTarget extends ad.joyplus.com.myapplication.AppUtil.glide.request.target.ImageViewTarget<Bitmap> {
    public BitmapImageViewTarget(ImageView view) {
        super(view);
    }

    /**
     * Sets the {@link Bitmap} on the view using
     * {@link ImageView#setImageBitmap(Bitmap)}.
     *
     * @param resource The bitmap to display.
     */
    @Override
    protected void setResource(Bitmap resource) {
        view.setImageBitmap(resource);
    }
}
