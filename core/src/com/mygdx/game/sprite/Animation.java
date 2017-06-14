package com.mygdx.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Piyush on 23-Mar-16.
 */
public class Animation {

    private Array<TextureRegion> mFrames;
    private float mMaxFrameTime;
    private float mCurrentFrameTime;
    private int mFrameCount;        // no of frames
    private int mFrame;         //current frame

    public Animation(TextureRegion region, int frameCount, float cycleTime) {
        mFrames = new Array<TextureRegion>();
        mFrameCount = frameCount;
        mMaxFrameTime = cycleTime / mFrameCount;
        mFrame = 0;
        int frameWidth = region.getRegionWidth() / frameCount;
        for(int i = 0; i < frameCount; i++){
            mFrames.add(new TextureRegion(region, i * frameWidth, 0,
                    frameWidth, region.getRegionHeight()));
        }
    }

    public void update(float dt){
        mCurrentFrameTime += dt;
        if (mCurrentFrameTime > mMaxFrameTime){
            mFrame++;
            mCurrentFrameTime = 0;
        }

        if(mFrame >= mFrameCount){
            mFrame = 0;
        }
    }

    public TextureRegion getFrame(){
        return mFrames.get(mFrame);
    }
}
