package com.geffencooper.fastfeet.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.geffencooper.fastfeet.FastFeet;
import com.geffencooper.fastfeet.sprites.Runner;

public class PlayState extends State {
    private Runner runner;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        runner = new Runner((FastFeet.WIDTH / 2) - (50), (FastFeet.HEIGHT / 2)+ 50);
        cam.setToOrtho(false, FastFeet.WIDTH , FastFeet.HEIGHT);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched())
        {
            runner.jump();
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        runner.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(runner.getRunner(), runner.getPosition().x, runner.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
