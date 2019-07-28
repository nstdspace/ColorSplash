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

import de.nstdspace.colorsplash.game.GameListener;
import de.nstdspace.colorsplash.game.gamemode.GameMode;
import de.nstdspace.colorsplash.game.gamemode.GameModeManager;
import de.nstdspace.colorsplash.view.DefaultStylesheet;
import de.nstdspace.colorsplash.view.GameField;
import de.nstdspace.colorsplash.view.LevelSelectContext;
import de.nstdspace.colorsplash.view.Stylesheet;
import de.nstdspace.colorsplash.view.subftrs.GuiViewContext;
import de.nstdspace.colorsplash.view.subftrs.IntroViewContext;
import de.nstdspace.colorsplash.view.subftrs.ViewContextListener;

public class ColorSplashGame extends ApplicationAdapter implements GameListener {

	private Stage gameStage;
	private GameMode gameMode;
	private SpriteBatch batch;
	private BitmapFont defaultFont;
	private OrthographicCamera camera;
	private Stylesheet defaultStyleSheet;

	public static float VIEWPORT_WIDTH = 720;
	public static float VIEWPORT_HEIGHT = 1280;

	public boolean SHOW_INTRO = false;

	@Override
	public void create() {
		super.create();

		VIEWPORT_WIDTH = Gdx.graphics.getWidth();
		VIEWPORT_HEIGHT = Gdx.graphics.getHeight();

		defaultStyleSheet = new DefaultStylesheet();
		GameModeManager.setStylesheet(defaultStyleSheet);

		createCamera();
		createGameStage();
		createGameMode();
		loadResources();

		if(SHOW_INTRO) {
			showIntro();
		}
		else {
			showLevelSelect();
			//showGame();
		}

		batch = new SpriteBatch();
		Gdx.input.setInputProcessor(gameStage);
	}

	private void createCamera(){
		camera = new OrthographicCamera();
		camera.setToOrtho(true, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
	}

	private void createGameStage(){
		//TODO: choose best viewport..
		FitViewport viewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, camera);
		gameStage = new Stage(viewport);
	}

	private void createGameMode(){
		ArrayList<Color> colorList = new ArrayList<Color>();
		colorList.add(Color.RED);
		colorList.add(Color.GREEN);
		colorList.add(Color.BLUE);
		colorList.add(Color.BROWN);
		gameMode = GameModeManager.enrollGameMode1(colorList, Color.RED, 1);
		gameMode.addGameListener(this);
	}

	private void showIntro(){
		final IntroViewContext introViewContext = new IntroViewContext(defaultFont);
		introViewContext.setSubViewListener(new ViewContextListener() {
			@Override
			public void onCreate() {

			}

			@Override
			public void onDispose() {
				showGame();
				introViewContext.addAction(Actions.removeActor());
			}
		});
		gameStage.addActor(introViewContext);
	}

	private void showLevelSelect(){
		LevelSelectContext context = new LevelSelectContext(defaultFont);
		gameStage.addActor(context);
	}

	private void showGame(){
		gameStage.addActor(new GuiViewContext(gameMode.getGameField().getStylesheet()));
		gameStage.addActor(gameMode.getGameField());
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
		GameField gameField = gameMode.getGameField();
		gameField.addAction(Actions.parallel(
				Actions.sequence(
					Actions.scaleTo(0, 1, 1, Interpolation.pow3In),
					Actions.scaleTo(1, 1, 1, Interpolation.pow3Out)
				),
				Actions.sequence(
					Actions.moveBy(gameField.getBoardSize() * 0.5f, 0, 1, Interpolation.pow3In),
					Actions.moveBy(-1 * gameField.getBoardSize() * 0.5f, 0, 1, Interpolation.pow3Out)
				),
				Actions.alpha(0, 2, Interpolation.fade)
		));

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