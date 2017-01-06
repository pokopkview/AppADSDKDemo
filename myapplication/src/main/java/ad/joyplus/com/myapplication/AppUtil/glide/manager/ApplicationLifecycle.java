package ad.joyplus.com.myapplication.AppUtil.glide.manager;

import ad.joyplus.com.myapplication.AppUtil.glide.manager.*;
import ad.joyplus.com.myapplication.AppUtil.glide.manager.LifecycleListener;

/**
 * A {@link ad.joyplus.com.myapplication.AppUtil.glide.manager.Lifecycle} implementation for tracking and notifying listeners of
 * {@link android.app.Application} lifecycle events.
 *
 * <p>
 *     Since there are essentially no {@link android.app.Application} lifecycle events, this class simply defaults to
 *     notifying new listeners that they are started.
 * </p>
 */
class ApplicationLifecycle implements ad.joyplus.com.myapplication.AppUtil.glide.manager.Lifecycle {
    @Override
    public void addListener(LifecycleListener listener) {
        listener.onStart();
    }
}
