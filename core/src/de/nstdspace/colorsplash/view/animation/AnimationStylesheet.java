package de.nstdspace.colorsplash.view.animation;

import com.badlogic.gdx.scenes.scene2d.Action;

import de.nstdspace.colorsplash.view.GameField;

public interface AnimationStylesheet {

    Action provideGameFieldDismissAnimation(GameField field);
}
