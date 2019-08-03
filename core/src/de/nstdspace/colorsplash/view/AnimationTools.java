package de.nstdspace.colorsplash.view;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class AnimationTools {

    /**
     * cardflip animations need the actor to have
     * the origin centered. otherwise this is bullshit.
     */

    public static Action xFlipAction(Actor actor, float duration){
        return Actions.sequence(
            Actions.scaleTo(1, 0, 0.5f * duration, Interpolation.pow3In),
            Actions.scaleTo(1, -1, 0.5f * duration, Interpolation.pow3Out)
        );
    }

    public static Action yFlipAction(Actor actor, float duration){
        return Actions.sequence(
            Actions.scaleTo(0, 1, 0.5f * duration, Interpolation.pow3In),
            Actions.scaleTo(-1, 1, 0.5f * duration, Interpolation.pow3Out)
        );
    }
}
