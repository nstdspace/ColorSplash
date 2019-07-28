package de.nstdspace.colorsplash.view.subftrs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

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

    private static float RELATIVE_BUTTON_BAR_HEIGHT = 0.1f;
    private float buttonBarHeight;

    public GuiViewContext(Stylesheet stylesheet){
        this.stylesheet = stylesheet;
        guiBackground = ResourceTools.createOneColoredTexture(new Color(0, 0, 0, 0.35f));
        buttonBarHeight = ColorSplashGame.VIEWPORT_HEIGHT * RELATIVE_BUTTON_BAR_HEIGHT;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.setColor(Color.WHITE);
        batch.draw(stylesheet.getBackgroundTexture(), 0, 0, ColorSplashGame.VIEWPORT_WIDTH, ColorSplashGame.VIEWPORT_HEIGHT);
        batch.draw(guiBackground, 0, 0, ColorSplashGame.VIEWPORT_WIDTH, buttonBarHeight);
        batch.draw(guiBackground, 0, ColorSplashGame.VIEWPORT_HEIGHT - buttonBarHeight, ColorSplashGame.VIEWPORT_WIDTH, buttonBarHeight);
    }
}
