package ad.joyplus.com.myapplication.AppUtil.glide.request.animation;

import ad.joyplus.com.myapplication.AppUtil.glide.request.animation.*;
import ad.joyplus.com.myapplication.AppUtil.glide.request.animation.GlideAnimation;
import ad.joyplus.com.myapplication.AppUtil.glide.request.animation.NoAnimation;
import ad.joyplus.com.myapplication.AppUtil.glide.request.animation.ViewPropertyAnimation;

/**
 * A {@link ad.joyplus.com.myapplication.AppUtil.glide.request.animation.GlideAnimationFactory} that produces ViewPropertyAnimations.
 *
 * @param <R> The type of the resource displayed in the view that is animated
 */
public class ViewPropertyAnimationFactory<R> implements ad.joyplus.com.myapplication.AppUtil.glide.request.animation.GlideAnimationFactory<R> {
    private final ad.joyplus.com.myapplication.AppUtil.glide.request.animation.ViewPropertyAnimation.Animator animator;
    private ad.joyplus.com.myapplication.AppUtil.glide.request.animation.ViewPropertyAnimation<R> animation;

    public ViewPropertyAnimationFactory(ad.joyplus.com.myapplication.AppUtil.glide.request.animation.ViewPropertyAnimation.Animator animator) {
        this.animator = animator;
    }

    /**
     * Returns a new {@link ad.joyplus.com.myapplication.AppUtil.glide.request.animation.GlideAnimation} for the given arguments. If
     * isMemoryCache is {@code true} or isFirstImage is {@code false}, returns a
     * {@link ad.joyplus.com.myapplication.AppUtil.glide.request.animation.NoAnimation} and otherwise returns a new
     * {@link ad.joyplus.com.myapplication.AppUtil.glide.request.animation.ViewPropertyAnimation} for the
     * {@link ad.joyplus.com.myapplication.AppUtil.glide.request.animation.ViewPropertyAnimation.Animator} provided in the constructor.
     */
    @Override
    public GlideAnimation<R> build(boolean isFromMemoryCache, boolean isFirstResource) {
        if (isFromMemoryCache || !isFirstResource) {
            return NoAnimation.get();
        }
        if (animation == null) {
            animation = new ViewPropertyAnimation<R>(animator);
        }

        return animation;
    }
}
