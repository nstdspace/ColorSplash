package de.nstdspace.colorsplash.game.gamemode;

import de.nstdspace.colorsplash.game.GameListener;
import de.nstdspace.colorsplash.view.GameField;
import de.nstdspace.colorsplash.view.GameFieldListener;

public interface GameMode extends GameFieldListener {

    boolean checkGameEndCondition();
    void create();
    void init();
    void addGameListener(GameListener listener);
    GameField getGameField();
}
