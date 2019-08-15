package de.nstdspace.colorsplash.game.gamemode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.nstdspace.colorsplash.view.DefaultColorBox;
import de.nstdspace.colorsplash.view.GameField;

public class GameModeLevel1_1 extends DefaultGameMode {

    private Color gameEndFillColor;
    private List<Color> colors;
    private Map<Color, Color> colorSwitchMap;

    public GameModeLevel1_1(List<Color> colors, Color gameEndFillColor, int shuffleCount){
        super(gameEndFillColor, shuffleCount);
        this.colors = colors;
        this.gameEndFillColor = gameEndFillColor;
    }

    @Override
    public void create() {
        createColorSwitchMap(colors);
    }

    private void createColorSwitchMap(final List<Color> colors){
        colorSwitchMap = new HashMap<>();
        for(int i = 0; i < colors.size(); i++){
            if(i == colors.size() - 1) colorSwitchMap.put(colors.get(i), colors.get(0));
            else colorSwitchMap.put(colors.get(i), colors.get(i + 1));
        }
        Gdx.app.log("colorSwitchMap: ", colorSwitchMap.toString());
    }

    @Override
    public void fieldTapAction(DefaultColorBox box) {
        getGameField().changeColors(box, GameField.ChangePattern.CROSS_SURROUND, colorSwitchMap);
    }

    public Map<Color, Color> getColorSwitchMap(){
        return colorSwitchMap;
    }
}
