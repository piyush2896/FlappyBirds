package com.mygdx.game.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Piyush on 19-Mar-16.
 */
public class Bird {

    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;

    private Vector3 mPosition;
    private Vector3 mVelocity;

    private Texture mBird;
    private Rectangle mBounds;
    private Animation mBirdAnimation;
    private Sound mFlapSound;

    public Bird(int x, int y){
        mPosition = new Vector3(x, y, 0);
        mVelocity = new Vector3(0, 0, 0);
        mBird = new Texture("birdanimation.png");
        mBirdAnimation = new Animation(new TextureRegion(mBird), 3, 0.5f);
        mBounds = new Rectangle(x, y, mBird.getWidth() / 3, mBird.getHeight());
        mFlapSound = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    public Vector3 getPosition() {
        return mPosition;
    }

    public TextureRegion getBird() {
        return mBirdAnimation.getFrame();
    }

    public void update(float dt){
        mBirdAnimation.update(dt);
        if(mPosition.y > 0)
            mVelocity.add(0, GRAVITY, 0);
        mVelocity.scl(dt);

        mPosition.add(MOVEMENT * dt, mVelocity.y, 0);

        if(mPosition.y < 0)
            mPosition.y = 0;

        mVelocity.scl(1/dt);
        mBounds.setPosition(mPosition.x, mPosition.y);
    }

    public void jump(){
        mVelocity.y = 250;
        mFlapSound.play(0.5f);      //50% volume
    }

    public Rectangle getBounds() {
        return mBounds;
    }

    public void dispose() {
        mBird.dispose();
        mFlapSound.dispose();
    }
}
