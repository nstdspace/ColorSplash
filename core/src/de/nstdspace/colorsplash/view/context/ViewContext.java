package de.nstdspace.colorsplash.view.context;

import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.ArrayList;

public abstract class ViewContext extends Group {

    private ArrayList<ViewContextListener> listener;

    public ViewContext(){
        listener = new ArrayList<>();
    }

    protected void onCreate(){
        fireEvent((l) -> l.onCreate());
    }

    protected void onDispose(){
        fireEvent((l) -> l.onDispose());
    }

    protected void fireEvent(ViewContextEventDisposer disposer){
        for(ViewContextListener listener : this.listener){
            disposer.fire(listener);
        }
    }

    protected interface ViewContextEventDisposer {
        void fire(ViewContextListener context);
    }

    public void addViewContextListener(ViewContextListener listener){
        this.listener.add(listener);
    }
}