package ad.joyplus.com.myapplication.AppUtil.glide.request.target;

import ad.joyplus.com.myapplication.AppUtil.glide.Glide;
import ad.joyplus.com.myapplication.AppUtil.glide.request.animation.GlideAnimation;
import ad.joyplus.com.myapplication.AppUtil.glide.request.target.*;

/**
 * A one time use {@link ad.joyplus.com.myapplication.AppUtil.glide.request.target.Target} class that loads a resource into memory and then
 * clears itself.
 *
 * @param <Z> The type of resource that will be loaded into memory.
 */
public final class PreloadTarget<Z> extends ad.joyplus.com.myapplication.AppUtil.glide.request.target.SimpleTarget<Z> {

    /**
     * Returns a PreloadTarget.
     *
     * @param width The width in pixels of the desired resource.
     * @param height The height in pixels of the desired resource.
     * @param <Z> The type of the desired resource.
     */
    public static <Z> PreloadTarget<Z> obtain(int width, int height) {
        return new PreloadTarget<Z>(width, height);
    }

    private PreloadTarget(int width, int height) {
        super(width, height);
    }

    @Override
    public void onResourceReady(Z resource, GlideAnimation<? super Z> glideAnimation) {
        Glide.clear(this);
    }
}
