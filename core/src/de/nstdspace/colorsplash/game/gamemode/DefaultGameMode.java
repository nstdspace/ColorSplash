package de.nstdspace.colorsplash.game.gamemode;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import java.util.ArrayList;
import java.util.Random;

import de.nstdspace.colorsplash.game.GameListener;
import de.nstdspace.colorsplash.view.DefaultColorBox;
import de.nstdspace.colorsplash.view.GameField;
import de.nstdspace.colorsplash.view.Stylesheet;

/**
 * default game mode with monochrome end condition
 * and simple "around the blcok" - change pattern
 */

public abstract class DefaultGameMode implements MonochromeGameMode {

    private ArrayList<GameListener> gameListener;
    private GameField gameField;
    private int shuffleCount;
    private Color color;

    interface GameFieldPattern {
        boolean checkBox(DefaultColorBox box);
    }

    public DefaultGameMode(Color color, int shuffleCount){
        this.color = color;
        this.gameListener = new ArrayList<>();
        this.shuffleCount = shuffleCount;
    }

    @Override
    public void init(){
        shuffleGameField(shuffleCount);
    }

    public void shuffleGameField(int shuffleCount){
        Random random = new Random();
        for(int i = 0; i < shuffleCount; i++){
            int randomX = random.nextInt(gameField.getGridSize());
            int randomY = random.nextInt(gameField.getGridSize());
            fieldTapAction(gameField.getColorBox(randomX, randomY));
        }
    }

    public void makeInitialGameFieldAppearance(){

    }

    public GameFieldPattern getGameEndPattern(){
        return filledMonochromePattern;
    }

    public void createGameField(Stylesheet stylesheet){
        gameField = new GameField(stylesheet);
        gameField.addGameFieldListener(this);
        makeInitialGameFieldAppearance();
    }

    public void animateTappedField(DefaultColorBox box){
        box.addAction(Actions.sequence(
                Actions.scaleTo(0.7f, 0.7f, 0.1f, Interpolation.fade),
                Actions.scaleTo(1.1f, 1.1f, 0.1f, Interpolation.fade),
                Actions.scaleTo(0.8f, 0.8f, 0.1f, Interpolation.fade),
                Actions.scaleTo(1.0f, 1.0f, 0.1f, Interpolation.fade)));
    }

    public abstract void fieldTapAction(DefaultColorBox box);

    @Override
    public void handleFieldTap(int x, int y){
        DefaultColorBox box = gameField.getColorBox(x, y);
        fieldTapAction(box);
        animateTappedField(box);
        if(checkGameEndCondition()){
            for(GameListener listener : gameListener)
            listener.gameFinished();
        }
    }

    @Override
    public GameField getGameField(){
        return gameField;
    }

    @Override
    public void addGameListener(GameListener listener){
        gameListener.add(listener);
    }

    @Override
    public boolean checkGameEndCondition(){
        return checkGameFieldPattern(getGameEndPattern());
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

    private GameFieldPattern filledMonochromePattern = new GameFieldPattern() {
        @Override
        public boolean checkBox(DefaultColorBox box) {
            return box.getGameColor().equals(color);
        }
    };

    @Override
    public Color getInitialColor() {
        return color;
    }

    @Override
    public Color getTargetColor() {
        return color;
    }
}