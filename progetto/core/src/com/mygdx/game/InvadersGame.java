package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.coreGame.Sprite;

public class InvadersGame extends ApplicationAdapter {
	SpriteBatch batch;
	Sprite[] img;
	Sprite player;
	float imgx;
	float imgy;

	float playerX;
	float playerY;
	boolean direzione = true;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Sprite[20];
		player = new Sprite(new Texture("badlogic.jpg"));
		playerX = 20;
		playerY = 20;
		player.setWidth(100);
		player.setY(playerY);
		
		for (int i = 0; i < img.length; i++) {
			img[i] = new Sprite(new Texture("progetto\\assets\\Idle.png"));
			img[i].setWidth(100);
			img[i].setX(i*80);
			img[i].setY((float)Math.random()*500+500);
		}
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		for (int i = 0; i < img.length; i++) {
			if(img[i] != null) {
				img[i].setY(img[i].getY()-1);
				img[i].draw(batch);
			}
			if(img[i].getY()<-80) img[i].setY((float)Math.random()*500+500);
		}
		
		if(playerX < 1000 && direzione){
			playerX++;
		}
		else{
			playerX--;
		}
		if(playerX > 500 || playerX < 20) direzione = !direzione;
		 
		player.setX(playerX);
		player.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		//img.dispose();
	}
}
