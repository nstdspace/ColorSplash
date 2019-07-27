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

    public GuiViewContext(Stylesheet stylesheet){
        this.stylesheet = stylesheet;
        guiBackground = ResourceTools.createOneColoredTexture(new Color(0, 0, 0, 0.35f));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.setColor(Color.WHITE);
        batch.draw(stylesheet.getBackgroundTexture(), 0, 0);
        batch.draw(guiBackground, 0, 0, ColorSplashGame.VIEWPORT_WIDTH, 100);
        batch.draw(guiBackground, 0, ColorSplashGame.VIEWPORT_HEIGHT - 100, ColorSplashGame.VIEWPORT_WIDTH, 100);
    }
}
