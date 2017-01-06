package ad.joyplus.com.myapplication.AppUtil.glide.request.target;

import android.graphics.drawable.Drawable;

import ad.joyplus.com.myapplication.AppUtil.glide.request.Request;
import ad.joyplus.com.myapplication.AppUtil.glide.request.target.*;

/**
 * A base {@link ad.joyplus.com.myapplication.AppUtil.glide.request.target.Target} for loading {@link ad.joyplus.com.myapplication.AppUtil.glide.load.engine.Resource}s that provides basic or empty
 * implementations for most methods.
 *
 * <p>
 *     For maximum efficiency, clear this target when you have finished using or displaying the
 *     {@link ad.joyplus.com.myapplication.AppUtil.glide.load.engine.Resource} loaded into it using
 *     {@link ad.joyplus.com.myapplication.AppUtil.glide.Glide#clear(ad.joyplus.com.myapplication.AppUtil.glide.request.target.Target)}.
 * </p>
 *
 * <p>
 *     For loading {@link ad.joyplus.com.myapplication.AppUtil.glide.load.engine.Resource}s into {@link android.view.View}s,
 *     {@link ad.joyplus.com.myapplication.AppUtil.glide.request.target.ViewTarget} or {@link ad.joyplus.com.myapplication.AppUtil.glide.request.target.ImageViewTarget}
 *     are preferable.
 * </p>
 *
 * @param <Z> The type of resource that will be received by this target.
 */
public abstract class BaseTarget<Z> implements ad.joyplus.com.myapplication.AppUtil.glide.request.target.Target<Z> {

    private Request request;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRequest(Request request) {
        this.request = request;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Request getRequest() {
        return request;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onLoadCleared(Drawable placeholder) {
        // Do nothing.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onLoadStarted(Drawable placeholder) {
        // Do nothing.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onLoadFailed(Exception e, Drawable errorDrawable) {
        // Do nothing.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onStart() {
        // Do nothing.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onStop() {
        // Do nothing.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDestroy() {
        // Do nothing.
    }
}
