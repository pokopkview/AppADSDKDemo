package ad.joyplus.com.myapplication.AppUtil.glide.load.model.stream;

import android.content.Context;
import android.net.Uri;

import ad.joyplus.com.myapplication.AppUtil.glide.Glide;
import ad.joyplus.com.myapplication.AppUtil.glide.load.model.GenericLoaderFactory;
import ad.joyplus.com.myapplication.AppUtil.glide.load.model.ModelLoader;
import ad.joyplus.com.myapplication.AppUtil.glide.load.model.ModelLoaderFactory;
import ad.joyplus.com.myapplication.AppUtil.glide.load.model.StringLoader;
import ad.joyplus.com.myapplication.AppUtil.glide.load.model.stream.*;

import java.io.InputStream;

/**
 * A {@link ModelLoader} for translating {@link String} models, such as file paths or remote urls, into
 * {@link InputStream} data.
 */
public class StreamStringLoader extends StringLoader<InputStream> implements ad.joyplus.com.myapplication.AppUtil.glide.load.model.stream.StreamModelLoader<String> {

    /**
     * The default factory for {@link ad.joyplus.com.myapplication.AppUtil.glide.load.model.stream.StreamStringLoader}s.
     */
    public static class Factory implements ModelLoaderFactory<String, InputStream> {
        @Override
        public ModelLoader<String, InputStream> build(Context context, GenericLoaderFactory factories) {
            return new StreamStringLoader(factories.buildModelLoader(Uri.class, InputStream.class));
        }

        @Override
        public void teardown() {
            // Do nothing.
        }
    }

    public StreamStringLoader(Context context) {
        this(Glide.buildStreamModelLoader(Uri.class, context));
    }

    public StreamStringLoader(ModelLoader<Uri, InputStream> uriLoader) {
        super(uriLoader);
    }
}
