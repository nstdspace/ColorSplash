package de.nstdspace.colorsplash.view.animation;

import com.badlogic.gdx.scenes.scene2d.Action;

public interface AnimationStylesheet<I> {

    Action provideAnimation(I id, Object... data);
}
