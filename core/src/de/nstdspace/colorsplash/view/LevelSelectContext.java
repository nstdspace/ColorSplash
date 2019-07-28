package de.nstdspace.colorsplash.view;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

import de.nstdspace.colorsplash.view.subftrs.ViewContext;

public class LevelSelectContext extends ViewContext {

    public LevelSelectContext(BitmapFont font){
        TextActor a = new TextActor("level 1", font);
        a.setPosition(100, 100);
        addActor(a);
    }
}
