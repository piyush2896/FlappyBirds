package com.mygdx.game.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.FlappyBirds;
import com.mygdx.game.sprite.Bird;
import com.mygdx.game.sprite.Obstacle;
import com.mygdx.game.sprite.Score;

/**
 * Created by Piyush on 19-Mar-16.
 */
public class PlayState extends State {

    private static final int OBSTACLE_SPACING = 125;
    private static final int OBSTACLE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -50;

    private Bird mBird;
    private Texture mBackground;
    private Texture mGround;
    private Vector2 mGroundPos1, mGroundPos2;
    private Score mScore;

    private Array<Obstacle> mObstacles;

    protected PlayState(GameStateManager manager) {
        super(manager);
        mBird = new Bird(50, 300);
        mCamera.setToOrtho(false, FlappyBirds.WIDTH / 2, FlappyBirds.HEIGHT / 2);
        mBackground = new Texture("bg.png");
        mGround = new Texture("ground.png");
        mGroundPos1 = new Vector2(mCamera.position.x - mCamera.viewportWidth / 2, GROUND_Y_OFFSET);
        mGroundPos2 = new Vector2((mCamera.position.x - mCamera.viewportWidth / 2) +
                mGround.getWidth(), GROUND_Y_OFFSET);

        mObstacles = new Array<Obstacle>();

        for (int i = 1; i <= OBSTACLE_COUNT; i++){
            mObstacles.add(new Obstacle(i * (OBSTACLE_SPACING + Obstacle.OBSTACLE_WIDTH)));
        }

        mScore = new Score();
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            mBird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        mBird.update(dt);
        mScore.update(dt);

        mCamera.position.x = mBird.getPosition().x + 80;

        for (Obstacle obstacle : mObstacles){
            if(mCamera.position.x - (mCamera.viewportWidth / 2) >
                    obstacle.getPosTopObs().x + obstacle.getTopObstacle().getWidth()){
                obstacle.reposition(obstacle.getPosTopObs().x +
                        (Obstacle.OBSTACLE_WIDTH + OBSTACLE_SPACING) * OBSTACLE_COUNT);
            }

            if(obstacle.collides(mBird.getBounds())){
                mManager.set(new GameOverState(mManager));
                break;
            }

            if(obstacle.crossed(mBird.getBounds())){
                mScore.incrementScore();
            }
        }

        if(mBird.getPosition().y <= mGround.getHeight() + GROUND_Y_OFFSET){
            mManager.set(new GameOverState(mManager));
        }

        mCamera.update();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(mCamera.combined);
        spriteBatch.begin();
        spriteBatch.draw(mBackground, mCamera.position.x - mCamera.viewportWidth / 2, 0);
        spriteBatch.draw(mBird.getBird(), mBird.getPosition().x, mBird.getPosition().y);
        for (Obstacle obstacle : mObstacles){
            spriteBatch.draw(obstacle.getTopObstacle(),
                    obstacle.getPosTopObs().x, obstacle.getPosTopObs().y);
            spriteBatch.draw(obstacle.getBottomObstacle(),
                    obstacle.getPosBottomObs().x, obstacle.getPosBottomObs().y);
        }
        spriteBatch.draw(mGround, mGroundPos1.x, mGroundPos1.y);
        spriteBatch.draw(mGround, mGroundPos2.x, mGroundPos2.y);
        mScore.setupBitmapFont(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        mBackground.dispose();
        mBird.dispose();
        for(Obstacle obstacle : mObstacles){
            obstacle.dispose();
        }
        mGround.dispose();
        System.out.println("Play state Disposed");
    }

    private void updateGround(){
        if(mCamera.position.x - mCamera.viewportWidth / 2 >  mGroundPos1.x + mGround.getWidth()){
            mGroundPos1.add(mGround.getWidth() * 2, 0);
        }
        if(mCamera.position.x - mCamera.viewportWidth / 2 > mGroundPos2.x + mGround.getWidth()){
            mGroundPos2.add(mGround.getWidth() * 2, 0);
        }
    }
}
