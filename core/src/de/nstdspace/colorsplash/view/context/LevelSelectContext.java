package de.nstdspace.colorsplash.view.context;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import de.nstdspace.colorsplash.ColorSplashGame;
import de.nstdspace.colorsplash.view.ResourceTools;
import de.nstdspace.colorsplash.view.TextActor;

public class LevelSelectContext extends ViewContext {

    private Texture levelAtlas;

    public LevelSelectContext(BitmapFont font){
        TextActor levelPackText = new TextActor("level Pack 1", font);
        levelPackText.setPosition(0.5f * (-levelPackText.getWidth() + ColorSplashGame.VIEWPORT_WIDTH), 0.14f * ColorSplashGame.VIEWPORT_HEIGHT);
        addActor(levelPackText);

        createLevelButtons();

        //Gdx.app.log("[info]", "" + h.getWidth() + ", " + h.getHeight());

    }

    private void createLevelButtons(){
        // TODO: this has to come from stylesheet or so
        levelAtlas = new Texture(Gdx.files.internal("level_num_atlas.png"));
        TextureRegion region0 = new TextureRegion(levelAtlas, 0, 0, 32, 32);
        TextureRegion region1 = new TextureRegion(levelAtlas, 32, 0, 32, 32);
        TextureRegionDrawable dr0 = new TextureRegionDrawable(region0);
        TextureRegionDrawable dr1 = new TextureRegionDrawable(region1);

        // TODO: remove hardcoding?
        float buttonRows = 7;
        float buttonCols = 5;
        float groupWidth = 0.7f * ColorSplashGame.VIEWPORT_WIDTH;
        float buttonRelativeSize = (1 / (buttonCols + 1));
        float buttonSize = buttonRelativeSize * groupWidth;

        float buttonRelativeGapSize1 = (1 - buttonRelativeSize * buttonCols) / (buttonCols - 1);
        float buttonGapSize = buttonRelativeGapSize1 * groupWidth;

        float groupHeight = buttonSize * buttonRows + (buttonRows - 1) * (buttonGapSize);

        Gdx.app.log("DEBUG", "" + buttonGapSize);

        Group h = new Group();

        Texture t = ResourceTools.createOneColoredTexture(Color.PINK);
        Image test = new Image(t);
        test.setSize(groupWidth, groupHeight);
        h.addActor(test);


        for(int i = 0; i < 35; i++){
            Image image = new Image();
            image.setDrawable(i % 2 == 0 ? dr0 : dr1);
            image.setSize(buttonSize, buttonSize);
            float buttonX = (i % 5) * (buttonSize + buttonGapSize);
            float buttonY = (i / 5) * (buttonSize + buttonGapSize);
            image.setPosition(buttonX, buttonY);
            int levelId = i;
            image.addListener((event) -> {
                if(((InputEvent) event).getType() == InputEvent.Type.touchDown){
                    fireEvent((l) -> ((LevelSelectListener) l).levelSelected(levelId));
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
