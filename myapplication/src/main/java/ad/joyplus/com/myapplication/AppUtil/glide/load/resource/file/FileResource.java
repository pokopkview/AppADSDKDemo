package ad.joyplus.com.myapplication.AppUtil.glide.load.resource.file;

import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.SimpleResource;

import java.io.File;

/**
 * A simple {@link ad.joyplus.com.myapplication.AppUtil.glide.load.engine.Resource} that wraps a {@link File}.
 */
public class FileResource extends SimpleResource<File> {
    public FileResource(File file) {
        super(file);
    }
}
