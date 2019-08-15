package de.nstdspace.colorsplash.view.context;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.nstdspace.colorsplash.ColorSplashGame;
import de.nstdspace.colorsplash.view.ResourceTools;
import de.nstdspace.colorsplash.view.Stylesheet;

/**
 * maybe give the Gui an own Stage?
 * many pros and cons about here.. gonna think about it.
 */
public class GuiViewContext extends ViewContext {

    private Stylesheet stylesheet;
    //TODO: move to stylesheet
    private Texture guiBackground;
    private TextureRegion backgroundTextureRegion;

    private static float RELATIVE_BUTTON_BAR_HEIGHT = 0.1f;
    private float buttonBarHeight;

    public enum GuiMode {
        NONE, INGAME, LEVEL_SELECT
    }

    public GuiMode currentMode = GuiMode.NONE;

    private HashMap<GuiMode, List<Actor>> buttonMap;


    public GuiViewContext(Stylesheet stylesheet){
        this.stylesheet = stylesheet;
        guiBackground = ResourceTools.createOneColoredTexture(new Color(0, 0, 0, 0.35f));
        createButtonMap();
        createBackground();
        buttonBarHeight = ColorSplashGame.VIEWPORT_HEIGHT * RELATIVE_BUTTON_BAR_HEIGHT;
    }

    private void createButtonMap(){
        buttonMap = new HashMap<>();
        for(GuiMode mode : GuiMode.values()){
            buttonMap.put(mode, new ArrayList<>());
        }
    }

    public void switchMode(GuiMode mode){
        for(Actor actor : buttonMap.get(currentMode)){
            removeActor(actor);
        }
        for(Actor actor : buttonMap.get(mode)){
            addActor(actor);
        }
        currentMode = mode;
    }

    public void registerButton(GuiMode mode, EventListener listener){
        Actor button = createButton(listener);
        buttonMap.get(mode).add(button);
    }

    private Actor createButton(EventListener listener){
        Image button = new Image();
        button.setDrawable(stylesheet.getShowTargetButtonDrawable());
        float buttonSize = 0.7f * buttonBarHeight;
        button.setSize(buttonSize, buttonSize);
        //TODO: fix position
        button.setPosition(0.15f * buttonBarHeight, 0.15f * buttonBarHeight);
        button.addListener(listener);
        return button;
    }

    private void createBackground(){
        // stretch small background texture to screen size
        TextureData data = stylesheet.getBackgroundTexture().getTextureData();
        if(!data.isPrepared()){
            data.prepare();
        }
        Pixmap p0 = data.consumePixmap();
        Pixmap p1 = new Pixmap(50, 50, Pixmap.Format.RGBA8888);
        p1.drawPixmap(p0, 0, 0, p0.getWidth(), p0.getHeight(), 0, 0, p1.getWidth(), p1.getHeight());
        Texture tex = new Texture(p1);
        tex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        backgroundTextureRegion = new TextureRegion(tex);
        backgroundTextureRegion.setRegion(0, 0, ColorSplashGame.VIEWPORT_WIDTH, ColorSplashGame.VIEWPORT_HEIGHT);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(getColor());
        batch.draw(backgroundTextureRegion, 0, 0);

        batch.setColor(Color.WHITE);
        batch.draw(guiBackground, 0, 0, ColorSplashGame.VIEWPORT_WIDTH, buttonBarHeight);
        batch.draw(guiBackground, 0, ColorSplashGame.VIEWPORT_HEIGHT - buttonBarHeight, ColorSplashGame.VIEWPORT_WIDTH, buttonBarHeight);

        super.draw(batch, parentAlpha);
    }
}
