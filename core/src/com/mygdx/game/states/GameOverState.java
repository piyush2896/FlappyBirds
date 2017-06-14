package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.FlappyBirds;

/**
 * Created by Piyush on 24-Mar-16.
 */
public class GameOverState extends State{

    private Texture mBackground;
    private Texture mGameOver;

    protected GameOverState(GameStateManager manager) {
        super(manager);
        mCamera.setToOrtho(false, FlappyBirds.WIDTH / 2, FlappyBirds.HEIGHT / 2);
        mBackground = new Texture("bg.png");
        mGameOver = new Texture("gameover.png");
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            mManager.set(new MenuState(mManager));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(mCamera.combined);
        spriteBatch.begin();
        spriteBatch.draw(mBackground, 0, 0);
        spriteBatch.draw(mGameOver, (mCamera.position.x - mGameOver.getWidth()/2),
                (mCamera.position.y));
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        mBackground.dispose();
        mGameOver.dispose();
    }
}
