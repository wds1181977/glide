package com.bumptech.glide.request.animation;

import android.view.View;
import android.widget.ImageView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static com.bumptech.glide.request.animation.GlideAnimation.ViewAdapter;
import static junit.framework.Assert.assertFalse;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class ViewPropertyAnimationTest {
    private ViewPropertyAnimation.Animator animator;
    private ViewPropertyAnimation animation;

    @Before
    public void setUp() {
        animator = mock(ViewPropertyAnimation.Animator.class);
        animation = new ViewPropertyAnimation(animator);
    }

    @Test
    public void testAlwaysReturnsFalse() {
        assertFalse(animation.animate(new Object(), mock(ViewAdapter.class)));
    }

    @Test
    public void testCallsAnimatorWithGivenView() {
        ImageView view = new ImageView(Robolectric.application);
        ViewAdapter adapter = mock(ViewAdapter.class);
        when(adapter.getView()).thenReturn(view);
        animation.animate(new Object(), adapter);

        verify(animator).animate(eq(view));
    }

    @Test
    public void testDoesNotCallAnimatorIfGivenAdapterWithNullView() {
        ViewAdapter adapter = mock(ViewAdapter.class);
        animation.animate(new Object(), adapter);

        verify(animator, never()).animate(any(View.class));
    }
}
