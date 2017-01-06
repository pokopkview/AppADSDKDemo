package ad.joyplus.com.myapplication.AppUtil.glide.load.model.file_descriptor;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import java.io.File;

import ad.joyplus.com.myapplication.AppUtil.glide.Glide;
import ad.joyplus.com.myapplication.AppUtil.glide.load.model.FileLoader;
import ad.joyplus.com.myapplication.AppUtil.glide.load.model.GenericLoaderFactory;
import ad.joyplus.com.myapplication.AppUtil.glide.load.model.ModelLoader;
import ad.joyplus.com.myapplication.AppUtil.glide.load.model.ModelLoaderFactory;

/**
 * A {@link ModelLoader} For translating {@link File} models into {@link ParcelFileDescriptor} data.
 */
public class FileDescriptorFileLoader extends FileLoader<ParcelFileDescriptor>
        implements FileDescriptorModelLoader<File> {

    /**
     * The default {@link ModelLoaderFactory} for
     * {@link ad.joyplus.com.myapplication.AppUtil.glide.load.model.file_descriptor.FileDescriptorFileLoader}s.
     */
    public static class Factory implements ModelLoaderFactory<File, ParcelFileDescriptor> {
        @Override
        public ModelLoader<File, ParcelFileDescriptor> build(Context context, GenericLoaderFactory factories) {
            return new FileDescriptorFileLoader(factories.buildModelLoader(Uri.class, ParcelFileDescriptor.class));
        }

        @Override
        public void teardown() {
            // Do nothing.
        }
    }

    public FileDescriptorFileLoader(Context context) {
        this(Glide.buildFileDescriptorModelLoader(Uri.class, context));
    }

    public FileDescriptorFileLoader(ModelLoader<Uri, ParcelFileDescriptor> uriLoader) {
        super(uriLoader);
    }
}
