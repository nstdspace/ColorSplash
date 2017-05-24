package de.nstdspace.colorsplash;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;
import java.util.HashMap;

import de.nstdspace.colorsplash.game.DefaultGameMode;
import de.nstdspace.colorsplash.game.GameListener;
import de.nstdspace.colorsplash.game.GameMode;
import de.nstdspace.colorsplash.view.DefaultStylesheet;
import de.nstdspace.colorsplash.view.GameField;
import de.nstdspace.colorsplash.view.subftrs.GuiViewContext;
import de.nstdspace.colorsplash.view.subftrs.IntroViewContext;
import de.nstdspace.colorsplash.view.subftrs.ViewContextListener;

public class ColorSplashGame extends ApplicationAdapter implements GameListener {

	private Stage gameStage;
	private GameMode gameMode;
	private SpriteBatch batch;
	private BitmapFont defaultFont;

	public static float VIEWPORT_WIDTH = 720;
	public static float VIEWPORT_HEIGHT = 1280;

	@Override
	public void create() {
		super.create();



		OrthographicCamera camera = new OrthographicCamera();
		camera.setToOrtho(true, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
		//TODO: choose best viewport..
		FitViewport viewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, camera);
		gameStage = new Stage(viewport);

//		ArrayList<Color> colorList = new ArrayList<Color>();
//		colorList.add(Color.RED);
//		colorList.add(Color.GREEN);
//		colorList.add(Color.BLUE);
//		colorList.add(Color.BROWN);
//		gameMode = new DefaultGameMode(colorList, Color.RED);
//		gameMode.addGameListener(this);

		/**
		 * TEST NEW (anonymous) GAMEMODE!
         * you have to tap 10 fields to win.
		 */
        gameMode = new GameMode() {

            int counter = 0;
            GameField field = new GameField(new DefaultStylesheet());
            GameListener listener;

            @Override
            public void create() {
                field.fill(Color.GREEN);
                field.addGameFieldListener(this);
            }

            @Override
            public void addGameListener(GameListener listener) {
                this.listener = listener;
            }

            @Override
            public GameField getGameField() {
                return field;
            }

            @Override
            public boolean checkGameEndCondition() {
                return counter == 10;
            }

            @Override
            public void handleFieldTap(int x, int y) {
                counter++;
				HashMap<Color, Color> map = new HashMap<Color, Color>();
				map.put(Color.GREEN, Color.RED);
				map.put(Color.RED, Color.GREEN);
				field.shuffle(new GameField.ChangePattern(new int[][]{{0, 0}}), map, 1000000);
                if(checkGameEndCondition()) listener.gameFinished();
            }
        };
		gameMode.addGameListener(this);
		gameMode.create();
		/**
		 * TEST end
		 */

		loadResources();
//
		final IntroViewContext introViewContext = new IntroViewContext(defaultFont);
		introViewContext.setSubViewListener(new ViewContextListener() {
			@Override
			public void onCreate() {

			}

			@Override
			public void onDispose() {
				gameStage.addActor(new GuiViewContext(gameMode.getGameField().getStylesheet()));
				gameStage.addActor(gameMode.getGameField());
				introViewContext.addAction(Actions.removeActor());
			}
		});
		gameStage.addActor(introViewContext);

		batch = new SpriteBatch();
		Gdx.input.setInputProcessor(gameStage);
	}

	private void loadResources(){
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 70;
		parameter.minFilter = Texture.TextureFilter.Nearest;
		parameter.magFilter = Texture.TextureFilter.MipMapLinearNearest;
		parameter.flip = true;
		parameter.color = Color.WHITE;
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Minimoon.ttf"));
		defaultFont = generator.generateFont(parameter);
	}


	@Override
	public void gameFinished() {
		Gdx.app.log("FINISH!", "PS: you are a noob.");
		gameMode.getGameField().addAction(Actions.moveBy(0, 1000, 1f, Interpolation.fade));
	}


	@Override
	public void render() {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gameStage.act();
		gameStage.draw();
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}