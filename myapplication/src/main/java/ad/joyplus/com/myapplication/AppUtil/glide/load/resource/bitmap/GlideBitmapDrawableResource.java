package ad.joyplus.com.myapplication.AppUtil.glide.load.resource.bitmap;

import ad.joyplus.com.myapplication.AppUtil.glide.load.engine.bitmap_recycle.BitmapPool;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.bitmap.*;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.drawable.DrawableResource;
import ad.joyplus.com.myapplication.AppUtil.glide.util.Util;

/**
 * A resource wrapper for {@link ad.joyplus.com.myapplication.AppUtil.glide.load.resource.bitmap.GlideBitmapDrawable}.
 */
public class GlideBitmapDrawableResource extends DrawableResource<ad.joyplus.com.myapplication.AppUtil.glide.load.resource.bitmap.GlideBitmapDrawable> {
    private final BitmapPool bitmapPool;

    public GlideBitmapDrawableResource(ad.joyplus.com.myapplication.AppUtil.glide.load.resource.bitmap.GlideBitmapDrawable drawable, BitmapPool bitmapPool) {
        super(drawable);
        this.bitmapPool = bitmapPool;
    }

    @Override
    public int getSize() {
        return Util.getBitmapByteSize(drawable.getBitmap());
    }

    @Override
    public void recycle() {
        bitmapPool.put(drawable.getBitmap());
    }
}
