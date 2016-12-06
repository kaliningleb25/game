package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// openGL

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Background background;
	Hero hero;
	private final int ASTEROIDS_COUNT = 24;
	Asteroid[] asteroids;
	private final int BULLETS_COUNT = 180;
	public static Bullet[] bullets;
	private Texture textureBullet;
	BitmapFont fnt;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Background();
		hero = new Hero();
		asteroids = new Asteroid[ASTEROIDS_COUNT];
		for (int i = 0; i < ASTEROIDS_COUNT; i++) {
			asteroids[i] = new Asteroid();
		}
		bullets = new Bullet[BULLETS_COUNT];
		for (int i = 0; i < BULLETS_COUNT; i++) {
			bullets[i] = new Bullet();
		}
		textureBullet = new Texture("bullet.png");
		fnt = new BitmapFont();
	}

	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		background.render(batch);
		fnt.draw(batch, "SCORE: " + hero.getScore(), 10, 20);
		hero.render(batch);
		for (int i = 0; i < ASTEROIDS_COUNT; i++) {
			asteroids[i].render(batch);
		}
		for (int i = 0; i < BULLETS_COUNT; i++) {
			if (bullets[i].isActive())
				batch.draw(textureBullet, bullets[i].getPosition().x, bullets[i].getPosition().y);
		}
		batch.end();
	}

	public void update() {
		background.update();
		hero.update();
		for (int i = 0; i < ASTEROIDS_COUNT; i++) {
			asteroids[i].update();
		}
		for (int i = 0; i < BULLETS_COUNT; i++) {
			if (bullets[i].isActive()) {
				bullets[i].update();
				for (int j = 0; j < ASTEROIDS_COUNT; j++) {
					if (asteroids[j].getRect().contains(bullets[i].getPosition())) {
						if (asteroids[j].getDamage(1)) {
							hero.addScore(asteroids[j].getHpMax() * 100);
						}
						bullets[i].destroy();
						break;
					}

				}
			}
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
