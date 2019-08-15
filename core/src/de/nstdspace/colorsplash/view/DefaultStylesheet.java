package de.nstdspace.colorsplash.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Queue;

import javax.annotation.Resource;

import de.nstdspace.colorsplash.view.animation.AnimationStylesheet;
import de.nstdspace.colorsplash.view.animation.DefaultAnimationStylesheet;

/**
 * offers style data for different usages.
 * TODO: split into different things for different use cases
 */
public class DefaultStylesheet implements Stylesheet {

    private static Texture backgroundTexture;
    private static TextureRegionDrawable colorBoxTextureRegionDrawable;
    private static AnimationStylesheet animationStylesheet;

    private static TextureRegionDrawable showTargetButtonDrawable;

    public DefaultStylesheet(){
        createTextures();
        animationStylesheet = new DefaultAnimationStylesheet();
    }

    private void createTextures(){
        backgroundTexture = ResourceTools.loadTexture("bgtest2.png");
        backgroundTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        colorBoxTextureRegionDrawable = new TextureRegionDrawable(new TextureRegion(ResourceTools.loadTexture("colorbox_light.png")));
        showTargetButtonDrawable = loadTextureIntoDrawable("show_target_button.png");
    }

    private TextureRegionDrawable loadTextureIntoDrawable(String name){
        return new TextureRegionDrawable(loadTextureIntoRegion(name));
    }

    private TextureRegion loadTextureIntoRegion(String name){
        return new TextureRegion(ResourceTools.loadTexture(name));
    }

    @Override
    public AnimationStylesheet getAnimationStylesheet() {
        return animationStylesheet;
    }

    @Override
    public Texture getBackgroundTexture() {
        return backgroundTexture;
    }

    @Override
    public TextureRegionDrawable getColorBoxDrawable() {
        return colorBoxTextureRegionDrawable;
    }

    @Override
    public TextureRegionDrawable getShowTargetButtonDrawable(){
        return showTargetButtonDrawable;
    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
    }
}