package ad.joyplus.com.myapplication.AppUtil.glide.load.resource.file;

import ad.joyplus.com.myapplication.AppUtil.glide.load.ResourceDecoder;
import ad.joyplus.com.myapplication.AppUtil.glide.load.engine.Resource;
import ad.joyplus.com.myapplication.AppUtil.glide.load.resource.file.FileResource;

import java.io.File;

/**
 * A simple {@link ResourceDecoder} that creates resource for a given {@link File}.
 */
public class FileDecoder implements ResourceDecoder<File, File> {

    @Override
    public Resource<File> decode(File source, int width, int height) {
        return new FileResource(source);
    }

    @Override
    public String getId() {
        return "";
    }
}
