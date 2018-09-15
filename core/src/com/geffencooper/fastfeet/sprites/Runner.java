package com.geffencooper.fastfeet.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.geffencooper.fastfeet.FastFeet;

public class Runner {
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 800;
    private static final int NUMRUNSTATES = 4;
    private int currentRunState;
    private Vector3 position;
    private Vector3 velocity;
    private Texture runnerPos1;
    private Texture runnerPos2;
    private Texture runnerPos3;
    private Texture runnerPos4;
    private Texture[] runStates;
    private Rectangle bounds1;
    private Rectangle bounds2;
    private Rectangle bounds3;
    private Rectangle bounds4;
    //private Animation runnerAnimation;

    public Runner(int x, int y)
    {
        currentRunState = 0;
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0,0,0);
        runnerPos1 = new Texture("runnerPos1.png");
        runnerPos2 = new Texture("runnerPos2.png");
        runnerPos3 = new Texture("runnerPos3.png");
        runnerPos4 = new Texture("runnerPos4.png");
        runStates = new Texture[]{runnerPos1, runnerPos2, runnerPos3, runnerPos4};
        //Texture texture = new Texture("runnerAnim.png");
       // runnerAnimation = new Animation(new TextureRegion(texture), 4, 0.05f);
        bounds1 = new Rectangle(x,y, 46, 126);
        bounds2 = new Rectangle(x,y, 59, 126);
        bounds3 = new Rectangle(x,y, 68, 126);
        bounds4 = new Rectangle(x,y, 55, 126);

    }

    public int getRunState()
    {
        System.out.println(currentRunState);
        if(currentRunState < 3)
        {
            currentRunState++;
            return currentRunState;
        }
        else
        {
            currentRunState = 0;
            return currentRunState;
        }
    }

    public Texture getRunner() {
        return runStates[getRunState()];
    }

    public Vector3 getPosition() {

        return position;
    }

    public void update(float dt)
    {
        //getRunState();
        //so velocity goes down by 15 every dt which is most likely at least 20 times per second so every second in the air is -300
        if(position.y > FastFeet.HEIGHT / 2)
        {
            velocity.add(0, GRAVITY, 0);
        }
        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y,0);
        //also the image is only rendered after this phase so even if the position is less than 240, it updates before rendering
        if(position.y < FastFeet.HEIGHT / 2)
        {
            position.y = FastFeet.HEIGHT / 2;
        }
        velocity.scl(1/dt);
        bounds1.setPosition(position.x, position.y);
        bounds2.setPosition(position.x, position.y);
        bounds3.setPosition(position.x, position.y);
        bounds4.setPosition(position.x, position.y);
    }

    public void jump()
    {
        velocity.y = 400;
    }

    public Rectangle getBounds()
    {
        switch(currentRunState)
        {
            case 0: {
                return bounds1;
            }
            case 1:
            {
                return bounds2;
            }
            case 2:
            {
                return bounds3;
            }
            case 3: {
                return bounds4;
            }
            default:
            {
                return bounds1;
            }
        }
    }

    public void dispose()
    {
        runnerPos1.dispose();
        runnerPos2.dispose();
        runnerPos3.dispose();
        runnerPos4.dispose();
    }
}
