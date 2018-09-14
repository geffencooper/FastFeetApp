package com.geffencooper.fastfeet.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.geffencooper.fastfeet.FastFeet;

import java.util.Random;

public class Spike
{
    public static final int SPIKE_WIDTH = 80;
    private static final int FLUCTUATION = 200;
    private static int SPIKEGAP = 100;
    private static int MINSCREENPOS = 400;
    private Texture spike;
    private Vector2 spikePos;
    private Random rand;
    private Rectangle bounds;

    public Spike(float x)
    {
        spike = new Texture("spike.png");
        rand = new Random();

        spikePos = new Vector2(x,(FastFeet.HEIGHT / 2)-5);

        bounds = new Rectangle(spikePos.x, spikePos.y, spike.getWidth(), spike.getHeight());
    }

    public Texture getSpike() {
        return spike;
    }

    public Vector2 getSpikePos() {
        return spikePos;
    }

    public void reposition(float x)
    {
        spikePos.set(x, FastFeet.HEIGHT / 2);
        bounds.setPosition(spikePos.x, spikePos.y);
    }

    public boolean collides(Rectangle player)
    {
        return player.overlaps(bounds);
    }

    public void dispose()
    {
        spike.dispose();
    }
}
