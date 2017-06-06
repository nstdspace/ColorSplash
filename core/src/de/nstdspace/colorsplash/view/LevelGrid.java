package de.nstdspace.colorsplash.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import de.nstdspace.colorsplash.ColorSplashGame;

public class LevelGrid extends Group {

    // gcd(ColorSplashGame.VIEWPORT_HEIGHT, ColorSplashGame.VIEWPORT_WIDTH) = SIZE + GAP_SIZE
    private static final int SIZE       = 70;
    private static final int GAP_SIZE   = 10;

    public LevelGrid() {
        int maxVertical     = (int)(ColorSplashGame.VIEWPORT_HEIGHT / (SIZE + GAP_SIZE));
        int maxHorizontal   = (int)(ColorSplashGame.VIEWPORT_WIDTH / (SIZE + GAP_SIZE));

        for (int iy = 0; iy < maxVertical; iy++) {
            for (int ix = 0; ix < maxHorizontal; ix++) {
                addActor(new GridItem(ix, iy, iy * maxVertical + ix + 1));
            }
        }
    }

    class GridItem extends Actor
    {
        private int level;

        public GridItem(int x, int y, int level) {
            setX((x + 0.5f) * GAP_SIZE + x * SIZE);
            setY((y + 0.5f) * GAP_SIZE + y * SIZE);
            this.level = level;

            addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    // setStage(level);
                    return true;
                }
            });
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            DefaultStylesheet sheet = new DefaultStylesheet();

            batch.draw(sheet.getColorBoxTexture().getRegion().getTexture(),
                    getX(), getY(), SIZE, SIZE);
        }

        public int getLevel() { return level; }
    }
}
