package ad.joyplus.com.myapplication.AppUtil.glide.load.resource.transcode;

import android.content.Context;
import android.graphics.Bitmap;

import ad.joyplus.com.myapplication.AppUtil.glide.load.engine.Resource;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.drawable.GlideDrawable;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.transcode.*;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.transcode.GlideBitmapDrawableTranscoder;

/**
 * A wrapper for {@link ad.joyplus.com.myapplication.AppUtil.glide.load.resource.transcode.GlideBitmapDrawableTranscoder} that transcodes
 * to {@link GlideDrawable} rather than
 * {@link ad.joyplus.com.myapplication.AppUtil.glide.load.resource.bitmap.GlideBitmapDrawable}.
 *
 * TODO: use ? extends GlideDrawable rather than GlideDrawable directly and remove this class.
 */
public class BitmapToGlideDrawableTranscoder implements ad.joyplus.com.myapplication.AppUtil.glide.load.resource.transcode.ResourceTranscoder<Bitmap, GlideDrawable> {

    private final ad.joyplus.com.myapplication.AppUtil.glide.load.resource.transcode.GlideBitmapDrawableTranscoder glideBitmapDrawableTranscoder;

    public BitmapToGlideDrawableTranscoder(Context context) {
        this(new ad.joyplus.com.myapplication.AppUtil.glide.load.resource.transcode.GlideBitmapDrawableTranscoder(context));
    }

    public BitmapToGlideDrawableTranscoder(GlideBitmapDrawableTranscoder glideBitmapDrawableTranscoder) {
        this.glideBitmapDrawableTranscoder = glideBitmapDrawableTranscoder;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Resource<GlideDrawable> transcode(Resource<Bitmap> toTranscode) {
        return (Resource<GlideDrawable>) (Resource<? extends GlideDrawable>)
                glideBitmapDrawableTranscoder.transcode(toTranscode);
    }

    @Override
    public String getId() {
        return glideBitmapDrawableTranscoder.getId();
    }
}
