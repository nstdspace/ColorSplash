package de.nstdspace.colorsplash.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * offers style data for different usages.
 * TODO: split into different things for different use cases
 */
public class DefaultStylesheet implements Stylesheet {

    private static Texture backgroundTexture;
    private static TextureRegionDrawable colorBoxTextureRegionDrawable;

    public DefaultStylesheet(){
        createTextures();
    }

    private void createTextures(){
        backgroundTexture = ResourceTools.loadTexture("background2.png");
        colorBoxTextureRegionDrawable = new TextureRegionDrawable(new TextureRegion(ResourceTools.loadTexture("colorbox.png")));
    }

    @Override
    public Texture getBackgroundTexture() {
        return backgroundTexture;
    }

    @Override
    public TextureRegionDrawable getColorBoxTexture() {
        return colorBoxTextureRegionDrawable;
    }
}