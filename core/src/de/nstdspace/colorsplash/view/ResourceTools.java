package de.nstdspace.colorsplash.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.HashMap;

public class ResourceTools {

    public static TextureRegionDrawable textureToDrawable(Texture texture){
        return new TextureRegionDrawable(new TextureRegion(texture));
    }

    public static Texture createOneColoredTexture(Color c){
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(c);
        pixmap.fill();
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }

    public static Texture loadTexture(String name){
        return new Texture(Gdx.files.internal(name));
    }
}