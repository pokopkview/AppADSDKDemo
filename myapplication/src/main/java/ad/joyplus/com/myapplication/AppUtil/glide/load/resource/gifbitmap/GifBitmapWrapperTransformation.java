package ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap;

import android.graphics.Bitmap;

import ad.joyplus.com.myapplication.AppUtil.glide.load.Transformation;
import ad.joyplus.com.myapplication.AppUtil.glide.load.engine.Resource;
import ad.joyplus.com.myapplication.AppUtil.glide.load.engine.bitmap_recycle.BitmapPool;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif.GifDrawable;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif.GifDrawableTransformation;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap.*;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap.GifBitmapWrapperResource;

/**
 * A {@link Transformation} that can apply a wrapped {@link Bitmap}
 * transformation to both {@link Bitmap}s and {@link GifDrawable}.
 */
public class GifBitmapWrapperTransformation implements Transformation<ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap.GifBitmapWrapper> {
    private final Transformation<Bitmap> bitmapTransformation;
    private final Transformation<GifDrawable> gifDataTransformation;

    public GifBitmapWrapperTransformation(BitmapPool bitmapPool, Transformation<Bitmap> bitmapTransformation) {
        this(bitmapTransformation, new GifDrawableTransformation(bitmapTransformation, bitmapPool));
    }

    GifBitmapWrapperTransformation(Transformation<Bitmap> bitmapTransformation,
            Transformation<GifDrawable> gifDataTransformation) {
        this.bitmapTransformation = bitmapTransformation;
        this.gifDataTransformation = gifDataTransformation;
    }

    @Override
    public Resource<ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap.GifBitmapWrapper> transform(Resource<ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap.GifBitmapWrapper> resource, int outWidth, int outHeight) {
        Resource<Bitmap> bitmapResource = resource.get().getBitmapResource();
        Resource<GifDrawable> gifResource = resource.get().getGifResource();
        if (bitmapResource != null && bitmapTransformation != null) {
            Resource<Bitmap> transformed = bitmapTransformation.transform(bitmapResource, outWidth, outHeight);
            if (!bitmapResource.equals(transformed)) {
                ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap.GifBitmapWrapper gifBitmap = new ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap.GifBitmapWrapper(transformed, resource.get().getGifResource());
                return new ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap.GifBitmapWrapperResource(gifBitmap);
            }
        } else if (gifResource != null && gifDataTransformation != null) {
            Resource<GifDrawable> transformed = gifDataTransformation.transform(gifResource, outWidth, outHeight);
            if (!gifResource.equals(transformed)) {
                ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap.GifBitmapWrapper gifBitmap = new ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap.GifBitmapWrapper(resource.get().getBitmapResource(), transformed);
                return new GifBitmapWrapperResource(gifBitmap);
            }
        }
        return resource;
    }

    @Override
    public String getId() {
        return bitmapTransformation.getId();
    }
}
