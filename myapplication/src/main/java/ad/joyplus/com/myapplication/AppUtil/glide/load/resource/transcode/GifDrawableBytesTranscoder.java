package ad.joyplus.com.myapplication.AppUtil.glide.load.resource.transcode;

import ad.joyplus.com.myapplication.AppUtil.glide.load.engine.Resource;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.bytes.BytesResource;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.gif.GifDrawable;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.transcode.*;

/**
 * An {@link ad.joyplus.com.myapplication.AppUtil.glide.load.resource.transcode.ResourceTranscoder} that converts
 * {@link GifDrawable} into bytes by obtaining the original bytes of the GIF from
 * the {@link GifDrawable}.
 */
public class GifDrawableBytesTranscoder implements ad.joyplus.com.myapplication.AppUtil.glide.load.resource.transcode.ResourceTranscoder<GifDrawable, byte[]> {
    @Override
    public Resource<byte[]> transcode(Resource<GifDrawable> toTranscode) {
        GifDrawable gifData = toTranscode.get();
        return new BytesResource(gifData.getData());
    }

    @Override
    public String getId() {
        return "GifDrawableBytesTranscoder.ad.joyplus.com.myapplication.AppUtil.glide.load.resource.transcode";
    }
}
