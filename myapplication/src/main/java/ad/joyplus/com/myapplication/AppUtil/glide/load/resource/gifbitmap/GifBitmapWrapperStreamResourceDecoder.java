package ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap;

import ad.joyplus.com.myapplication.AppUtil.glide.load.ResourceDecoder;
import ad.joyplus.com.myapplication.AppUtil.glide.load.engine.Resource;
import ad.joyplus.com.myapplication.AppUtil.glide.load.model.ImageVideoWrapper;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap.*;

import java.io.IOException;
import java.io.InputStream;

/**
 * A {@link ResourceDecoder} that can decode an
 * {@link ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap.GifBitmapWrapper} from {@link InputStream} data.
 */
public class GifBitmapWrapperStreamResourceDecoder implements ResourceDecoder<InputStream, ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap.GifBitmapWrapper> {
    private final ResourceDecoder<ImageVideoWrapper, ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap.GifBitmapWrapper> gifBitmapDecoder;

    public GifBitmapWrapperStreamResourceDecoder(
            ResourceDecoder<ImageVideoWrapper, ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap.GifBitmapWrapper> gifBitmapDecoder) {
        this.gifBitmapDecoder = gifBitmapDecoder;
    }

    @Override
    public Resource<ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gifbitmap.GifBitmapWrapper> decode(InputStream source, int width, int height) throws IOException {
        return gifBitmapDecoder.decode(new ImageVideoWrapper(source, null), width, height);
    }

    @Override
    public String getId() {
        return gifBitmapDecoder.getId();
    }
}
