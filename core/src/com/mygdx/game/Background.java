package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import org.omg.CORBA.MARSHAL;

/**
 * Created by kaliningleb on 04.12.16.
 */
public class Background {
    class Star {
        private Vector2 position; // position.x position.y
        private float speed; // 1.1f, 2.2f, 0.5f, 3.0f

        public Star() {
         //   Gdx.graphics.getWidth();  // 1280
         //   Gdx.graphics.getHeight(); // 720
            position = new Vector2(1280 * (float)Math.random(), 720 * (float)Math.random()); // случайное расположение звезд от 0 до 1280 и от 0 до 720
            speed = 1.0f + (float)Math.random() * 8.0f;     // начальная скорость от 0 до 8 (случайная)
        }

        public void update() {
            position.x -= speed;
            if (position.x < -20) {     // если звезда залетает за левую сторону - она появляется справа
                position.x = 1280;
                position.y = 720 * (float)Math.random();  // случайно меняется координата по y
                speed = 1.0f + (float)Math.random() * 8.0f;  // случайно меняется скорость от 0 до 8
            }
        }
    }

    private Texture textureStar;
    private Texture texture;
    private final int STARS_COUNT = 300;
    private Star[] stars;

    public Background() {
        texture = new Texture("background_black.jpg");
        textureStar = new Texture("star3.png");
        stars = new Star[STARS_COUNT];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star();
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, 0, 0);
        for (int i = 0; i < stars.length; i++) {
            batch.draw(textureStar, stars[i].position.x, stars[i].position.y);
            if (Math.random() < 0.05) {
                batch.draw(textureStar, stars[i].position.x, stars[i].position.y, 3,3,4,4,1.5f,1.5f,0.0f,0,0,12,12,false,false);
            }
        }
    }

    public void update() {
        for (int i = 0; i < stars.length; i++) {
            stars[i].update();
        }
    }


}
