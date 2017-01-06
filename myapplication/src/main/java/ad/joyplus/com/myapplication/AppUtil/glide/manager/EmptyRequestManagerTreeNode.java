package ad.joyplus.com.myapplication.AppUtil.glide.manager;

import ad.joyplus.com.myapplication.AppUtil.glide.RequestManager;
import ad.joyplus.com.myapplication.AppUtil.glide.manager.*;

import java.util.Collections;
import java.util.Set;

/**
 * A {@link ad.joyplus.com.myapplication.AppUtil.glide.manager.RequestManagerTreeNode} that returns no relatives.
 */
final class EmptyRequestManagerTreeNode implements ad.joyplus.com.myapplication.AppUtil.glide.manager.RequestManagerTreeNode {
    @Override
    public Set<RequestManager> getDescendants() {
        return Collections.emptySet();
    }
}
