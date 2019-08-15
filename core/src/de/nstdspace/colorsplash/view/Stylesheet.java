package de.nstdspace.colorsplash.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;

import de.nstdspace.colorsplash.view.animation.AnimationStylesheet;

public interface Stylesheet extends Disposable {

    TextureRegionDrawable getColorBoxDrawable();
    TextureRegionDrawable getShowTargetButtonDrawable();
    Texture getBackgroundTexture();

    AnimationStylesheet getAnimationStylesheet();
}