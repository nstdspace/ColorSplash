package de.nstdspace.colorsplash.game;

import com.badlogic.gdx.graphics.Color;

import java.util.Arrays;

import de.nstdspace.colorsplash.game.gamemode.GameMode;
import de.nstdspace.colorsplash.game.gamemode.GameModeManager;
import de.nstdspace.colorsplash.view.GameField;

public class LevelManager {

    public static GameMode initGameMode(int pack, int level){
        GameMode gameMode = null;
        if (pack == 1) {
            if (level == 0) {
                gameMode = GameModeManager.enrollGameMode1(Arrays.asList(Color.RED, Color.GREEN, Color.BLUE, Color.BROWN), Color.RED, 3);
            }
            if (level == 1) {
                gameMode = GameModeManager.enrollGameMode1(Arrays.asList(Color.RED, Color.GREEN, Color.BLUE, Color.BROWN), Color.RED, 20);
            }
            if (level == 2) {
                gameMode = GameModeManager.enrollGameModeLike1WithPattern(Arrays.asList(Color.DARK_GRAY, Color.YELLOW), Color.DARK_GRAY, 10, new GameField.ChangePattern(
                        new int[][]{
                                {-1, 0, 1},
                                {-1, 1, 1},
                                {1, 0, 1},
                                {1, -1, 1}
                        }
                ));
            }
            if (level == 3) {
                gameMode = GameModeManager.enrollGameModeLike1WithPattern(Arrays.asList(Color.WHITE, Color.PURPLE, Color.VIOLET), Color.VIOLET, 20, new GameField.ChangePattern(
                        new int[][]{
                                {0, 1, 1},
                                {-1, -1, 1},
                                {1, -1, 1}
                        }
                ));
            }
            if (level == 4) {
                gameMode = GameModeManager.enrollGameModeLike1WithPattern(Arrays.asList(Color.YELLOW, Color.ORANGE, Color.CYAN, Color.RED), Color.RED, 20, new GameField.ChangePattern(
                        new int[][]{
                                {-1, -1, 2},
                                {-1, 1, 1},
                                {1, -1, 1},
                                {1, 1, 2}
                        }
                ));
            }
        }
        return gameMode;
    }
}
