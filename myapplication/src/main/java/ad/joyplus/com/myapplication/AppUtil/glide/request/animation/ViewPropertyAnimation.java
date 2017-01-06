package ad.joyplus.com.myapplication.AppUtil.glide.request.animation;

import android.view.View;

import ad.joyplus.com.myapplication.AppUtil.glide.request.animation.*;

/**
 * A {@link ad.joyplus.com.myapplication.AppUtil.glide.request.animation.GlideAnimation GlideAnimation} that accepts an interface
 * that can apply an animation like a {@link android.view.ViewPropertyAnimator}
 * or a {@link android.animation.ObjectAnimator} to an {@link View}.
 *
 * @param <R> The type of the resource displayed in the view that is animated
 */
public class ViewPropertyAnimation<R> implements ad.joyplus.com.myapplication.AppUtil.glide.request.animation.GlideAnimation<R> {

    private final Animator animator;

    /**
     * Constructor for a view property animation that takes an
     * {@link ad.joyplus.com.myapplication.AppUtil.glide.request.animation.ViewPropertyAnimation.Animator} interface that can apply an animation
     * to a view.
     *
     * @param animator The animator to use.
     */
    public ViewPropertyAnimation(Animator animator) {
        this.animator = animator;
    }

    /**
     * Always applies the {@link ad.joyplus.com.myapplication.AppUtil.glide.request.animation.ViewPropertyAnimation.Animator} given in the
     * constructor to the given view and returns {@code false} because the animator cannot set the new resource on
     * the view.
     *
     * @param current {@inheritDoc}
     * @param adapter {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean animate(R current, ViewAdapter adapter) {
        final View view = adapter.getView();
        if (view != null) {
            animator.animate(adapter.getView());
        }
        return false;
    }

    /**
     * An interface that allows an animation to be applied on or started from an {@link View}.
     */
    public interface Animator {
        /**
         * Starts an animation on the given {@link View}.
         *
         * @param view The view to animate.
         */
        void animate(View view);
    }

}
