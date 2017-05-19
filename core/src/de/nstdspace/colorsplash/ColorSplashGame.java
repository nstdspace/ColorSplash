package de.nstdspace.colorsplash;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;

import de.nstdspace.colorsplash.game.DefaultGameMode;
import de.nstdspace.colorsplash.game.GameListener;
import de.nstdspace.colorsplash.view.subftrs.GuiViewContext;
import de.nstdspace.colorsplash.view.subftrs.IntroViewContext;
import de.nstdspace.colorsplash.view.subftrs.ViewContextListener;

public class ColorSplashGame extends ApplicationAdapter implements GameListener {

	private Stage gameStage;
	private DefaultGameMode gameMode;
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

		ArrayList<Color> colorList = new ArrayList<Color>();
		colorList.add(Color.RED);
		colorList.add(Color.GREEN);
		colorList.add(Color.BLUE);
		colorList.add(Color.BROWN);
		gameMode = new DefaultGameMode(colorList, Color.RED);
		gameMode.addGameListener(this);

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
//		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
//		parameter.size = 70;
//		parameter.minFilter = Texture.TextureFilter.Nearest;
//		parameter.magFilter = Texture.TextureFilter.MipMapLinearNearest;
//		parameter.flip = true;
//		parameter.color = Color.WHITE;
//		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Minimoon.ttf"));
//		defaultFont = generator.generateFont(parameter);
	}


	@Override
	public void gameFinished() {
		Gdx.app.log("FINISH!", "PS: you are a noob.");
		gameStage.addAction(Actions.moveBy(0, 1000, 1f, Interpolation.fade));
	}


	@Override
	public void render() {
		Gdx.gl.glClearColor(0.0f, 1.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gameStage.act();
		gameStage.draw();
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}