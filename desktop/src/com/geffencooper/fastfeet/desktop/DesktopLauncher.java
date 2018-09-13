package com.geffencooper.fastfeet.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.geffencooper.fastfeet.FastFeet;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = FastFeet.WIDTH;
		config.height = FastFeet.HEIGHT;
		config.title = FastFeet.TITLE;

		new LwjglApplication(new FastFeet(), config);
	}
}
