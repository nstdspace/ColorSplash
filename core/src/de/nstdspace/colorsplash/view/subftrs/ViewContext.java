package de.nstdspace.colorsplash.view.subftrs;

import com.badlogic.gdx.scenes.scene2d.Group;

public abstract class ViewContext extends Group {

    private ViewContextListener listener;

    public ViewContext(){
        listener = new ViewContextListener() {
            @Override
            public void onCreate() {

            }

            @Override
            public void onDispose() {

            }
        };
    }

    protected void onCreate(){
        listener.onCreate();
    }

    protected void onDispose(){
        listener.onDispose();
    }

    public void setSubViewListener(ViewContextListener listener){
        this.listener = listener;
    }
}