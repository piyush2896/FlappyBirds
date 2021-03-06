package com.mygdx.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Piyush on 19-Mar-16.
 */
public abstract class State {
    protected OrthographicCamera mCamera;
    protected Vector3 mMouse;
    protected GameStateManager mManager;

    protected State(GameStateManager manager){
        mManager = manager;
        mCamera = new OrthographicCamera();
        mMouse = new Vector3();
    }

    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch spriteBatch);
    public abstract void dispose();

}
