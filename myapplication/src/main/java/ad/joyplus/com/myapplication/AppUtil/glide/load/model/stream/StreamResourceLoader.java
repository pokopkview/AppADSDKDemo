package ad.joyplus.com.myapplication.AppUtil.glide.load.model.stream;

import android.content.Context;
import android.net.Uri;

import ad.joyplus.com.myapplication.AppUtil.glide.Glide;
import ad.joyplus.com.myapplication.AppUtil.glide.load.model.GenericLoaderFactory;
import ad.joyplus.com.myapplication.AppUtil.glide.load.model.ModelLoader;
import ad.joyplus.com.myapplication.AppUtil.glide.load.model.ModelLoaderFactory;
import ad.joyplus.com.myapplication.AppUtil.glide.load.model.ResourceLoader;
import ad.joyplus.com.myapplication.AppUtil.glide.load.model.stream.*;

import java.io.InputStream;

/**
 * A {@link ModelLoader} For translating android resource id models for local uris into {@link InputStream} data.
 */
public class StreamResourceLoader extends ResourceLoader<InputStream> implements ad.joyplus.com.myapplication.AppUtil.glide.load.model.stream.StreamModelLoader<Integer> {

    /**
     * The default factory for {@link ad.joyplus.com.myapplication.AppUtil.glide.load.model.stream.StreamResourceLoader}s.
     */
    public static class Factory implements ModelLoaderFactory<Integer, InputStream> {

        @Override
        public ModelLoader<Integer, InputStream> build(Context context, GenericLoaderFactory factories) {
            return new StreamResourceLoader(context, factories.buildModelLoader(Uri.class, InputStream.class));
        }

        @Override
        public void teardown() {
            // Do nothing.
        }
    }

    public StreamResourceLoader(Context context) {
        this(context, Glide.buildStreamModelLoader(Uri.class, context));
    }

    public StreamResourceLoader(Context context, ModelLoader<Uri, InputStream> uriLoader) {
        super(context, uriLoader);
    }
}
