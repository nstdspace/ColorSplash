package de.nstdspace.colorsplash.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;

/**
 * offers style data for different usages.
 * TODO: split into different things for different use cases
 */
public class DefaultStylesheet implements Stylesheet {

    private static Texture backgroundTexture;
    private static TextureRegionDrawable colorBoxTextureRegionDrawable;

<<<<<<< HEAD
    static {
        backgroundTexture = ResourceTools.loadTexture("background2.png");
=======
    public DefaultStylesheet(){
        createTextures();
    }

    private void createTextures(){
        backgroundTexture = ResourceTools.loadTexture("bgtest.png");
        backgroundTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
>>>>>>> b39d66169e89d0dba90371a913f65f71f102220c
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

<<<<<<< HEAD
    public static void dispose() {
        backgroundTexture.dispose();
        colorBoxTextureRegionDrawable.getRegion().getTexture().dispose();
=======
    @Override
    public void dispose() {
        backgroundTexture.dispose();
>>>>>>> b39d66169e89d0dba90371a913f65f71f102220c
    }
}