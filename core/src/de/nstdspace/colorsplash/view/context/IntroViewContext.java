package de.nstdspace.colorsplash.view.context;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import de.nstdspace.colorsplash.ColorSplashGame;

public class IntroViewContext extends ViewContext {

    private String introText = "~ ColorSplash ~";
    private BitmapFont font;

    //TODO: maybe bit bad
    private Actor actor;

    public IntroViewContext(BitmapFont font){
        onCreate();
        this.font = font;
        GlyphLayout layout = new GlyphLayout();
        layout.setText(font, introText);
        float moveX = layout.width + (ColorSplashGame.VIEWPORT_WIDTH - layout.width) / 2;
        actor = new Actor();
        actor.setPosition(-layout.width, ColorSplashGame.VIEWPORT_HEIGHT / 2 - layout.height / 2);
        Action action =
                Actions.sequence(
                    Actions.moveBy(moveX, 0, 1.5f, Interpolation.pow2),
                    Actions.moveBy(moveX, 0, 1.5f, Interpolation.pow2)
                );
        actor.addAction(action);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        actor.act(Gdx.graphics.getDeltaTime());
        font.draw(batch, introText, actor.getX(), actor.getY());
        if(!actor.hasActions()) onDispose();
    }
}
