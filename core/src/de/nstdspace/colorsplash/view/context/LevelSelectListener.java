package de.nstdspace.colorsplash.view.context;

public interface LevelSelectListener extends ViewContextListener {

    void levelSelected(int pack, int level);

    @Override
    default void onCreate() {

    }

    @Override
    default void onDispose() {

    }
}
