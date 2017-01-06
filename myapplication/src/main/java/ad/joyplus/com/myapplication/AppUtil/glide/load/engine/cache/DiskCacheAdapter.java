package ad.joyplus.com.myapplication.AppUtil.glide.load.engine.cache;


import java.io.File;

import ad.joyplus.com.myapplication.AppUtil.glide.load.Key;

/**
 * A simple class that returns null for all gets and ignores all writes.
 */
public class DiskCacheAdapter implements DiskCache {
    @Override
    public File get(Key key) {
        // no op, default for overriders
        return null;
    }

    @Override
    public void put(Key key, Writer writer) {
        // no op, default for overriders
    }

    @Override
    public void delete(Key key) {
        // no op, default for overriders
    }

    @Override
    public void clear() {
        // no op, default for overriders
    }
}
