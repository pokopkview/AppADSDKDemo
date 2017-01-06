package ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif;

import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.drawable.DrawableResource;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif.*;
import ad.joyplus.com.myapplication.AppUtil.glide.util.Util;

/**
 * A resource wrapping an {@link ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif.GifDrawable}.
 */
public class GifDrawableResource extends DrawableResource<ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif.GifDrawable> {
    public GifDrawableResource(ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif.GifDrawable drawable) {
        super(drawable);
    }

    @Override
    public int getSize() {
        return drawable.getData().length + Util.getBitmapByteSize(drawable.getFirstFrame());
    }

    @Override
    public void recycle() {
        drawable.stop();
        drawable.recycle();
    }
}
