package de.nstdspace.colorsplash.view;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class TextActor extends Actor {

    private BitmapFont font;
    private String text;

    public TextActor(String text, BitmapFont font){
        this.text = text;
        this.font = font;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        font.draw(batch, text, 0, 0);
    }
}
