package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.FlappyBirds;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = FlappyBirds.WIDTH;
		config.height = FlappyBirds.HEIGHT;
		config.title = FlappyBirds.TITLE;
		new LwjglApplication(new FlappyBirds(), config);
	}
}
