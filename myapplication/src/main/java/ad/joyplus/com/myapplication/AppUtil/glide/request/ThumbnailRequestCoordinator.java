package ad.joyplus.com.myapplication.AppUtil.glide.request;

import ad.joyplus.com.myapplication.AppUtil.glide.request.*;
import ad.joyplus.com.myapplication.AppUtil.glide.request.Request;

/**
 * A coordinator that coordinates two individual {@link ad.joyplus.com.myapplication.AppUtil.glide.request.Request}s that load a small thumbnail version of an image and
 * the full size version of the image at the same time.
 */
public class ThumbnailRequestCoordinator implements ad.joyplus.com.myapplication.AppUtil.glide.request.RequestCoordinator, ad.joyplus.com.myapplication.AppUtil.glide.request.Request {
    private ad.joyplus.com.myapplication.AppUtil.glide.request.Request full;
    private ad.joyplus.com.myapplication.AppUtil.glide.request.Request thumb;
    private ad.joyplus.com.myapplication.AppUtil.glide.request.RequestCoordinator coordinator;

    public ThumbnailRequestCoordinator() {
        this(null);
    }

    public ThumbnailRequestCoordinator(ad.joyplus.com.myapplication.AppUtil.glide.request.RequestCoordinator coordinator) {
        this.coordinator = coordinator;
    }

    public void setRequests(ad.joyplus.com.myapplication.AppUtil.glide.request.Request full, ad.joyplus.com.myapplication.AppUtil.glide.request.Request thumb) {
        this.full = full;
        this.thumb = thumb;
    }

    /**
     *
     * Returns true if the request is either the request loading the fullsize image or if the request loading the
     * full size image has not yet completed.
     *
     * @param request {@inheritDoc}
     */
    @Override
    public boolean canSetImage(ad.joyplus.com.myapplication.AppUtil.glide.request.Request request) {
        return parentCanSetImage() && (request.equals(full) || !full.isResourceSet());
    }

    private boolean parentCanSetImage() {
        return coordinator == null || coordinator.canSetImage(this);
    }

    /**
     * Returns true if the request is the request loading the fullsize image and if neither the full nor the thumbnail
     * image have completed sucessfully.
     *
     * @param request {@inheritDoc}.
     */
    @Override
    public boolean canNotifyStatusChanged(ad.joyplus.com.myapplication.AppUtil.glide.request.Request request) {
        return parentCanNotifyStatusChanged() && request.equals(full) && !isAnyResourceSet();
    }

    private boolean parentCanNotifyStatusChanged() {
        return coordinator == null || coordinator.canNotifyStatusChanged(this);
    }

    @Override
    public boolean isAnyResourceSet() {
        return parentIsAnyResourceSet() || isResourceSet();
    }

    @Override
    public void onRequestSuccess(Request request) {
        if (request.equals(thumb)) {
            return;
        }
        if (coordinator != null) {
            coordinator.onRequestSuccess(this);
        }
        // Clearing the thumb is not necessarily safe if the thumb is being displayed in the Target,
        // as a layer in a cross fade for example. The only way we know the thumb is not being
        // displayed and is therefore safe to clear is if the thumb request has not yet completed.
        if (!thumb.isComplete()) {
          thumb.clear();
        }
    }

    private boolean parentIsAnyResourceSet() {
        return coordinator != null && coordinator.isAnyResourceSet();
    }

    /**
     * Starts first the thumb request and then the full request.
     */
    @Override
    public void begin() {
        if (!thumb.isRunning()) {
            thumb.begin();
        }
        if (!full.isRunning()) {
            full.begin();
        }
    }

    @Override
    public void pause() {
        full.pause();
        thumb.pause();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        thumb.clear();
        full.clear();
    }

    @Override
    public boolean isPaused() {
        return full.isPaused();
    }

    /**
     * Returns true if the full request is still running.
     */
    @Override
    public boolean isRunning() {
        return full.isRunning();
    }

    /**
     * Returns true if the full request is complete.
     */
    @Override
    public boolean isComplete() {
        return full.isComplete() || thumb.isComplete();
    }

    @Override
    public boolean isResourceSet() {
        return full.isResourceSet() || thumb.isResourceSet();
    }

    @Override
    public boolean isCancelled() {
        return full.isCancelled();
    }

    /**
     * Returns true if the full request has failed.
     */
    @Override
    public boolean isFailed() {
        return full.isFailed();
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void recycle() {
        full.recycle();
        thumb.recycle();
    }
}
