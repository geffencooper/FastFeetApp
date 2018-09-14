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
    private Vector3 position;
    private Vector3 velocity;
    private Texture runner;
    private Rectangle bounds1;
    private Rectangle bounds2;
    private Rectangle bounds3;
    private Rectangle bounds4;
    private Animation runnerAnimation;

    public Runner(int x, int y)
    {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0,0,0);
        //runner = new Texture("runPos1.png");
        Texture texture = new Texture("runnerAnim.png");
        runnerAnimation = new Animation(new TextureRegion(texture), 4, 0.05f);
        bounds1 = new Rectangle(x,y, 46, 126);
        bounds2 = new Rectangle(x,y, 59, 126);
        bounds3 = new Rectangle(x,y, 68, 126);
        bounds4 = new Rectangle(x,y, 55, 126);

    }

    public TextureRegion getRunner() {
        return runnerAnimation.getFrame();
    }

    public Vector3 getPosition() {

        return position;
    }

    public void update(float dt)
    {
        runnerAnimation.update(dt);
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
    }

    public void jump()
    {
        velocity.y = 400;
    }

    public Rectangle getBounds()
    {
        return bounds1;
    }

    public void dispose()
    {
        runner.dispose();
    }
}
