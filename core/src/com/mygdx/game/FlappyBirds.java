package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.MenuState;

public class FlappyBirds extends ApplicationAdapter {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final String TITLE = "Flappy Birds";

    private GameStateManager mManager;
    private SpriteBatch mBatch;
	private Music mMusic;
	
	@Override
	public void create () {
		mBatch = new SpriteBatch();
        mManager = new GameStateManager();
		mMusic = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		mMusic.setLooping(true);
		mMusic.setVolume(0.1f);		//10% volume
		mMusic.play();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        mManager.push(new MenuState(mManager));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		mManager.update(Gdx.graphics.getDeltaTime());
        mManager.render(mBatch);
	}

	@Override
	public void dispose() {
		super.dispose();
		mMusic.dispose();
	}
}
