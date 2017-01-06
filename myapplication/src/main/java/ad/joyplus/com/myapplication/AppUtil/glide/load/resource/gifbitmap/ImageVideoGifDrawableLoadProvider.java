package ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap;

import android.graphics.Bitmap;

import ad.joyplus.com.myapplication.AppUtil.glide.load.Encoder;
import ad.joyplus.com.myapplication.AppUtil.glide.load.ResourceDecoder;
import ad.joyplus.com.myapplication.AppUtil.glide.load.ResourceEncoder;
import ad.joyplus.com.myapplication.AppUtil.glide.load.engine.bitmap_recycle.BitmapPool;
import ad.joyplus.com.myapplication.AppUtil.glide.load.model.ImageVideoWrapper;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.file.FileToStreamDecoder;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif.GifDrawable;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap.*;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap.GifBitmapWrapper;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap.GifBitmapWrapperResourceEncoder;
import ad.joyplus.com.myapplication.AppUtil.glide.provider.DataLoadProvider;

import java.io.File;
import java.io.InputStream;

/**
 * An {@link DataLoadProvider} that can load either an
 * {@link GifDrawable} or an {@link Bitmap} from either an
 * {@link InputStream} or an {@link android.os.ParcelFileDescriptor}.
 */
public class ImageVideoGifDrawableLoadProvider implements DataLoadProvider<ImageVideoWrapper, ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap.GifBitmapWrapper> {
    private final ResourceDecoder<File, ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap.GifBitmapWrapper> cacheDecoder;
    private final ResourceDecoder<ImageVideoWrapper, ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap.GifBitmapWrapper> sourceDecoder;
    private final ResourceEncoder<ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap.GifBitmapWrapper> encoder;
    private final Encoder<ImageVideoWrapper> sourceEncoder;

    public ImageVideoGifDrawableLoadProvider(DataLoadProvider<ImageVideoWrapper, Bitmap> bitmapProvider,
            DataLoadProvider<InputStream, GifDrawable> gifProvider, BitmapPool bitmapPool) {

        final ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap.GifBitmapWrapperResourceDecoder decoder = new ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap.GifBitmapWrapperResourceDecoder(
                bitmapProvider.getSourceDecoder(),
                gifProvider.getSourceDecoder(),
                bitmapPool
        );
        cacheDecoder = new FileToStreamDecoder<ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap.GifBitmapWrapper>(new GifBitmapWrapperStreamResourceDecoder(decoder));
        sourceDecoder = decoder;
        encoder = new GifBitmapWrapperResourceEncoder(bitmapProvider.getEncoder(), gifProvider.getEncoder());

        //TODO: what about the gif provider?
        sourceEncoder = bitmapProvider.getSourceEncoder();
    }

    @Override
    public ResourceDecoder<File, ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap.GifBitmapWrapper> getCacheDecoder() {
        return cacheDecoder;
    }

    @Override
    public ResourceDecoder<ImageVideoWrapper, ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap.GifBitmapWrapper> getSourceDecoder() {
        return sourceDecoder;
    }

    @Override
    public Encoder<ImageVideoWrapper> getSourceEncoder() {
        return sourceEncoder;
    }

    @Override
    public ResourceEncoder<GifBitmapWrapper> getEncoder() {
        return encoder;
    }
}
