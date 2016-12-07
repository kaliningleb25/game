package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by kaliningleb on 05.12.16.
 */
public class Asteroid {
    private static Texture texture;
    private Vector2 position; // position.x position.y
    // private Vector2 velocity; // И потом складывам вектора (правильный вариант)
    private float speed; // 1.1f, 2.2f, 0.5f, 3.0f
    private float speedY;
    private Rectangle rect;     // Прямоугольник для проверки столкновения пули с астероидом
    private float angle;
    private int hp;
    private int hpMax;

    public int getHpMax() {
        return hpMax;
    }

    public Rectangle getRect() {
        return rect;
    }

    public Asteroid() {
        if (texture == null) texture = new Texture("asteroid-icon.png");
        //   Gdx.graphics.getWidth();  // 1280
        //   Gdx.graphics.getHeight(); // 720
        position = new Vector2(1280 + 1280 * (float)Math.random(), 720 * (float)Math.random()); // случайное расположение звезд от 0 до 1280 и от 0 до 720
        speed = 4.0f + (float)Math.random() * 5.0f;     // начальная скорость от 0 до 8 (случайная)
        rect = new Rectangle(position.x, position.y, 60,60);
        speedY = 2.0f * ((float)Math.random() - 0.5f);
        angle = (float)Math.random() * 360;
        hpMax = 5 + (int)(Math.random() * 10);
        hp = hpMax;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
        batch.draw(texture, position.x, position.y, 30, 30, 60, 60, 1.0f * (hp / 5f), 1.0f * (hp / 5f), angle, 0, 0, 60, 60 ,false, false);
    }

    public void recreate() {
        position.x = 1280 + 1280 * (float)Math.random();
        position.y = 720 * (float)Math.random();  // случайно меняется координата по y
        speed = 4.0f + (float)Math.random() * 5.0f;  // случайно меняется скорость от 5 до 10
        speedY = 2.0f * ((float)Math.random() - 0.5f);
        angle = (float)Math.random() * 360;
        hpMax = 5 + (int)(Math.random() * 10);
        hp = hpMax;
    }

    public boolean getDamage(int dmg) {
        hp -= dmg;
        if (hp <= 0) {
            recreate();
            return true;
        }
        return false;
    }

    public void update() {
        angle += speed + Math.abs(speedY) / 2;
        position.x -= speed;
        position.y += speedY;
        rect.x = position.x;
        rect.y = position.y;
        if (position.y < -60) position.y = 720;
        if (position.y > 720) position.y = -60;
        if (position.x < -60) recreate();     // если звезда залетает за левую сторону - она появляется справа

    }

    public void startGame() {
        if (texture == null) texture = new Texture("asteroid-icon.png");
        //   Gdx.graphics.getWidth();  // 1280
        //   Gdx.graphics.getHeight(); // 720
        position = new Vector2(1280 + 1280 + 1280 * (float)Math.random(), 720 * (float)Math.random()); // случайное расположение звезд от 0 до 1280 и от 0 до 720
    }
}
