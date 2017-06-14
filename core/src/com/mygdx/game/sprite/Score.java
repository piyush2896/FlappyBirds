package com.mygdx.game.sprite;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Piyush on 24-Mar-16.
 */
public class Score {

    private static final int MOVEMENT = 100;
    private static final int Y_COORDINATE = 25;
    private Vector3 mPosition;
    private BitmapFont mScoreFont;
    private int mScore;

    public Score(){
        mScore = 0;
        mScoreFont = new BitmapFont();
        mScoreFont.setColor(Color.BLUE);
        mPosition = new Vector3(50, Y_COORDINATE, 0);
    }

    public void update(float dt){
        mPosition.add(MOVEMENT * dt, 0, 0);
    }

    public void incrementScore(){
        mScore++;
    }

    public void setupBitmapFont(SpriteBatch spriteBatch){
        mScoreFont.draw(spriteBatch, "Score : " + mScore, mPosition.x, mPosition.y);
    }
}
