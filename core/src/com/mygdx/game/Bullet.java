package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by kaliningleb on 05.12.16.
 */
public class Bullet {
    private Vector2 position;
    private float speedX;
    private float speedY;
    private boolean active;

    public Vector2 getPosition() {
        return position;
    }

    public boolean isActive() {
        return active;
    }

    public Bullet() {
        //   Gdx.graphics.getWidth();  // 1280
        //   Gdx.graphics.getHeight(); // 720
        position = new Vector2(0,0);
        speedX = 10.0f;
        speedY = 0.0f;
        active = false;
    }

    public void destroy() {
        active = false;
    }

    public void setup(float x, float y, float vx, float vy) {
        active = true;
        position.x = x;
        position.y = y;
        speedX = vx;
        speedY = vy;
    }

    public void update() {
        position.x += speedX;
        position.y += speedY;
        if (position.x > 1280) destroy();
    }
}
