package de.nstdspace.colorsplash.view.animation;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import de.nstdspace.colorsplash.view.AnimationTools;
import de.nstdspace.colorsplash.view.GameField;

public class DefaultAnimationStylesheet implements AnimationStylesheet {

    @Override
    public Action provideGameFieldDismissAnimation(GameField field) {
        return Actions.parallel(
                AnimationTools.yFlipAction(field, 2.0f),
                Actions.alpha(0, 2.0f, Interpolation.linear)
        );
    }
}