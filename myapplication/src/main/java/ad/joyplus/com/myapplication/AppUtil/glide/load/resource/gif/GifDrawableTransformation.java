package ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif;

import android.graphics.Bitmap;

import ad.joyplus.com.myapplication.AppUtil.glide.load.Transformation;
import ad.joyplus.com.myapplication.AppUtil.glide.load.engine.Resource;
import ad.joyplus.com.myapplication.AppUtil.glide.load.engine.bitmap_recycle.BitmapPool;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.bitmap.BitmapResource;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif.*;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif.GifDrawable;

/**
 * An {@link Transformation} that wraps a transformation for a {@link Bitmap}
 * and can apply it to every frame of any {@link ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif.GifDrawable}.
 */
public class GifDrawableTransformation implements Transformation<ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif.GifDrawable> {
    private final Transformation<Bitmap> wrapped;
    private final BitmapPool bitmapPool;

    public GifDrawableTransformation(Transformation<Bitmap> wrapped, BitmapPool bitmapPool) {
        this.wrapped = wrapped;
        this.bitmapPool = bitmapPool;
    }

    @Override
    public Resource<ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif.GifDrawable> transform(Resource<ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif.GifDrawable> resource, int outWidth, int outHeight) {
        ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif.GifDrawable drawable = resource.get();

        // The drawable needs to be initialized with the correct width and height in order for a view displaying it
        // to end up with the right dimensions. Since our transformations may arbitrarily modify the dimensions of
        // our gif, here we create a stand in for a frame and pass it to the transformation to see what the final
        // transformed dimensions will be so that our drawable can report the correct intrinsic width and height.
        Bitmap firstFrame = resource.get().getFirstFrame();
        Resource<Bitmap> bitmapResource = new BitmapResource(firstFrame, bitmapPool);
        Resource<Bitmap> transformed = wrapped.transform(bitmapResource, outWidth, outHeight);
        Bitmap transformedFrame = transformed.get();
        if (!transformedFrame.equals(firstFrame)) {
            return new ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif.GifDrawableResource(new GifDrawable(drawable, transformedFrame, wrapped));
        } else {
            return resource;
        }
    }

    @Override
    public String getId() {
        return wrapped.getId();
    }
}
