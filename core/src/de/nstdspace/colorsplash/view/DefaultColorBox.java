package de.nstdspace.colorsplash.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class DefaultColorBox extends Image {

    public int fieldPositionX;
    public int fieldPositionY;
    public Color gameColor;

    public DefaultColorBox(int fieldPositionX, int fieldPositionY, TextureRegionDrawable texture){
        this.fieldPositionX = fieldPositionX;
        this.fieldPositionY = fieldPositionY;
        setDrawable(texture);
    }

    @Override
    public void setColor(Color color) {
//        super.setColor(color);

        //only test
        addAction(Actions.color(color, 0.3f));
        gameColor = color;
    }

    public Color getGameColor(){
        return gameColor;
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        Color oldColor = batch.getColor();
        batch.setColor(getColor());
        super.draw(batch, parentAlpha);
        batch.setColor(oldColor);
    }
}