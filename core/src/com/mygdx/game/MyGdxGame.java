package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

import java.security.Key;

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
	BitmapFont infoFont;


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
		infoFont = new BitmapFont();

	}

	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		background.render(batch);
		fnt.draw(batch, "SCORE: " + hero.getScore(), 10, 20);

		// Столкновение героя с астероидом

		for (int i = 0; i < ASTEROIDS_COUNT; i++) {
			if (asteroids[i].getRect().contains(hero.getPosition())) {
		//		infoFont.draw(batch, "GAME OVER!!! Try again? (Y/N)", 250,250);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				hero.startGame();
				asteroids = new Asteroid[ASTEROIDS_COUNT];
				for (int j = 0; j < ASTEROIDS_COUNT; j++) {
					asteroids[j] = new Asteroid();
				}
			}
		}

		hero.render(batch);
		for (int i = 0; i < ASTEROIDS_COUNT; i++) {
			asteroids[i].render(batch);
		}
		for (int i = 0; i < BULLETS_COUNT; i++) {
			if (bullets[i].isActive())
				batch.draw(textureBullet, bullets[i].getPosition().x, bullets[i].getPosition().y);
		}

		// Выход из игры

		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			System.exit(0);
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
