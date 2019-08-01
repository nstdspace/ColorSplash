package de.nstdspace.colorsplash.view.animation;

import com.badlogic.gdx.scenes.scene2d.Action;
import static de.nstdspace.colorsplash.view.animation.DefaultAnimationStylesheet.AnimationID;
import static de.nstdspace.colorsplash.view.animation.DefaultAnimationStylesheet.AnimationID.*;

public class DefaultAnimationStylesheet implements AnimationStylesheet<AnimationID> {

    enum AnimationID {
        DISMISS_GAMEFIELD
    }

    @Override
    public Action provideAnimation(AnimationID id, Object... data) {
        if(id == DISMISS_GAMEFIELD){
            
        }
        return null;
    }
}