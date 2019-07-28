package de.nstdspace.colorsplash.view.context;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import de.nstdspace.colorsplash.ColorSplashGame;
import de.nstdspace.colorsplash.view.ResourceTools;
import de.nstdspace.colorsplash.view.TextActor;

public class LevelSelectContext extends ViewContext {

    public LevelSelectContext(BitmapFont font){
        TextActor levelPackText = new TextActor("level Pack 1", font);
        levelPackText.setPosition(0.5f * (-levelPackText.getWidth() + ColorSplashGame.VIEWPORT_WIDTH), 0.2f * ColorSplashGame.VIEWPORT_HEIGHT);
        addActor(levelPackText);

        Group h = new Group();

        Texture t = ResourceTools.createOneColoredTexture(new Color(0.0f, 0.0f, 0.0f, 0.6f));
        Image i = new Image();
        i.setDrawable(new TextureRegionDrawable(t));
        i.setSize(100, 100);
        i.addListener((event) -> {
           if(((InputEvent) event).getType() == InputEvent.Type.touchDown){
               fireEvent((l) -> ((LevelSelectListener) l).levelSelected(0));
           }
           return true;
        });

        h.addActor(i);

        TextActor a = new TextActor("0", font);
        h.addActor(a);

        addActor(h);
        h.setPosition(300, 300);

        Gdx.app.log("[info]", "" + h.getWidth() + ", " + h.getHeight());

    }

    public void addLevelSelectListener(LevelSelectListener listener){
        addViewContextListener(listener);
    }
}
