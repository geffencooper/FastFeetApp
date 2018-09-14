package com.geffencooper.fastfeet.States;

import java.util.Stack;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class GameStateManager//keeps track of what state the game is in
{
    private Stack<State> states;
    //we will create a stack of states, the state at the top will be the current state

    public GameStateManager()
    {
        states = new Stack<State>();
    }

    public void push(State state)
    {
        states.push(state);
    }

    public void pop()
    {
        states.pop().dispose();
    }

    public void set(State state)
    {
        states.pop().dispose();
        states.push(state);
    }

    public void update(float dt)
    {
        states.peek().update(dt);
        //states.peek returns the state at the top of the stack
    }

    public void render(SpriteBatch sb)
    {
        states.peek().render(sb);
    }
}
