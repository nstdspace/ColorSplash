package de.nstdspace.colorsplash.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import java.util.ArrayList;
import java.util.HashMap;

import de.nstdspace.colorsplash.view.DefaultColorBox;
import de.nstdspace.colorsplash.view.DefaultStylesheet;
import de.nstdspace.colorsplash.view.GameField;
import de.nstdspace.colorsplash.view.GameFieldListener;

public class DefaultGameMode implements GameMode, GameFieldListener {

    private GameListener gameListener;
    private GameField gameField;

    private Color gameEndFillColor;
    private HashMap<Color, Color> colorSwitchMap;

    public DefaultGameMode(ArrayList<Color> colors){
        this(colors, colors.get(0));
    }

    public DefaultGameMode(ArrayList<Color> colors, Color gameEndFillColor){
        this.gameEndFillColor = gameEndFillColor;
        createColorSwitchMap(colors);
        create();
        //TODO: make level system and so on
        gameField.shuffle(GameField.ChangePattern.CROSS_SURROUND, colorSwitchMap, 100);
    }

    @Override
    public void create() {
        createGameField();
    }

    private void createGameField(){
        gameField = new GameField(new DefaultStylesheet());
        gameField.addGameFieldListener(this);
        gameField.fill(gameEndFillColor);
    }

    private void createColorSwitchMap(ArrayList<Color> colors){
        colorSwitchMap = new HashMap<Color, Color>();
        for(int i = 0; i < colors.size(); i++){
            if(i == colors.size() - 1) colorSwitchMap.put(colors.get(i), colors.get(0));
            else colorSwitchMap.put(colors.get(i), colors.get(i + 1));
        }
        Gdx.app.log("colorSwitchMap: ", colorSwitchMap.toString());
    }

    @Override
    public void handleFieldTap(int x, int y){
        gameField.changeColors(x, y, GameField.ChangePattern.CROSS_SURROUND, colorSwitchMap);

        //only a test.
        gameField.getColorBox(x, y).addAction(Actions.sequence(
                Actions.scaleTo(0.7f, 0.7f, 0.1f, Interpolation.fade),
                Actions.scaleTo(1.1f, 1.1f, 0.1f, Interpolation.fade),
                Actions.scaleTo(0.8f, 0.8f, 0.1f, Interpolation.fade),
                Actions.scaleTo(1.0f, 1.0f, 0.1f, Interpolation.fade)));

        if(checkGameEndCondition()){
            gameListener.gameFinished();
        }
    }

    @Override
    public GameField getGameField(){
        return gameField;
    }

    @Override
    public void addGameListener(GameListener listener){
        this.gameListener = listener; // more like setGameListener?
    }

    private GameFieldPattern defaultPattern = new GameFieldPattern() {
        @Override
        public boolean checkBox(DefaultColorBox box) {
            return box.getGameColor().equals(gameEndFillColor);
        }
    };

    @Override
    public boolean checkGameEndCondition(){
        return checkGameFieldPattern(defaultPattern);
    }

    private boolean checkGameFieldPattern(GameFieldPattern pattern){
        DefaultColorBox colorBoxGrid[][] = gameField.getBoxGrid();
        for(DefaultColorBox row[] : colorBoxGrid){
            for(DefaultColorBox box : row){
                if(!pattern.checkBox(box)) return false;
            }
        }
        return true;
    }

    interface GameFieldPattern {
        boolean checkBox(DefaultColorBox box);
    }
}