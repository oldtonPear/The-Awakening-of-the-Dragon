package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.coreGame.Parameters;

public class InvadersGame extends ApplicationAdapter implements InputProcessor, Observer{
	private SpriteBatch batch;
	private float camwidth = 4;
	private float camHeight;

	private Camera cam;
	private Screen currentScreen;

	private boolean isMenuActivated;
	
	@Override
	public void create () {
		Gdx.input.setInputProcessor(this);
		Parameters.setAspectRatio(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		camHeight = camwidth * Parameters.getInverseAspectRatio();
		cam = new OrthographicCamera(camwidth, camHeight);
		cam.position.set(camwidth * 0.5f, camHeight * 0.5f, 0);

		batch = new SpriteBatch();

		isMenuActivated = true;
		currentScreen = new Menu("Level", camwidth * Parameters.getInverseAspectRatio());
	}

	@Override
	public void render () {

		cam.update();

		batch.setProjectionMatrix(cam.combined);
		batch.begin();

		currentScreen.update();
		currentScreen.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		try{
			if(character == 'e' && !isMenuActivated){
				((Level) currentScreen).spawnFireball();
			}
			if(character == ' ' && isMenuActivated){
				toggleMenu();
			} 
			((Level) currentScreen).moveDragon(character);
		}catch(ClassCastException e){

		}
		
		return true;
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

	public void toggleMenu(){
		if(isMenuActivated){
			//System.out.println("entering level");
			currentScreen = new Level("Level", camwidth * Parameters.getInverseAspectRatio());
			((Level) currentScreen).register(this);
		} 
		else{
			//System.out.println("entering menu");
			currentScreen = new Menu("Menu", camwidth * Parameters.getInverseAspectRatio());
		} 
		isMenuActivated =! isMenuActivated;
	}

	@Override
	public void updateObserver(String s) {
		toggleMenu();
	}
}
