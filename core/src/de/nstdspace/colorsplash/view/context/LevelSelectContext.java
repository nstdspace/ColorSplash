package de.nstdspace.colorsplash.view.context;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import de.nstdspace.colorsplash.ColorSplashGame;
import de.nstdspace.colorsplash.view.AnimationTools;
import de.nstdspace.colorsplash.view.TextActor;

public class LevelSelectContext extends ViewContext {

    private Texture levelAtlas;

    public LevelSelectContext(BitmapFont font){
        TextActor levelPackText = new TextActor("level Pack 1", font);
        levelPackText.setPosition(0.5f * (-levelPackText.getWidth() + ColorSplashGame.VIEWPORT_WIDTH), ColorSplashGame.VIEWPORT_HEIGHT - (0.2f * ColorSplashGame.VIEWPORT_HEIGHT));
        addActor(levelPackText);

        createLevelButtons();

        //Gdx.app.log("[info]", "" + h.getWidth() + ", " + h.getHeight());

    }

    private void createLevelButtons(){
        // TODO: this has to come from stylesheet or so
        levelAtlas = new Texture(Gdx.files.internal("level_num_atlas.png"));
        TextureRegion region0 = new TextureRegion(levelAtlas, 0, 0, 32, 32);
        TextureRegionDrawable dr0 = new TextureRegionDrawable(region0);
        // TODO: remove hardcoding?
        float buttonRows = 7;
        float buttonCols = 5;
        float groupWidth = 0.7f * ColorSplashGame.VIEWPORT_WIDTH;
        float buttonRelativeSize = (1 / (buttonCols + 1));
        float buttonSize = buttonRelativeSize * groupWidth;

        float buttonRelativeGapSize1 = (1 - buttonRelativeSize * buttonCols) / (buttonCols - 1);
        float buttonGapSize = buttonRelativeGapSize1 * groupWidth;

        float groupHeight = buttonSize * buttonRows + (buttonRows - 1) * (buttonGapSize);

        Group h = new Group();

//        Texture t = ResourceTools.createOneColoredTexture(Color.PINK);
//        Image test = new Image(t);
//        test.setSize(groupWidth, groupHeight);
//        h.addActor(test);


        Color buttonTint = new Color(249 / 255.0f, 224 / 255.0f, 14 / 255.0f, 1.0f);
        //TODO: implement pack selection
        int packId = 1;

        for(int i = 0; i < 35; i++){
            Image image = new Image();
            image.setDrawable(dr0);
            image.setSize(buttonSize, buttonSize);
            float buttonX = (i % 5) * (buttonSize + buttonGapSize);
            float buttonY = (i / 5) * (buttonSize + buttonGapSize);
            image.setPosition(buttonX, buttonY);
            int levelId = i;
            image.setColor(buttonTint);
            image.setOrigin(buttonSize * 0.5f, buttonSize * 0.5f);
            image.addListener((event) -> {
                if(((InputEvent) event).getType() == InputEvent.Type.touchDown){
                    image.addAction(Actions.sequence(
                            AnimationTools.xFlipAction(image, 1.0f),
                            new Action() {
                                @Override
                                public boolean act(float delta) {
                                    fireEvent((l) -> ((LevelSelectListener) l).levelSelected(packId, levelId));
                                    return true;
                                }
                            }
                    ));
                }
                return true;
            });
            h.addActor(image);
        }
        h.setSize(groupWidth, groupHeight);
        h.setPosition(0.5f * (ColorSplashGame.VIEWPORT_WIDTH - groupWidth), 0.5f * (ColorSplashGame.VIEWPORT_HEIGHT - groupHeight));
        addActor(h);
    }

    public void addLevelSelectListener(LevelSelectListener listener){
        addViewContextListener(listener);
    }


}
