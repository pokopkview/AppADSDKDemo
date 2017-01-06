package ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap;

import android.graphics.Bitmap;

import ad.joyplus.com.myapplication.AppUtil.glide.load.ResourceEncoder;
import ad.joyplus.com.myapplication.AppUtil.glide.load.engine.Resource;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif.GifDrawable;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap.*;

import java.io.OutputStream;

/**
 * A {@link ResourceEncoder} that can encode either an {@link Bitmap} or
 * {@link GifDrawable}.
 */
public class GifBitmapWrapperResourceEncoder implements ResourceEncoder<ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap.GifBitmapWrapper> {
    private final ResourceEncoder<Bitmap> bitmapEncoder;
    private final ResourceEncoder<GifDrawable> gifEncoder;
    private String id;

    public GifBitmapWrapperResourceEncoder(ResourceEncoder<Bitmap> bitmapEncoder,
            ResourceEncoder<GifDrawable> gifEncoder) {
        this.bitmapEncoder = bitmapEncoder;
        this.gifEncoder = gifEncoder;
    }

    @Override
    public boolean encode(Resource<ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap.GifBitmapWrapper> resource, OutputStream os) {
        final ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap.GifBitmapWrapper gifBitmap = resource.get();
        final Resource<Bitmap> bitmapResource = gifBitmap.getBitmapResource();

        if (bitmapResource != null) {
            return bitmapEncoder.encode(bitmapResource, os);
        } else {
            return gifEncoder.encode(gifBitmap.getGifResource(), os);
        }
    }

    @Override
    public String getId() {
        if (id == null) {
            id = bitmapEncoder.getId() + gifEncoder.getId();
        }
        return id;
    }
}
