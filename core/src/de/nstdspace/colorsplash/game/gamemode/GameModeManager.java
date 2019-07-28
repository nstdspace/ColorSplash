package de.nstdspace.colorsplash.game.gamemode;

import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;

/**
 *  kind of a factory to avoid not existing data at construction
 *
 *  create() and init() have to be called after initializing new
 *  game mode object.
 */
public class GameModeManager {

    private static void enrollGameMode(GameMode gameMode){
        gameMode.create();
        gameMode.init();
    }

    public static GameMode1 enrollGameMode1(ArrayList<Color> colors, Color gameEndColor, int shuffleCount) {
        GameMode1 gameMode = new GameMode1(colors, gameEndColor, shuffleCount);
        enrollGameMode(gameMode);
        return gameMode;
    }
}