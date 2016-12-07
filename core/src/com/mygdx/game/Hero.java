package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by kaliningleb on 05.12.16.
 */
public class Hero {
    private Texture texture;
    private Vector2 position;
    public float speed;
    private int fireRate;
    private int fireCounter;
    private int score;

    public Vector2 getPosition() { return position; }

    public int getScore() {
        return score;
    }

    public void addScore(int x) {
        score += x;
    }

    public Hero() {
        texture = new Texture("spaceship2.png");
        position = new Vector2(100, 100);
        speed = 5.0f;
        fireRate = 5;
        fireCounter = 0;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
 //       batch.draw(texture, position.x, position.y - 720);
 //       batch.draw(texture, position.x, position.y + 720);
    }

    public void update() {
        float mspd = speed;
        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            mspd *= 2;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            position.y += mspd;
            if (position.y > 720) position.y = -60;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            position.y -= mspd;
            if (position.y < -60) position.y = 720;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            position.x -= mspd * 0.5f;
            if (position.x < 0) position.x = 0;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            position.x += mspd;
            if (position.x > 1200) position.x = 1200;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            fireCounter++;
            if (fireCounter > fireRate) {
                fireCounter = 0;
                fire(10, -1);
                fire(10, 1);
                fire(10, 0);

            }
        }
    }

    public void fire(float vx, float vy) {
        for (int i = 0; i < MyGdxGame.bullets.length; i++) {
            if (!MyGdxGame.bullets[i].isActive()) {
                MyGdxGame.bullets[i].setup(position.x, position.y, vx, vy); // Привязка к координатам корабля
                break;
            }
        }
    }

    public void startGame() {
       position =  new Vector2(100, 100);
       score = 0;
    }
}
