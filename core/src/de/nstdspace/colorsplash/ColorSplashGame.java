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
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.utils.viewport.FitViewport;

import de.nstdspace.colorsplash.game.GameListener;
import de.nstdspace.colorsplash.game.LevelManager;
import de.nstdspace.colorsplash.game.gamemode.GameMode;
import de.nstdspace.colorsplash.game.gamemode.GameModeManager;
import de.nstdspace.colorsplash.view.DefaultStylesheet;
import de.nstdspace.colorsplash.view.GameField;
import de.nstdspace.colorsplash.view.Stylesheet;
import de.nstdspace.colorsplash.view.context.GuiViewContext;
import de.nstdspace.colorsplash.view.context.IntroViewContext;
import de.nstdspace.colorsplash.view.context.LevelSelectContext;
import de.nstdspace.colorsplash.view.context.ViewContextListener;

public class ColorSplashGame extends ApplicationAdapter implements GameListener {

	private Stage gameStage;
	private GameMode currentGameMode;
	private SpriteBatch batch;
	private BitmapFont defaultFont;
	private OrthographicCamera camera;
	private Stylesheet defaultStyleSheet;
    private GuiViewContext guiViewContext;

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
		loadResources();

		if(SHOW_INTRO) {
			showIntro();
		}
		else {
			showGuiContext();
			showLevelSelect();
			//showGame();
		}

		batch = new SpriteBatch();
		Gdx.input.setInputProcessor(gameStage);
	}

	private void createCamera(){
		camera = new OrthographicCamera();
		camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
	}

	private void createGameStage(){
		//TODO: choose best viewport..
		FitViewport viewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, camera);
		gameStage = new Stage(viewport);
	}

	private void showIntro(){
		final IntroViewContext introViewContext = new IntroViewContext(defaultFont);
		introViewContext.addViewContextListener(new ViewContextListener() {
			@Override
			public void onCreate() {

			}

			@Override
			public void onDispose() {
				showLevelSelect();
			}
		});
		gameStage.addActor(introViewContext);
	}

	private void showGuiContext(){
		guiViewContext = new GuiViewContext(defaultStyleSheet);
		guiViewContext.setColor(Color.WHITE);
		makeGuiBackgroundGlimmer();
		gameStage.addActor(guiViewContext);
	}

	//TODO: move this to gui context
	Action glimmerAction;
	private void makeGuiBackgroundGlimmer(){
		Color backgroundChangeColors[] = new Color[]{
				Color.GREEN, Color.RED, Color.BLUE, Color.PINK, Color.ORANGE
		};
		Action[] colorActions = new Action[backgroundChangeColors.length];
		for (int i = 0; i < colorActions.length; i++){
			colorActions[i] = Actions.color(backgroundChangeColors[i], 4f, Interpolation.sine);
		}
		glimmerAction = Actions.repeat(RepeatAction.FOREVER, Actions.sequence(colorActions));
		guiViewContext.addAction(glimmerAction);
	}

	private void stopGuiBackgroundGlimmer(){
		guiViewContext.removeAction(glimmerAction);
	}


	private void showLevelSelect(){
		LevelSelectContext context = new LevelSelectContext(defaultFont);
		context.addLevelSelectListener((int pack, int level) -> {
			context.remove();
			initLevel(pack, level);
			showGame();
		});
		gameStage.addActor(context);
	}

	private void initLevel(int pack, int level){
        currentGameMode = LevelManager.initGameMode(pack, level);
		currentGameMode.addGameListener(this);
	}

	private void showGame(){
		//TODO: move to gui context
		stopGuiBackgroundGlimmer();
		gameStage.addActor(currentGameMode.getGameField());
	}

	private void loadResources(){
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 70;
		parameter.minFilter = Texture.TextureFilter.Nearest;
		parameter.magFilter = Texture.TextureFilter.MipMapLinearNearest;
		parameter.flip = false;
		parameter.color = Color.WHITE;
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Minimoon.ttf"));
		defaultFont = generator.generateFont(parameter);
	}


	@Override
	public void gameFinished() {
		GameField gameField = currentGameMode.getGameField();
		Action afterDismissAction = new Action() {
			@Override
			public boolean act(float delta) {
				gameField.remove();
				showLevelSelect();
				return true;
			}
		};
		gameField.addAction(Actions.sequence(
			defaultStyleSheet.getAnimationStylesheet().provideGameFieldDismissAnimation(gameField),
			afterDismissAction
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
		defaultFont.dispose();
		batch.dispose();
		gameStage.dispose();
		defaultStyleSheet.dispose();
	}
}