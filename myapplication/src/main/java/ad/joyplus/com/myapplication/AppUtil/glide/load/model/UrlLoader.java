package ad.joyplus.com.myapplication.AppUtil.glide.load.model;

import ad.joyplus.com.myapplication.AppUtil.glide.load.data.DataFetcher;
import ad.joyplus.com.myapplication.AppUtil.glide.load.model.*;

import java.net.URL;

/**
 * A wrapper class that translates {@link URL} objects into {@link ad.joyplus.com.myapplication.AppUtil.glide.load.model.GlideUrl}
 * objects and then uses the wrapped {@link ad.joyplus.com.myapplication.AppUtil.glide.load.model.ModelLoader} for
 * {@link ad.joyplus.com.myapplication.AppUtil.glide.load.model.GlideUrl}s to load the data.
 *
 * @param <T> The type of data that will be loaded from the {@link URL}s.
 */
public class UrlLoader<T> implements ModelLoader<URL, T> {
    private final ModelLoader<ad.joyplus.com.myapplication.AppUtil.glide.load.model.GlideUrl, T> glideUrlLoader;

    public UrlLoader(ModelLoader<ad.joyplus.com.myapplication.AppUtil.glide.load.model.GlideUrl, T> glideUrlLoader) {
        this.glideUrlLoader = glideUrlLoader;
    }

    @Override
    public DataFetcher<T> getResourceFetcher(URL model, int width, int height) {
        return glideUrlLoader.getResourceFetcher(new ad.joyplus.com.myapplication.AppUtil.glide.load.model.GlideUrl(model), width, height);
    }
}
