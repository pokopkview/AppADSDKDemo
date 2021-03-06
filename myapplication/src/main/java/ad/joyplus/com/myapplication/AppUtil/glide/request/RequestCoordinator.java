package ad.joyplus.com.myapplication.AppUtil.glide.request;

import ad.joyplus.com.myapplication.AppUtil.glide.request.*;

/**
 * An interface for coordinating multiple requests with the same {@link ad.joyplus.com.myapplication.AppUtil.glide.request.target.Target}.
 */
public interface RequestCoordinator {

    /**
     * Returns true if the {@link ad.joyplus.com.myapplication.AppUtil.glide.request.Request} can display a loaded bitmap.
     *
     * @param request The {@link ad.joyplus.com.myapplication.AppUtil.glide.request.Request} requesting permission to display a bitmap.
     */
    boolean canSetImage(ad.joyplus.com.myapplication.AppUtil.glide.request.Request request);

    /**
     * Returns true if the {@link ad.joyplus.com.myapplication.AppUtil.glide.request.Request} can display a placeholder.
     *
     * @param request The {@link ad.joyplus.com.myapplication.AppUtil.glide.request.Request} requesting permission to display a placeholder.
     */
    boolean canNotifyStatusChanged(ad.joyplus.com.myapplication.AppUtil.glide.request.Request request);

    /**
     * Returns true if any coordinated {@link ad.joyplus.com.myapplication.AppUtil.glide.request.Request} has successfully completed.
     *
     * @see ad.joyplus.com.myapplication.AppUtil.glide.request.Request#isComplete()
     */
    boolean isAnyResourceSet();

    /**
     * Must be called when a request coordinated by this object completes successfully.
     */
    void onRequestSuccess(ad.joyplus.com.myapplication.AppUtil.glide.request.Request request);
}
