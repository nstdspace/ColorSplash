package de.nstdspace.colorsplash.game.gamemode;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import java.util.ArrayList;
import java.util.Random;

import de.nstdspace.colorsplash.game.GameListener;
import de.nstdspace.colorsplash.view.DefaultColorBox;
import de.nstdspace.colorsplash.view.DefaultStylesheet;
import de.nstdspace.colorsplash.view.GameField;

public abstract class DefaultGameMode implements GameMode {

    private ArrayList<GameListener> gameListener;
    private GameField gameField;
    private int shuffleCount;

    public DefaultGameMode(int shuffleCount){
        this.gameListener = new ArrayList<>();
        this.shuffleCount = shuffleCount;
    }

    @Override
    public void create() {
        createGameField();
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

    public abstract void restoreInitialGameFieldAppearance();

    public abstract GameFieldPattern getGameEndPattern();

    private void createGameField(){
        gameField = new GameField(new DefaultStylesheet());
        gameField.addGameFieldListener(this);
        restoreInitialGameFieldAppearance();
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

    interface GameFieldPattern {
        boolean checkBox(DefaultColorBox box);
    }
}