package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.FlappyBirds;

/**
 * Created by Piyush on 19-Mar-16.
 */
public class MenuState extends State {

    private Texture mBackground;
    private Texture mPlayButton;

    public MenuState(GameStateManager manager) {
        super(manager);
        mCamera.setToOrtho(false, FlappyBirds.WIDTH / 2, FlappyBirds.HEIGHT / 2);
        mBackground = new Texture("bg.png");
        mPlayButton = new Texture("playbtn.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            mManager.set(new PlayState(mManager));
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
        spriteBatch.draw(mPlayButton, (mCamera.position.x - mPlayButton.getWidth()/2),
                (mCamera.position.y));
        spriteBatch.end();

    }

    @Override
    public void dispose() {
        mBackground.dispose();
        mPlayButton.dispose();
        System.out.println("Menu state Disposed");
    }
}
