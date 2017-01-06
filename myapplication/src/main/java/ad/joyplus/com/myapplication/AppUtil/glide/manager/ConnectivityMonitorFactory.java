package ad.joyplus.com.myapplication.AppUtil.glide.manager;

import android.content.Context;
import android.content.pm.PackageManager;

import ad.joyplus.com.myapplication.AppUtil.glide.manager.*;
import ad.joyplus.com.myapplication.AppUtil.glide.manager.NullConnectivityMonitor;

/**
 * A factory class that produces a functional {@link ad.joyplus.com.myapplication.AppUtil.glide.manager.ConnectivityMonitor} if the application
 * has the {@code android.permission.ACCESS_NETWORK_STATE} permission and a no-op non functional
 * {@link ad.joyplus.com.myapplication.AppUtil.glide.manager.ConnectivityMonitor} if the app does not have the required permission.
 */
public class ConnectivityMonitorFactory {
    public ad.joyplus.com.myapplication.AppUtil.glide.manager.ConnectivityMonitor build(Context context, ad.joyplus.com.myapplication.AppUtil.glide.manager.ConnectivityMonitor.ConnectivityListener listener) {
        final int res = context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE");
        final boolean hasPermission = res == PackageManager.PERMISSION_GRANTED;
        if (hasPermission) {
            return new DefaultConnectivityMonitor(context, listener);
        } else {
            return new ad.joyplus.com.myapplication.AppUtil.glide.manager.NullConnectivityMonitor();
        }
    }
}
