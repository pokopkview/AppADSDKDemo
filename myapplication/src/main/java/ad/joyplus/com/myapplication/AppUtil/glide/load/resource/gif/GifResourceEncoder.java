package ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif;

import android.graphics.Bitmap;
import android.util.Log;

import ad.joyplus.com.myapplication.AppUtil.glide.gifdecoder.GifDecoder;
import ad.joyplus.com.myapplication.AppUtil.glide.gifdecoder.GifHeader;
import ad.joyplus.com.myapplication.AppUtil.glide.gifdecoder.GifHeaderParser;
import ad.joyplus.com.myapplication.AppUtil.glide.gifencoder.AnimatedGifEncoder;
import ad.joyplus.com.myapplication.AppUtil.glide.load.ResourceEncoder;
import ad.joyplus.com.myapplication.AppUtil.glide.load.Transformation;
import ad.joyplus.com.myapplication.AppUtil.glide.load.engine.Resource;
import ad.joyplus.com.myapplication.AppUtil.glide.load.engine.bitmap_recycle.BitmapPool;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.UnitTransformation;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.bitmap.BitmapResource;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif.*;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif.GifDrawable;
import ad.joyplus.com.myapplication.AppUtil.glide.util.LogTime;

import java.io.IOException;
import java.io.OutputStream;

/**
 * An {@link ResourceEncoder} that can write
 * {@link ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif.GifDrawable} to cache.
 */
public class GifResourceEncoder implements ResourceEncoder<ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif.GifDrawable> {
    private static final Factory FACTORY = new Factory();
    private static final String TAG = "GifEncoder";
    private final GifDecoder.BitmapProvider provider;
    private final BitmapPool bitmapPool;
    private final Factory factory;

    public GifResourceEncoder(BitmapPool bitmapPool) {
        this(bitmapPool, FACTORY);
    }

    // Visible for testing.
    GifResourceEncoder(BitmapPool bitmapPool, Factory factory) {
        this.bitmapPool = bitmapPool;
        provider = new ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif.GifBitmapProvider(bitmapPool);
        this.factory = factory;
    }

    @Override
    public boolean encode(Resource<ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif.GifDrawable> resource, OutputStream os) {
        long startTime = LogTime.getLogTime();

        ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif.GifDrawable drawable = resource.get();
        Transformation<Bitmap> transformation = drawable.getFrameTransformation();
        if (transformation instanceof UnitTransformation) {
            return writeDataDirect(drawable.getData(), os);
        }

        GifDecoder decoder = decodeHeaders(drawable.getData());

        AnimatedGifEncoder encoder = factory.buildEncoder();
        if (!encoder.start(os)) {
            return false;
        }

        for (int i = 0; i < decoder.getFrameCount(); i++) {
            Bitmap currentFrame = decoder.getNextFrame();
            Resource<Bitmap> transformedResource = getTransformedFrame(currentFrame, transformation, drawable);
            try {
                if (!encoder.addFrame(transformedResource.get())) {
                    return false;
                }
                int currentFrameIndex = decoder.getCurrentFrameIndex();
                int delay = decoder.getDelay(currentFrameIndex);
                encoder.setDelay(delay);

                decoder.advance();
            } finally {
                transformedResource.recycle();
            }
        }

        boolean result = encoder.finish();

        if (Log.isLoggable(TAG, Log.VERBOSE)) {
            Log.v(TAG, "Encoded gif with " + decoder.getFrameCount() + " frames and " + drawable.getData().length
                    + " bytes in " + LogTime.getElapsedMillis(startTime) + " ms");
        }

        return result;
    }

    private boolean writeDataDirect(byte[] data, OutputStream os) {
        boolean success = true;
        try {
            os.write(data);
        } catch (IOException e) {
            if (Log.isLoggable(TAG, Log.DEBUG)) {
                Log.d(TAG, "Failed to write data to output stream in GifResourceEncoder", e);
            }
            success = false;
        }
        return success;
    }

    private GifDecoder decodeHeaders(byte[] data) {
        GifHeaderParser parser = factory.buildParser();
        parser.setData(data);
        GifHeader header = parser.parseHeader();

        GifDecoder decoder = factory.buildDecoder(provider);
        decoder.setData(header, data);
        decoder.advance();

        return decoder;
    }

    private Resource<Bitmap> getTransformedFrame(Bitmap currentFrame, Transformation<Bitmap> transformation,
            GifDrawable drawable) {
        // TODO: what if current frame is null?
        Resource<Bitmap> bitmapResource = factory.buildFrameResource(currentFrame, bitmapPool);
        Resource<Bitmap> transformedResource = transformation.transform(bitmapResource,
                drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        if (!bitmapResource.equals(transformedResource)) {
            bitmapResource.recycle();
        }
        return transformedResource;
    }

    @Override
    public String getId() {
        return "";
    }

    // Visible for testing.
    static class Factory {

        public GifDecoder buildDecoder(GifDecoder.BitmapProvider bitmapProvider) {
            return new GifDecoder(bitmapProvider);
        }

        public GifHeaderParser buildParser() {
            return new GifHeaderParser();
        }

        public AnimatedGifEncoder buildEncoder() {
            return new AnimatedGifEncoder();
        }

        public Resource<Bitmap> buildFrameResource(Bitmap bitmap, BitmapPool bitmapPool) {
            return new BitmapResource(bitmap, bitmapPool);
        }
    }
}
