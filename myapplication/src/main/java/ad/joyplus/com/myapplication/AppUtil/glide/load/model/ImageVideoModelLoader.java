package ad.joyplus.com.myapplication.AppUtil.glide.load.model;

import android.os.ParcelFileDescriptor;
import android.util.Log;

import ad.joyplus.com.myapplication.AppUtil.glide.Priority;
import ad.joyplus.com.myapplication.AppUtil.glide.load.data.DataFetcher;
import ad.joyplus.com.myapplication.AppUtil.glide.load.model.*;
import ad.joyplus.com.myapplication.AppUtil.glide.load.model.ImageVideoWrapper;

import java.io.InputStream;

/**
 * A wrapper model loader that provides both an {@link InputStream} and a
 * {@link ParcelFileDescriptor} for a given model type by wrapping an
 * {@link ad.joyplus.com.myapplication.AppUtil.glide.load.model.ModelLoader} for {@link InputStream}s for the given model type and an
 * {@link ad.joyplus.com.myapplication.AppUtil.glide.load.model.ModelLoader} for {@link ParcelFileDescriptor} for the given model
 * type.
 *
 * @param <A> The model type.
 */
public class ImageVideoModelLoader<A> implements ad.joyplus.com.myapplication.AppUtil.glide.load.model.ModelLoader<A, ad.joyplus.com.myapplication.AppUtil.glide.load.model.ImageVideoWrapper> {
    private static final String TAG = "IVML";

    private final ad.joyplus.com.myapplication.AppUtil.glide.load.model.ModelLoader<A, InputStream> streamLoader;
    private final ad.joyplus.com.myapplication.AppUtil.glide.load.model.ModelLoader<A, ParcelFileDescriptor> fileDescriptorLoader;

    public ImageVideoModelLoader(ad.joyplus.com.myapplication.AppUtil.glide.load.model.ModelLoader<A, InputStream> streamLoader,
                                 ad.joyplus.com.myapplication.AppUtil.glide.load.model.ModelLoader<A, ParcelFileDescriptor> fileDescriptorLoader) {
        if (streamLoader == null && fileDescriptorLoader == null) {
            throw new NullPointerException("At least one of streamLoader and fileDescriptorLoader must be non null");
        }
        this.streamLoader = streamLoader;
        this.fileDescriptorLoader = fileDescriptorLoader;
    }

    @Override
    public DataFetcher<ad.joyplus.com.myapplication.AppUtil.glide.load.model.ImageVideoWrapper> getResourceFetcher(A model, int width, int height) {
        DataFetcher<InputStream> streamFetcher = null;
        if (streamLoader != null) {
            streamFetcher = streamLoader.getResourceFetcher(model, width, height);
        }
        DataFetcher<ParcelFileDescriptor> fileDescriptorFetcher = null;
        if (fileDescriptorLoader != null) {
            fileDescriptorFetcher = fileDescriptorLoader.getResourceFetcher(model, width, height);
        }

        if (streamFetcher != null || fileDescriptorFetcher != null) {
            return new ImageVideoFetcher(streamFetcher, fileDescriptorFetcher);
        } else {
            return null;
        }
    }

    static class ImageVideoFetcher implements DataFetcher<ad.joyplus.com.myapplication.AppUtil.glide.load.model.ImageVideoWrapper> {
        private final DataFetcher<InputStream> streamFetcher;
        private final DataFetcher<ParcelFileDescriptor> fileDescriptorFetcher;

        public ImageVideoFetcher(DataFetcher<InputStream> streamFetcher,
                DataFetcher<ParcelFileDescriptor> fileDescriptorFetcher) {
            this.streamFetcher = streamFetcher;
            this.fileDescriptorFetcher = fileDescriptorFetcher;
        }

        @SuppressWarnings("resource")
        // @see ModelLoader.loadData
        @Override
        public ad.joyplus.com.myapplication.AppUtil.glide.load.model.ImageVideoWrapper loadData(Priority priority) throws Exception {
            InputStream is = null;
            if (streamFetcher != null) {
                try {
                    is = streamFetcher.loadData(priority);
                } catch (Exception e) {
                    if (Log.isLoggable(TAG, Log.VERBOSE)) {
                        Log.v(TAG, "Exception fetching input stream, trying ParcelFileDescriptor", e);
                    }
                    if (fileDescriptorFetcher == null) {
                        throw e;
                    }
                }
            }
            ParcelFileDescriptor fileDescriptor = null;
            if (fileDescriptorFetcher != null) {
                try {
                    fileDescriptor = fileDescriptorFetcher.loadData(priority);
                } catch (Exception e) {
                    if (Log.isLoggable(TAG, Log.VERBOSE)) {
                        Log.v(TAG, "Exception fetching ParcelFileDescriptor", e);
                    }
                    if (is == null) {
                        throw e;
                    }
                }
            }
            return new ImageVideoWrapper(is, fileDescriptor);
        }

        @Override
        public void cleanup() {
            //TODO: what if this throws?
            if (streamFetcher != null) {
                streamFetcher.cleanup();
            }
            if (fileDescriptorFetcher != null) {
                fileDescriptorFetcher.cleanup();
            }
        }

        @Override
        public String getId() {
            // Both the stream fetcher and the file descriptor fetcher should return the same id.
            if (streamFetcher != null) {
                return streamFetcher.getId();
            } else {
                return fileDescriptorFetcher.getId();
            }
        }

        @Override
        public void cancel() {
            if (streamFetcher != null) {
                streamFetcher.cancel();
            }
            if (fileDescriptorFetcher != null) {
                fileDescriptorFetcher.cancel();
            }
        }
    }
}
