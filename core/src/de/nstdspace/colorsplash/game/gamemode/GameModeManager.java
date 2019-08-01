package de.nstdspace.colorsplash.game.gamemode;

import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;


import de.nstdspace.colorsplash.view.Stylesheet;

/**
 *  kind of a factory to avoid not existing data at construction
 *
 *  create(), createGameField() and init() have to be called after
 *  initializing new game mode object.
 *
 *  GameModeManager has static "current selected" stylesheeet field for simplicity.
 */
public class GameModeManager {

    public static Stylesheet stylesheet;

    public static void setStylesheet(Stylesheet stylesheet){
        GameModeManager.stylesheet = stylesheet;
    }

    private static void enrollGameMode(GameMode gameMode){
        gameMode.create();
        gameMode.createGameField(GameModeManager.stylesheet);
        gameMode.init();
    }

    public static GameModeLevel1_1 enrollGameMode1(ArrayList<Color> colors, Color gameEndColor, int shuffleCount) {
        GameModeLevel1_1 gameMode = new GameModeLevel1_1(colors, gameEndColor, shuffleCount);
        enrollGameMode(gameMode);
        return gameMode;
    }
}