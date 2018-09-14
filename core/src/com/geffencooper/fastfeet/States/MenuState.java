package com.geffencooper.fastfeet.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.geffencooper.fastfeet.FastFeet;

public class MenuState extends State
{
    private Texture background;
    private Texture playButton;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("background.png");
        playButton = new Texture("playButton.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched())
        {
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, FastFeet.WIDTH, FastFeet.HEIGHT);
        sb.draw(playButton, (FastFeet.WIDTH / 2) - (playButton.getWidth() / 2), (FastFeet.HEIGHT / 2) - (playButton.getHeight() / 2));
        sb.end();

    }

    @Override
    public void dispose() {
       background.dispose();
       playButton.dispose();
       System.out.println("menu state disposed");
    }
}
