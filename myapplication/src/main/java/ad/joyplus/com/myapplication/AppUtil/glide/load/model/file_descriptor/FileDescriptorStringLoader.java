package ad.joyplus.com.myapplication.AppUtil.glide.load.model.file_descriptor;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;

import ad.joyplus.com.myapplication.AppUtil.glide.Glide;
import ad.joyplus.com.myapplication.AppUtil.glide.load.model.GenericLoaderFactory;
import ad.joyplus.com.myapplication.AppUtil.glide.load.model.ModelLoader;
import ad.joyplus.com.myapplication.AppUtil.glide.load.model.ModelLoaderFactory;
import ad.joyplus.com.myapplication.AppUtil.glide.load.model.StringLoader;
import ad.joyplus.com.myapplication.AppUtil.glide.load.model.file_descriptor.*;

/**
 * A {@link ModelLoader} For translating {@link String} models, such as file paths, into {@link ParcelFileDescriptor}
 * data.
 */
public class FileDescriptorStringLoader extends StringLoader<ParcelFileDescriptor>
        implements ad.joyplus.com.myapplication.AppUtil.glide.load.model.file_descriptor.FileDescriptorModelLoader<String> {

    /**
     * The default factory for {@link ad.joyplus.com.myapplication.AppUtil.glide.load.model.file_descriptor.FileDescriptorStringLoader}s.
     */
    public static class Factory implements ModelLoaderFactory<String, ParcelFileDescriptor> {
        @Override
        public ModelLoader<String, ParcelFileDescriptor> build(Context context, GenericLoaderFactory factories) {
            return new FileDescriptorStringLoader(factories.buildModelLoader(Uri.class, ParcelFileDescriptor.class));
        }

        @Override
        public void teardown() {
            // Do nothing.
        }
    }

    public FileDescriptorStringLoader(Context context) {
        this(Glide.buildFileDescriptorModelLoader(Uri.class, context));
    }

    public FileDescriptorStringLoader(ModelLoader<Uri, ParcelFileDescriptor> uriLoader) {
        super(uriLoader);
    }
}
