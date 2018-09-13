package com.geffencooper.fastfeet.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.geffencooper.fastfeet.FastFeet;

public class Runner {
    private static final int GRAVITY = -15;
    private Vector3 position;
    private Vector3 velocity;
    private Texture runner;

    public Runner(int x, int y)
    {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0,0,0);
        runner = new Texture("runPos1.png");
    }

    public Texture getRunner() {
        return runner;
    }

    public Vector3 getPosition() {

        return position;
    }

    public void update(float dt)
    {
        //if the guy is in the air then gravity affects him
        if(position.y > FastFeet.HEIGHT / 2)
        {
            velocity.add(0, GRAVITY, 0);
            velocity.scl(dt);
            position.add(0, velocity.y, 0);
            velocity.scl(1/dt);

        }
        //otherwise his position is the ground level
        else if(position.y == FastFeet.HEIGHT / 2)
        {
            position.y = FastFeet.HEIGHT / 2;
           // velocity.scl(dt);
           // position.add(0, velocity.y, 0);
           // velocity.scl(1/dt);
        }

    }

    public void jump()
    {
        //if jump is clicked then update position, we can't wait for the update
        //method because it sets position to 0, if we are on ground, before checking velocity
        //we can't check velocity first because we must make sure the player is always above ground
        velocity.y = 250;
        velocity.scl(Gdx.graphics.getDeltaTime());
        position.add(0, velocity.y,0);
        velocity.scl(1/Gdx.graphics.getDeltaTime());
    }
}
