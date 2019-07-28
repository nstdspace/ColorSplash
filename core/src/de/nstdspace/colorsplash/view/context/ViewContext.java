package de.nstdspace.colorsplash.view.context;

import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.ArrayList;

public abstract class ViewContext extends Group {

    private ArrayList<ViewContextListener> listener;

    public ViewContext(){
        listener = new ArrayList<>();
    }

    protected void onCreate(){
        for(ViewContextListener listener : this.listener){
            listener.onCreate();
        }
    }

    protected void onDispose(){
        for(ViewContextListener listener : this.listener){
            listener.onDispose();
        }
    }

    public void addViewContextListener(ViewContextListener listener){
        this.listener.add(listener);
    }
}