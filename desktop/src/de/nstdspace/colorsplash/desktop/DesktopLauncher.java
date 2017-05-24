package de.nstdspace.colorsplash.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import de.nstdspace.colorsplash.ColorSplashGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = (int) (ColorSplashGame.VIEWPORT_WIDTH * 0.6f);
		config.height = (int) (ColorSplashGame.VIEWPORT_HEIGHT * 0.6f);
		new LwjglApplication(new ColorSplashGame(), config);
	}
}
