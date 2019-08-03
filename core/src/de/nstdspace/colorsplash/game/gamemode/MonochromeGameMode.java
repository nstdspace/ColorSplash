package de.nstdspace.colorsplash.game.gamemode;

import com.badlogic.gdx.graphics.Color;

public interface MonochromeGameMode extends GameMode {

    Color getInitialColor();
    Color getTargetColor();
}
