package com.mygdx.game.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Piyush on 19-Mar-16.
 */
public class Obstacle {

    private static final int FLUCTUATION = 130;
    private static final int OBSTACLE_GAP = 100;
    private static final int LOWEST_OPENING = 100;
    public static final int OBSTACLE_WIDTH = 52;

    private Texture mTopObstacle;
    private Texture mBottomObstacle;
    private Vector2 mPosTopObs;
    private Vector2 mPosBottomObs;
    private Random rand;
    private Rectangle mBoundsTop;
    private Rectangle mBoundsBottom;

    public Obstacle(float x){
        mTopObstacle = new Texture("obstacleTop.png");
        mBottomObstacle = new Texture("obstacleBottom.png");
        rand = new Random();

        mPosTopObs = new Vector2(x, rand.nextInt(FLUCTUATION) + OBSTACLE_GAP + LOWEST_OPENING);
        mPosBottomObs = new Vector2(x, mPosTopObs.y - OBSTACLE_GAP - mBottomObstacle.getHeight());

        mBoundsTop = new Rectangle(mPosTopObs.x, mPosTopObs.y,
                mTopObstacle.getWidth(), mTopObstacle.getHeight());
        mBoundsBottom = new Rectangle(mPosBottomObs.x, mPosBottomObs.y,
                mBottomObstacle.getWidth(), mBottomObstacle.getHeight());

    }

    public Texture getTopObstacle() {
        return mTopObstacle;
    }

    public Texture getBottomObstacle() {
        return mBottomObstacle;
    }

    public Vector2 getPosTopObs() {
        return mPosTopObs;
    }

    public Vector2 getPosBottomObs() {
        return mPosBottomObs;
    }

    public void reposition(float x){
        mPosTopObs.set(x, rand.nextInt(FLUCTUATION) + OBSTACLE_GAP + LOWEST_OPENING);
        mPosBottomObs.set(x, mPosTopObs.y - OBSTACLE_GAP - mBottomObstacle.getHeight());

        mBoundsTop.setPosition(mPosTopObs.x, mPosTopObs.y);
        mBoundsBottom.setPosition(mPosBottomObs.x, mPosBottomObs.y);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(mBoundsTop) || player.overlaps(mBoundsBottom);
    }

    public boolean crossed(Rectangle player){
        return player.x > mBoundsTop.x + mTopObstacle.getWidth();
    }

    public void dispose() {
        mTopObstacle.dispose();
        mBottomObstacle.dispose();
    }
}
