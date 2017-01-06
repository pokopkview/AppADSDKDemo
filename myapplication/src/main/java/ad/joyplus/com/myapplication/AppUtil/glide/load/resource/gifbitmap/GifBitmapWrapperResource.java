package ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap;

import android.graphics.Bitmap;

import ad.joyplus.com.myapplication.AppUtil.glide.load.engine.Resource;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif.GifDrawable;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap.*;

/**
 * A resource that wraps an {@link ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap.GifBitmapWrapper}.
 */
public class GifBitmapWrapperResource implements Resource<ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap.GifBitmapWrapper> {
    private final ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap.GifBitmapWrapper data;

    public GifBitmapWrapperResource(ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap.GifBitmapWrapper data) {
        if (data == null) {
            throw new NullPointerException("Data must not be null");
        }
        this.data = data;
    }

    @Override
    public ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap.GifBitmapWrapper get() {
        return data;
    }

    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public void recycle() {
        Resource<Bitmap> bitmapResource = data.getBitmapResource();
        if (bitmapResource != null) {
            bitmapResource.recycle();
        }
        Resource<GifDrawable> gifDataResource = data.getGifResource();
        if (gifDataResource != null) {
            gifDataResource.recycle();
        }
    }
}
