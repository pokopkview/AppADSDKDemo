package ad.joyplus.com.myapplication.AppUtil.glide.load.resource.transcode;

import android.graphics.Bitmap;

import ad.joyplus.com.myapplication.AppUtil.glide.load.engine.Resource;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.bitmap.GlideBitmapDrawable;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.drawable.GlideDrawable;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap.GifBitmapWrapper;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.transcode.*;

/**
 * An {@link ad.joyplus.com.myapplication.AppUtil.glide.load.resource.transcode.ResourceTranscoder} that can transcode either an
 * {@link Bitmap} or an {@link ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif.GifDrawable} into an
 * {@link android.graphics.drawable.Drawable}.
 */
public class GifBitmapWrapperDrawableTranscoder implements ad.joyplus.com.myapplication.AppUtil.glide.load.resource.transcode.ResourceTranscoder<GifBitmapWrapper, GlideDrawable> {
    private final ad.joyplus.com.myapplication.AppUtil.glide.load.resource.transcode.ResourceTranscoder<Bitmap, GlideBitmapDrawable> bitmapDrawableResourceTranscoder;

    public GifBitmapWrapperDrawableTranscoder(
            ad.joyplus.com.myapplication.AppUtil.glide.load.resource.transcode.ResourceTranscoder<Bitmap, GlideBitmapDrawable> bitmapDrawableResourceTranscoder) {
        this.bitmapDrawableResourceTranscoder = bitmapDrawableResourceTranscoder;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Resource<GlideDrawable> transcode(Resource<GifBitmapWrapper> toTranscode) {
        GifBitmapWrapper gifBitmap = toTranscode.get();
        Resource<Bitmap> bitmapResource = gifBitmap.getBitmapResource();

        final Resource<? extends GlideDrawable> result;
        if (bitmapResource != null) {
            result = bitmapDrawableResourceTranscoder.transcode(bitmapResource);
        } else {
            result = gifBitmap.getGifResource();
        }
        // This is unchecked but always safe, anything that extends a Drawable can be safely cast to a Drawable.
        return (Resource<GlideDrawable>) result;
    }

    @Override
    public String getId() {
        return "GifBitmapWrapperDrawableTranscoder.ad.joyplus.com.myapplication.AppUtil.glide.load.resource.transcode";
    }
}
