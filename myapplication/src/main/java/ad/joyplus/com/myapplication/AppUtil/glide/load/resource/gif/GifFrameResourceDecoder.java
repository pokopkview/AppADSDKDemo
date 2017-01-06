package ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif;

import android.graphics.Bitmap;

import ad.joyplus.com.myapplication.AppUtil.glide.gifdecoder.GifDecoder;
import ad.joyplus.com.myapplication.AppUtil.glide.load.ResourceDecoder;
import ad.joyplus.com.myapplication.AppUtil.glide.load.engine.Resource;
import ad.joyplus.com.myapplication.AppUtil.glide.load.engine.bitmap_recycle.BitmapPool;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.bitmap.BitmapResource;

class GifFrameResourceDecoder implements ResourceDecoder<GifDecoder, Bitmap> {
    private final BitmapPool bitmapPool;

    public GifFrameResourceDecoder(BitmapPool bitmapPool) {
        this.bitmapPool = bitmapPool;
    }

    @Override
    public Resource<Bitmap> decode(GifDecoder source, int width, int height) {
        Bitmap bitmap = source.getNextFrame();
        return BitmapResource.obtain(bitmap, bitmapPool);
    }

    @Override
    public String getId() {
        return "GifFrameResourceDecoder.ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif";
    }
}
