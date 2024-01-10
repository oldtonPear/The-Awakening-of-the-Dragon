package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.coreGame.Parameters;

public class InvadersGame extends ApplicationAdapter implements InputProcessor, Observer{
	private SpriteBatch batch;
	private float camwidth = 4;
	private float camHeight;

	private Camera cam;
	private Screen currentScreen;

	private Sound music;

	private int currentKeyPressed;

	private int timepassed;
	
	@Override
	public void create () {
		Gdx.input.setInputProcessor(this);
		Parameters.setAspectRatio(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		camHeight = camwidth * Parameters.getInverseAspectRatio();
		cam = new OrthographicCamera(camwidth, camHeight);
		cam.position.set(camwidth * 0.5f, camHeight * 0.5f, 0);

		batch = new SpriteBatch();
		
		currentScreen = new Menu("Level", camwidth * Parameters.getInverseAspectRatio());

		music = ResourceLoader.getSound(ResourceEnum.MUSIC);
	}

	@Override
	public void render () {

		cam.update();

		batch.setProjectionMatrix(cam.combined);
		batch.begin();

		currentScreen.update();
		currentScreen.draw(batch);

		if(currentScreen instanceof Losing_screen || currentScreen instanceof Winning_screen){
			timepassed++;
			if(timepassed >= 100) enterMenu();
		}

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		if(currentScreen instanceof Level){
			if(keycode == 29 || keycode == 32){
				((Level) currentScreen).moveDragon(keycode);
				currentKeyPressed = keycode;
			}
			
			if(keycode == 62){
					((Level) currentScreen).spawnFireball();
				}
			else if(keycode == 66){
				((Level) currentScreen).executeSuperAttack();
				} 
		} 
		else{
			if(keycode == 62){
				enterLevel();
			}
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(currentScreen instanceof Level){
			if(keycode == currentKeyPressed){
				((Level) currentScreen).moveDragon(0);
			}
		} 
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;

	}

	@Override
	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
		return false;

	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}

	/**
	 * toggles the current state of the screen
	 * Level-Menu
	 */
	public void enterLevel(){
		if(currentScreen instanceof Menu){
			currentScreen = new Level("Level", camwidth * Parameters.getInverseAspectRatio());
			((Level) currentScreen).register(this);
			music.loop();
		} 
	}
	public void enterMenu(){
		music.stop();
		currentScreen = new Menu("Menu", camwidth * Parameters.getInverseAspectRatio());
	}

	public void winningLosingScreen(){
		music.stop();
		if(currentScreen instanceof Level){
			int score = ((Level) currentScreen).getScore();
			if(score >= 20){
				currentScreen = new Winning_screen("YOU WIN!", camwidth * Parameters.getInverseAspectRatio());
			}
			else{
				currentScreen = new Losing_screen("YOU LOSE!", camwidth * Parameters.getInverseAspectRatio());
			}
		}
	}

	/**
	 * updates observers
	 * @see Observer
	 * @param invoking's class name
	 */
	@Override
	public void updateObserver() {
		winningLosingScreen();
	}
}
