package ad.joyplus.com.myapplication.AppUtil.glide.load.model.stream;

import android.content.Context;
import android.net.Uri;

import ad.joyplus.com.myapplication.AppUtil.glide.Glide;
import ad.joyplus.com.myapplication.AppUtil.glide.load.data.DataFetcher;
import ad.joyplus.com.myapplication.AppUtil.glide.load.data.StreamAssetPathFetcher;
import ad.joyplus.com.myapplication.AppUtil.glide.load.data.StreamLocalUriFetcher;
import ad.joyplus.com.myapplication.AppUtil.glide.load.model.GenericLoaderFactory;
import ad.joyplus.com.myapplication.AppUtil.glide.load.model.GlideUrl;
import ad.joyplus.com.myapplication.AppUtil.glide.load.model.ModelLoader;
import ad.joyplus.com.myapplication.AppUtil.glide.load.model.ModelLoaderFactory;
import ad.joyplus.com.myapplication.AppUtil.glide.load.model.UriLoader;
import ad.joyplus.com.myapplication.AppUtil.glide.load.model.stream.StreamModelLoader;

import java.io.InputStream;

/**
 * A {@link ModelLoader} for translating uri models into {@link InputStream} data. Capable of handling 'http',
 * 'https', 'android.resource', 'content', and 'file' schemes. Unsupported schemes will throw an exception in
 * {@link #getResourceFetcher(Uri, int, int)}.
 */
public class StreamUriLoader extends UriLoader<InputStream> implements StreamModelLoader<Uri> {

    /**
     * THe default factory for {@link ad.joyplus.com.myapplication.AppUtil.glide.load.model.stream.StreamUriLoader}s.
     */
    public static class Factory implements ModelLoaderFactory<Uri, InputStream> {

        @Override
        public ModelLoader<Uri, InputStream> build(Context context, GenericLoaderFactory factories) {
            return new StreamUriLoader(context, factories.buildModelLoader(GlideUrl.class, InputStream.class));
        }

        @Override
        public void teardown() {
            // Do nothing.
        }
    }

    public StreamUriLoader(Context context) {
        this(context, Glide.buildStreamModelLoader(GlideUrl.class, context));
    }

    public StreamUriLoader(Context context, ModelLoader<GlideUrl, InputStream> urlLoader) {
        super(context, urlLoader);
    }

    @Override
    protected DataFetcher<InputStream> getLocalUriFetcher(Context context, Uri uri) {
        return new StreamLocalUriFetcher(context, uri);
    }

    @Override
    protected DataFetcher<InputStream> getAssetPathFetcher(Context context, String assetPath) {
        return new StreamAssetPathFetcher(context.getApplicationContext().getAssets(), assetPath);
    }
}
