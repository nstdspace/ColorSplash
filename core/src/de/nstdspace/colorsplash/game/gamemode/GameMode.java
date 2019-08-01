package de.nstdspace.colorsplash.game.gamemode;

import de.nstdspace.colorsplash.game.GameListener;
import de.nstdspace.colorsplash.view.GameField;
import de.nstdspace.colorsplash.view.GameFieldListener;
import de.nstdspace.colorsplash.view.Stylesheet;

public interface GameMode extends GameFieldListener {

    boolean checkGameEndCondition();
    void create();
    void createGameField(Stylesheet stylesheet);
    void init();
    void addGameListener(GameListener listener);
    void makeInitialGameFieldAppearance();
    GameField getGameField();
}
