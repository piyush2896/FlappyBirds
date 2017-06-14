package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by Piyush on 19-Mar-16.
 */
public class GameStateManager {

    private Stack<State> mStateStack;

    public GameStateManager() {
        mStateStack = new Stack<State>();
    }

    public void push(State state){
        mStateStack.push(state);
    }

    public void pop(){
        mStateStack.pop().dispose();
    }

    public void set(State state){
        mStateStack.pop().dispose();
        mStateStack.push(state);
    }

    public void update(float dt){
        mStateStack.peek().update(dt);
    }

    public void render(SpriteBatch spriteBatch){
        mStateStack.peek().render(spriteBatch);
    }
}
