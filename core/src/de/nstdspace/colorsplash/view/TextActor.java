package de.nstdspace.colorsplash.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * using this because to lazy to set up a skin.
 *
 * WARNING: none of the Actor methods will work here but
 * invoking unexpected results.
 */
public class TextActor extends Actor {

    private BitmapFont font;
    private String text;

    private Texture DEBUG_BG;

    public TextActor(String text, BitmapFont font){
        this.text = text;
        this.font = font;
        GlyphLayout layout = new GlyphLayout(font, text);
        setSize(layout.width, layout.height);

        DEBUG_BG = ResourceTools.createOneColoredTexture(Color.PINK);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        Color color = batch.getColor();
        batch.draw(DEBUG_BG, getX(), getY(), getWidth(), getHeight());
        font.draw(batch, text, getX(), getY());
    }
}
