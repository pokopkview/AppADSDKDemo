package ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif;

import android.content.Context;

import ad.joyplus.com.myapplication.AppUtil.glide.load.Encoder;
import ad.joyplus.com.myapplication.AppUtil.glide.load.ResourceDecoder;
import ad.joyplus.com.myapplication.AppUtil.glide.load.ResourceEncoder;
import ad.joyplus.com.myapplication.AppUtil.glide.load.engine.bitmap_recycle.BitmapPool;
import ad.joyplus.com.myapplication.AppUtil.glide.load.model.StreamEncoder;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.file.FileToStreamDecoder;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif.*;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif.GifDrawable;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif.GifResourceEncoder;
import ad.joyplus.com.myapplication.AppUtil.glide.provider.DataLoadProvider;

import java.io.File;
import java.io.InputStream;

/**
 * An {@link DataLoadProvider} that loads an {@link InputStream} into
 * {@link ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif.GifDrawable} that can be used to display an animated GIF.
 */
public class GifDrawableLoadProvider implements DataLoadProvider<InputStream, ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif.GifDrawable> {
    private final ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif.GifResourceDecoder decoder;
    private final ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif.GifResourceEncoder encoder;
    private final StreamEncoder sourceEncoder;
    private final FileToStreamDecoder<ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif.GifDrawable> cacheDecoder;

    public GifDrawableLoadProvider(Context context, BitmapPool bitmapPool) {
        decoder = new ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif.GifResourceDecoder(context, bitmapPool);
        cacheDecoder = new FileToStreamDecoder<ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif.GifDrawable>(decoder);
        encoder = new GifResourceEncoder(bitmapPool);
        sourceEncoder = new StreamEncoder();
    }

    @Override
    public ResourceDecoder<File, ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif.GifDrawable> getCacheDecoder() {
        return cacheDecoder;
    }

    @Override
    public ResourceDecoder<InputStream, ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif.GifDrawable> getSourceDecoder() {
        return decoder;
    }

    @Override
    public Encoder<InputStream> getSourceEncoder() {
        return sourceEncoder;
    }

    @Override
    public ResourceEncoder<GifDrawable> getEncoder() {
        return encoder;
    }
}
