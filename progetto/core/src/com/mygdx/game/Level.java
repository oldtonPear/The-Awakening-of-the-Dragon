package com.mygdx.game;

import java.util.LinkedList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.coreGame.Parameters;
import com.mygdx.entities.Dragon;
import com.mygdx.entities.Fireball;
import com.mygdx.entities.Plane;
import com.mygdx.entities.Stealth_plane;
import com.mygdx.entities.War_plane;

public class Level extends Screen implements Observed{

    private float skySpeed;
    
    private float backgroundY;

    private float width;
    private float height;
    
    private Texture skyPicture;
    
    
    private Dragon dragon;
	private Plane[] planes;

    private LinkedList<Fireball> fireball;

    private LinkedList<Observer> observers = new LinkedList<>();

    Level(String name, float height){
        super();

        this.skySpeed = -0.005f;

        this.backgroundY = 0;

        this.height = height;
        this.width = height * Parameters.getAspectRatio();

        this.skyPicture = ResourceLoader.getTexture(ResourceEnum.SKY);
        
        planes = new Plane[20];
		for (int i = 0; i < planes.length; i++) {

			if(i%2==0) planes[i] = new Stealth_plane();
            else planes[i] = new War_plane();

            planes[i].setY((float)(3.5+Math.random()*30));
			planes[i].setX((float)(Math.random()*3));
			
		}
		dragon = new Dragon();
        fireball = new LinkedList<>();
        
    }

    public float getSpeed() {
        return skySpeed;
    }
    public void setSpeed(float speed) {
        this.skySpeed = speed;
    }

    @Override
    public void draw(SpriteBatch sb) {
        sb.draw(skyPicture, 0, backgroundY, width, height);
        sb.draw(skyPicture, 0, backgroundY + height, width, height);
        
		dragon.draw(sb);

        for (Fireball f : fireball) {
            f.draw(sb);
        }
        
		for (int i = 0; i < planes.length; i++) {
            if(planes[i] != null){
                planes[i].update();
			    planes[i].draw(sb);
            }
		}
    }

    public void update(){
        for (Fireball f : fireball) {
            f.update();
        }
        dragon.update();
        backgroundY += skySpeed;
        if(backgroundY <= -height) backgroundY += height;

        for (int j = 0; j < planes.length; j++) {
            if(planes[j] != null){
                if(dragon.collidesWidth(planes[j])){
                    notifyObservers(this.getClass().getSimpleName());
                } 
                
                else{
                    LinkedList<Fireball> removed = new LinkedList<Fireball>();
                    for (Fireball f : fireball) {
                        if(f.collidesWidth(planes[j]) || (f.getY() > 4)){
                            removed.add(f);
                            planes[j] = null;
                            break;
                        }
                    }
                    fireball.removeAll(removed);
                } 
            }
        }
    } 

    public void moveDragon(char character){
        if(character == 'a'){
			if(dragon.getX() > 0f)
			dragon.setX(dragon.getX() - 0.05f);
		}
		if(character == 'd'){
			if(dragon.getX() < 4-dragon.getWidth())
			dragon.setX(dragon.getX() + 0.05f);
		}
    }
    public void spawnFireball(){
        float fx = dragon.getX()+0.1f;
        float fy = dragon.getY()+0.4f;
        boolean create = true;
        Fireball newF = new Fireball(fx, fy);
        for (Fireball f : fireball) {
            if(f.collidesWidth(newF)) create = false;
        }
        if(create) fireball.add(newF);
    }
    public void despawnFireball(Fireball f){
        fireball.remove(f);
    }

    public void register(Observer o){
        if(this.observers.size() == 0)
        observers.add(o);
    }
    public void unregister(Observer o){
        observers.remove(0);
    }

    public void notifyObservers(String s){
        observers.getFirst().updateObserver(s);
    }
}
