package ad.joyplus.com.myapplication.AppUtil.glide.load;

import ad.joyplus.com.myapplication.AppUtil.glide.load.*;
import ad.joyplus.com.myapplication.AppUtil.glide.load.engine.Resource;

import java.util.Arrays;
import java.util.Collection;

/**
 * A transformation that applies one or more transformations in iteration order to a resource.
 *
 * @param <T> The type of {@link Resource} that will be transformed.
 */
public class MultiTransformation<T> implements ad.joyplus.com.myapplication.AppUtil.glide.load.Transformation<T> {
    private final Collection<? extends ad.joyplus.com.myapplication.AppUtil.glide.load.Transformation<T>> transformations;
    private String id;

    @SafeVarargs
    public MultiTransformation(ad.joyplus.com.myapplication.AppUtil.glide.load.Transformation<T>... transformations) {
        if (transformations.length < 1) {
            throw new IllegalArgumentException("MultiTransformation must contain at least one Transformation");
        }
        this.transformations = Arrays.asList(transformations);
    }

    public MultiTransformation(Collection<? extends ad.joyplus.com.myapplication.AppUtil.glide.load.Transformation<T>> transformationList) {
        if (transformationList.size() < 1) {
            throw new IllegalArgumentException("MultiTransformation must contain at least one Transformation");
        }
        this.transformations = transformationList;
    }

    @Override
    public Resource<T> transform(Resource<T> resource, int outWidth, int outHeight) {
        Resource<T> previous = resource;

        for (ad.joyplus.com.myapplication.AppUtil.glide.load.Transformation<T> transformation : transformations) {
            Resource<T> transformed = transformation.transform(previous, outWidth, outHeight);
            if (previous != null && !previous.equals(resource) && !previous.equals(transformed)) {
                previous.recycle();
            }
            previous = transformed;
        }
        return previous;
    }

    @Override
    public String getId() {
        if (id == null) {
            StringBuilder sb = new StringBuilder();
            for (ad.joyplus.com.myapplication.AppUtil.glide.load.Transformation<T> transformation : transformations) {
                sb.append(transformation.getId());
            }
            id = sb.toString();
        }
        return id;
    }
}
