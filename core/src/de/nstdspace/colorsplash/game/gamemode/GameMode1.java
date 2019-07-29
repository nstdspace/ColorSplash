package de.nstdspace.colorsplash.game.gamemode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;
import java.util.HashMap;

import de.nstdspace.colorsplash.view.DefaultColorBox;
import de.nstdspace.colorsplash.view.GameField;

public class GameMode1 extends DefaultGameMode {

    private Color gameEndFillColor;
    private ArrayList<Color> colors;
    private HashMap<Color, Color> colorSwitchMap;

    public GameMode1(ArrayList<Color> colors, Color gameEndFillColor, int shuffleCount){
        super(shuffleCount);
        this.colors = colors;
        this.gameEndFillColor = gameEndFillColor;
    }

    @Override
    public void create() {
        createColorSwitchMap(colors);
    }

    private void createColorSwitchMap(ArrayList<Color> colors){
        colorSwitchMap = new HashMap<>();
        for(int i = 0; i < colors.size(); i++){
            if(i == colors.size() - 1) colorSwitchMap.put(colors.get(i), colors.get(0));
            else colorSwitchMap.put(colors.get(i), colors.get(i + 1));
        }
        Gdx.app.log("colorSwitchMap: ", colorSwitchMap.toString());
    }

    private GameFieldPattern filledMonochromePattern = new GameFieldPattern() {
        @Override
        public boolean checkBox(DefaultColorBox box) {
            return box.getGameColor().equals(gameEndFillColor);
        }
    };

    @Override
    public GameFieldPattern getGameEndPattern() {
        return filledMonochromePattern;
    }

    @Override
    public void restoreInitialGameFieldAppearance() {
        getGameField().fill(gameEndFillColor);
    }

    @Override
    public void fieldTapAction(DefaultColorBox box) {
        getGameField().changeColors(box, GameField.ChangePattern.CROSS_SURROUND, colorSwitchMap);
    }
}
