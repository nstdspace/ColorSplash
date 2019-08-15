package de.nstdspace.colorsplash.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

import java.util.HashMap;
import java.util.Map;

import de.nstdspace.colorsplash.ColorSplashGame;

public class GameField extends Group {

    public static final float RELATIVE_BOARD_WIDTH = 0.8f;

    private float boardSize = ColorSplashGame.VIEWPORT_WIDTH * RELATIVE_BOARD_WIDTH;
    private int gridSize = 5;
    private float boxSize = boardSize / gridSize;

    //bottom left: (0, 0)
    private DefaultColorBox[][] boxGrid = new DefaultColorBox[gridSize][gridSize];

    private GameFieldListener gameFieldListener;

    private Stylesheet stylesheet;

    public GameField(Stylesheet stylesheet){
        this.stylesheet = stylesheet;
        setPosition((ColorSplashGame.VIEWPORT_WIDTH - boardSize) / 2, (ColorSplashGame.VIEWPORT_HEIGHT - boardSize) / 2);
        setSize(boardSize, boardSize);
        setOrigin(0.5f * boardSize, 0.5f * boardSize);
        create();
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        super.draw(batch, parentAlpha);
    }

    public void addGameFieldListener(GameFieldListener listener){
        this.gameFieldListener = listener;
    }

    private void create(){
        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                final DefaultColorBox box = new DefaultColorBox(i, j, stylesheet.getColorBoxTexture());
                box.setColor(Color.BLACK);
                box.setSize(boxSize, boxSize);
                box.setOrigin(box.getWidth() * 0.5f, box.getHeight() * 0.5f);
                box.setPosition(i * boxSize, j * boxSize);
                box.addListener(new EventListener(){
                    @Override
                    public boolean handle(Event event){
                        if(gameFieldListener != null && ((InputEvent) event).getType() == InputEvent.Type.touchDown)
                            gameFieldListener.handleFieldTap(box.fieldPositionX, box.fieldPositionY);
                        return false;
                    }
                });
                addActor(box);
                boxGrid[j][i] = box;
            }
        }
    }

    public void changeColors(DefaultColorBox box, ChangePattern pattern, Map<Color, Color> colorSwitchMap){
        changeColors(box.fieldPositionX, box.fieldPositionY, pattern, colorSwitchMap);
    }

    public void changeColors(int x, int y, ChangePattern pattern, Map<Color, Color> colorSwitchMap){
        for(int entry[] : pattern.getChangeBehvaiour()){
            int arrayX = x + entry[0];
            int arrayY = y + entry[1];
            if(arrayX >= 0 && arrayX < boxGrid.length && arrayY >= 0 && arrayY < boxGrid.length){
                Color currentColor = getColorBox(arrayX, arrayY).getGameColor();
                int colorStep = entry[2];
                for(int i = 0; i < colorStep; i++){
                    currentColor = colorSwitchMap.get(currentColor);
                }
                boxGrid[arrayY][arrayX].setColor(currentColor);
            }
        }
    }

    public void fill(final Color color){
        iterateBoxGrid(new BoxAction() {
            @Override
            public boolean filter(DefaultColorBox box) {
                return true;
            }

            @Override
            public void apply(DefaultColorBox box) {
                box.setColor(color);
            }
        });
    }

    public void iterateBoxGrid(BoxAction action){
        for(int i = 0; i < boxGrid.length; i++){
            for(int j = 0; j < boxGrid[i].length; j++){
                if(action.filter(boxGrid[j][i])) action.apply(boxGrid[j][i]);
            }
        }
    }

    public interface BoxAction {

        default boolean filter(DefaultColorBox box){
            return true;
        }

        void apply(DefaultColorBox box);
    }

    public DefaultColorBox[][] getBoxGrid(){
        return boxGrid;
    }

    public Stylesheet getStylesheet(){
        return stylesheet;
    }

    public DefaultColorBox getColorBox(int x, int y){
        return boxGrid[y][x];
    }

    public int getGridSize(){
        return gridSize;
    }

    public float getBoardSize(){
        return boardSize;
    }

    /**
     * This represents how the boxes around
     * a tapped box are affected eg if it is tapped
     * (or the change event is triggered)
     *
     * TODO: find good name for this. extract class when clean.
     */
    public static class ChangePattern {

        public static ChangePattern CROSS_SURROUND = new ChangePattern(new int[][]{{0, 1, 1}, {1, 0, 1}, {-1, 0, 1}, {0, -1, 1}});

        /**
         * 0: x-dir
         * 1: y-dir
         * 2: color-steps
         */
        private int changeBehvaiour[][];

        public ChangePattern(int changeBehvaiour[][]){
            this.changeBehvaiour = changeBehvaiour;
        }

        public int[][] getChangeBehvaiour(){
            return changeBehvaiour;
        }
    }
}
