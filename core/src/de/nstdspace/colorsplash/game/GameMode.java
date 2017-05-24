package de.nstdspace.colorsplash.game;

import de.nstdspace.colorsplash.view.GameField;
import de.nstdspace.colorsplash.view.GameFieldListener;

public interface GameMode extends GameFieldListener {

    boolean checkGameEndCondition();
    void create();
    void addGameListener(GameListener listener);
    GameField getGameField();
}
