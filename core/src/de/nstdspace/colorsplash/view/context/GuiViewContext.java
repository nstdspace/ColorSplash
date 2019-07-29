package de.nstdspace.colorsplash.view.context;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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

    public GuiViewContext(Stylesheet stylesheet){
        this.stylesheet = stylesheet;
        guiBackground = ResourceTools.createOneColoredTexture(new Color(0, 0, 0, 0.35f));

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

        //backgroundTextureRegion = new TextureRegion(stylesheet.getBackgroundTexture());
        //backgroundTextureRegion.setRegion(0, 0, ColorSplashGame.VIEWPORT_WIDTH, ColorSplashGame.VIEWPORT_HEIGHT);

        buttonBarHeight = ColorSplashGame.VIEWPORT_HEIGHT * RELATIVE_BUTTON_BAR_HEIGHT;

        //TODO: move this to stylesheet
        backgroundTint = new Color(220 / 255.0f, 83 / 255.0f, 83 / 255.0f, 1.0f);
    }

    private Color backgroundTint;

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.setColor(backgroundTint);
        batch.draw(backgroundTextureRegion, 0, 0);
        batch.draw(guiBackground, 0, 0, ColorSplashGame.VIEWPORT_WIDTH, buttonBarHeight);
        batch.draw(guiBackground, 0, ColorSplashGame.VIEWPORT_HEIGHT - buttonBarHeight, ColorSplashGame.VIEWPORT_WIDTH, buttonBarHeight);
    }
}
