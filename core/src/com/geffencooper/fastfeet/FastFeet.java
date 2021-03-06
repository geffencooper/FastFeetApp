package com.geffencooper.fastfeet;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.geffencooper.fastfeet.States.GameStateManager;
import com.geffencooper.fastfeet.States.MenuState;

public class FastFeet extends ApplicationAdapter {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 480;

	public static final String TITLE = "Fast Feet";
	private GameStateManager gsm;
	private SpriteBatch batch;
	private Music music;

	@Override
	public void create () {
		music = Gdx.audio.newMusic(Gdx.files.internal("runningSound.mp3"));
		music.setLooping(true);
		music.setVolume(0.8f);
		music.play();
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		music.dispose();
	}

}
