package ad.joyplus.com.myapplication.AppUtil.glide.manager;

import ad.joyplus.com.myapplication.AppUtil.glide.manager.*;

/**
 * A no-op {@link ad.joyplus.com.myapplication.AppUtil.glide.manager.ConnectivityMonitor}.
 */
class NullConnectivityMonitor implements ad.joyplus.com.myapplication.AppUtil.glide.manager.ConnectivityMonitor {

    @Override
    public void onStart() {
        // Do nothing.
    }

    @Override
    public void onStop() {
        // Do nothing.
    }

    @Override
    public void onDestroy() {
        // Do nothing.
    }
}
