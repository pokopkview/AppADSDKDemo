package ad.joyplus.com.myapplication.AppUtil.glide.manager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v4.app.Fragment;

import ad.joyplus.com.myapplication.AppUtil.glide.RequestManager;
import ad.joyplus.com.myapplication.AppUtil.glide.manager.*;
import ad.joyplus.com.myapplication.AppUtil.glide.manager.RequestManagerRetriever;
import ad.joyplus.com.myapplication.AppUtil.glide.manager.RequestManagerTreeNode;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * A view-less {@link Fragment} used to safely store an
 * {@link RequestManager} that can be used to start, stop and manage Glide requests started for
 * targets within the fragment or activity this fragment is a child of.
 *
 * @see ad.joyplus.com.myapplication.AppUtil.glide.manager.RequestManagerFragment
 * @see ad.joyplus.com.myapplication.AppUtil.glide.manager.RequestManagerRetriever
 * @see RequestManager
 */
public class SupportRequestManagerFragment extends Fragment {
    private RequestManager requestManager;
    private final ad.joyplus.com.myapplication.AppUtil.glide.manager.ActivityFragmentLifecycle lifecycle;
    private final ad.joyplus.com.myapplication.AppUtil.glide.manager.RequestManagerTreeNode requestManagerTreeNode =
            new SupportFragmentRequestManagerTreeNode();
    private final HashSet<SupportRequestManagerFragment> childRequestManagerFragments =
        new HashSet<SupportRequestManagerFragment>();
    private SupportRequestManagerFragment rootRequestManagerFragment;

    public SupportRequestManagerFragment() {
        this(new ad.joyplus.com.myapplication.AppUtil.glide.manager.ActivityFragmentLifecycle());
    }

    // For testing only.
    @SuppressLint("ValidFragment")
    public SupportRequestManagerFragment(ad.joyplus.com.myapplication.AppUtil.glide.manager.ActivityFragmentLifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }

    /**
     * Sets the current {@link RequestManager}.
     *
     * @param requestManager The manager to set.
     */
    public void setRequestManager(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    ad.joyplus.com.myapplication.AppUtil.glide.manager.ActivityFragmentLifecycle getLifecycle() {
        return lifecycle;
    }

    /**
     * Returns the current {@link RequestManager} or null if none is set.
     */
    public RequestManager getRequestManager() {
        return requestManager;
    }

    /**
     * Returns the {@link ad.joyplus.com.myapplication.AppUtil.glide.manager.RequestManagerTreeNode} that provides tree traversal methods relative to the associated
     * {@link RequestManager}.
     */
    public ad.joyplus.com.myapplication.AppUtil.glide.manager.RequestManagerTreeNode getRequestManagerTreeNode() {
        return requestManagerTreeNode;
    }

    private void addChildRequestManagerFragment(SupportRequestManagerFragment child) {
        childRequestManagerFragments.add(child);
    }

    private void removeChildRequestManagerFragment(SupportRequestManagerFragment child) {
        childRequestManagerFragments.remove(child);
    }

    /**
     * Returns the set of fragments that this RequestManagerFragment's parent is a parent to. (i.e. our parent is
     * the fragment that we are annotating).
     */
    public Set<SupportRequestManagerFragment> getDescendantRequestManagerFragments() {
        if (rootRequestManagerFragment == null) {
            return Collections.emptySet();
        } else if (rootRequestManagerFragment == this) {
            return Collections.unmodifiableSet(childRequestManagerFragments);
        } else {
            HashSet<SupportRequestManagerFragment> descendants =
                new HashSet<SupportRequestManagerFragment>();
            for (SupportRequestManagerFragment fragment
                : rootRequestManagerFragment.getDescendantRequestManagerFragments()) {
                if (isDescendant(fragment.getParentFragment())) {
                    descendants.add(fragment);
                }
            }
            return Collections.unmodifiableSet(descendants);
        }
    }

    /**
     * Returns true if the fragment is a descendant of our parent.
     */
    private boolean isDescendant(Fragment fragment) {
        Fragment root = this.getParentFragment();
        while (fragment.getParentFragment() != null) {
            if (fragment.getParentFragment() == root) {
                return true;
            }
            fragment = fragment.getParentFragment();
        }
        return false;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        rootRequestManagerFragment = RequestManagerRetriever.get()
                .getSupportRequestManagerFragment(getActivity().getSupportFragmentManager());
        if (rootRequestManagerFragment != this) {
            rootRequestManagerFragment.addChildRequestManagerFragment(this);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (rootRequestManagerFragment != null) {
            rootRequestManagerFragment.removeChildRequestManagerFragment(this);
            rootRequestManagerFragment = null;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        lifecycle.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        lifecycle.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lifecycle.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        // If an activity is re-created, onLowMemory may be called before a manager is ever set.
        // See #329.
        if (requestManager != null) {
            requestManager.onLowMemory();
        }
    }

    private class SupportFragmentRequestManagerTreeNode implements RequestManagerTreeNode {
        @Override
        public Set<RequestManager> getDescendants() {
            Set<SupportRequestManagerFragment> descendantFragments = getDescendantRequestManagerFragments();
            HashSet<RequestManager> descendants = new HashSet<RequestManager>(descendantFragments.size());
            for (SupportRequestManagerFragment fragment : descendantFragments) {
                if (fragment.getRequestManager() != null) {
                    descendants.add(fragment.getRequestManager());
                }
            }
            return descendants;
        }
    }
}
