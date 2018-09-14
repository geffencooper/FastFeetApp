package com.geffencooper.fastfeet.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.geffencooper.fastfeet.FastFeet;
import com.geffencooper.fastfeet.sprites.Runner;
import com.geffencooper.fastfeet.sprites.Spike;

import java.util.Random;

public class PlayState extends State {
    private static final int SPIKE_SPACING = 125;
    private static final int SPIKE_COUNT = 8;
    private Random rand;
    private Runner runner;
    private Texture background;
    private Array<Spike> spikes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        runner = new Runner((FastFeet.WIDTH / 2) - (50), (FastFeet.HEIGHT / 2)+ 50);
        cam.setToOrtho(false, FastFeet.WIDTH , FastFeet.HEIGHT);
        background = new Texture("background.png");
        //spike = new Spike(FastFeet.HEIGHT / 2);
        spikes = new Array<Spike>();
        rand = new Random();
        for(int i = 1; i <= SPIKE_COUNT; i++)
        {
            spikes.add(new Spike(i * (SPIKE_SPACING + Spike.SPIKE_WIDTH) +400));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched())
        {
            runner.jump();
        }

    }

    int prior(int i)
    {
        if(i == 0)
        {
            return 7;
        }
        else
        {
            return i - 1;
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        runner.update(dt);
        cam.position.x = runner.getPosition().x + 80;

        //for(Spike spike : spikes)
        for(int i = 0; i < SPIKE_COUNT; i++)
        {
            if((cam.position.x - (cam.viewportWidth / 2)) > (spikes.get(i).getSpikePos().x + spikes.get(i).getSpike().getWidth()))
            {
                //System.out.print("cam position: " + (cam.position.x - (cam.viewportWidth / 2)));
                //System.out.println("spike position: " + (spike.getSpikePos().x + spike.getSpike().getWidth()));
                //System.out.println(rand.nextInt(100));
                System.out.println(i);
                spikes.get(i).reposition(spikes.get(prior(i)).getSpikePos().x + 200+rand.nextInt(200)/*((Spike.SPIKE_WIDTH + SPIKE_SPACING )) * SPIKE_COUNT +400*/);
            }

            if(spikes.get(i).collides(runner.getBounds()))
            {
                gsm.set(new PlayState(gsm));
            }
        }

        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, cam.position.x - (cam.viewportWidth / 2), -20);
        sb.draw(runner.getRunner(), runner.getPosition().x, runner.getPosition().y);
        for(Spike spike : spikes)
        {
            sb.draw(spike.getSpike(), spike.getSpikePos().x, spike.getSpikePos().y);
        }
       // sb.draw(spike.getSpike(), spike.getSpikePos().x, spike.getSpikePos().y-5);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        runner.dispose();
        for( Spike spike : spikes)
        {
            spike.dispose();
        }
        System.out.println("game state disposed");
    }
}
