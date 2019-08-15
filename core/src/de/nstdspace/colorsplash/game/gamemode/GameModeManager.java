package de.nstdspace.colorsplash.game.gamemode;

import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;
import java.util.List;


import de.nstdspace.colorsplash.view.DefaultColorBox;
import de.nstdspace.colorsplash.view.GameField;
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

    public static GameModeLevel1_1 enrollGameMode1(List<Color> colors, Color gameEndColor, int shuffleCount) {
        GameModeLevel1_1 gameMode = new GameModeLevel1_1(colors, gameEndColor, shuffleCount);
        enrollGameMode(gameMode);
        return gameMode;
    }

    public static DefaultGameMode enrollGameModeLike1WithPattern(List<Color> colors, Color gameEndColor, int shuffleCount, GameField.ChangePattern pattern){
        GameModeLevel1_1 gameMode = new GameModeLevel1_1(colors, gameEndColor, shuffleCount){
            @Override
            public void fieldTapAction(DefaultColorBox box) {
                getGameField().changeColors(box, pattern, getColorSwitchMap());
            }
        };
        enrollGameMode(gameMode);
        return gameMode;
    }
}