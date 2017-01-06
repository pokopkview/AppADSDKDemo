package ad.joyplus.com.myapplication.AppUtil.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;

import java.io.File;
import java.io.InputStream;

import ad.joyplus.com.myapplication.AppUtil.glide.load.Encoder;
import ad.joyplus.com.myapplication.AppUtil.glide.load.ResourceDecoder;
import ad.joyplus.com.myapplication.AppUtil.glide.load.ResourceEncoder;
import ad.joyplus.com.myapplication.AppUtil.glide.load.model.ImageVideoWrapper;
import ad.joyplus.com.myapplication.AppUtil.glide.load.model.ImageVideoWrapperEncoder;
import ad.joyplus.com.myapplication.AppUtil.glide.provider.DataLoadProvider;

/**
 * A {@link DataLoadProvider} for loading either an {@link InputStream} or an
 * {@link ParcelFileDescriptor} as an {@link Bitmap}.
 */
public class ImageVideoDataLoadProvider implements DataLoadProvider<ImageVideoWrapper, Bitmap> {
    private final ad.joyplus.com.myapplication.AppUtil.glide.load.resource.bitmap.ImageVideoBitmapDecoder sourceDecoder;
    private final ResourceDecoder<File, Bitmap> cacheDecoder;
    private final ResourceEncoder<Bitmap> encoder;
    private final ImageVideoWrapperEncoder sourceEncoder;

    public ImageVideoDataLoadProvider(DataLoadProvider<InputStream, Bitmap> streamBitmapProvider,
            DataLoadProvider<ParcelFileDescriptor, Bitmap> fileDescriptorBitmapProvider) {
        encoder = streamBitmapProvider.getEncoder();
        sourceEncoder = new ImageVideoWrapperEncoder(streamBitmapProvider.getSourceEncoder(),
                fileDescriptorBitmapProvider.getSourceEncoder());
        cacheDecoder = streamBitmapProvider.getCacheDecoder();
        sourceDecoder = new ad.joyplus.com.myapplication.AppUtil.glide.load.resource.bitmap.ImageVideoBitmapDecoder(streamBitmapProvider.getSourceDecoder(),
                fileDescriptorBitmapProvider.getSourceDecoder());
    }

    @Override
    public ResourceDecoder<File, Bitmap> getCacheDecoder() {
        return cacheDecoder;
    }

    @Override
    public ResourceDecoder<ImageVideoWrapper, Bitmap> getSourceDecoder() {
        return sourceDecoder;
    }

    @Override
    public Encoder<ImageVideoWrapper> getSourceEncoder() {
        return sourceEncoder;
    }

    @Override
    public ResourceEncoder<Bitmap> getEncoder() {
        return encoder;
    }
}
