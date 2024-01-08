package com.mygdx.game;

import java.util.LinkedList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.coreGame.Parameters;
import com.mygdx.entities.Bullet;
import com.mygdx.entities.Dragon;
import com.mygdx.entities.Explosion;
import com.mygdx.entities.Fireball;
import com.mygdx.entities.Health;
import com.mygdx.entities.Plane;
import com.mygdx.entities.Stealth_bullet;
import com.mygdx.entities.Stealth_plane;
import com.mygdx.entities.War_bullet;
import com.mygdx.entities.War_plane;


/**
 * @author Christian Torli
 * @oldtonPear
 * Class to manage the level
 */

public class Level extends Screen implements Observed{

    private float skySpeed;
    
    private float backgroundY;

    private float width;
    private float height;
    
    private Texture skyPicture;
    
    private int nLives;

    private boolean gameContinuing;
    
    private Dragon dragon;
	private Plane[] planes;

    private LinkedList<Fireball> fireballs;
    private LinkedList<Bullet> bullets;
    private LinkedList<Explosion> explosions;

    private LinkedList<Observer> observers = new LinkedList<>();

    private Health health;

    Level(String name, float height){
        super();

        nLives = 3;

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
        fireballs = new LinkedList<>();
        explosions = new LinkedList<>();
        bullets = new LinkedList<>();

        health = new Health();

	gameContinuing = true;
    }

    @Override
    public void draw(SpriteBatch sb) {
        sb.draw(skyPicture, 0, backgroundY, width, height);
        sb.draw(skyPicture, 0, backgroundY + height, width, height);
        
		dragon.draw(sb);

        for (Fireball f : fireballs) {
            f.draw(sb);
        }

        for (Bullet bullet : bullets) {
            bullet.draw(sb);
        }
        for (Explosion expl : explosions) {
            expl.draw(sb);
        }
        
		for (int i = 0; i < planes.length; i++) {
            if(planes[i] != null){
			    planes[i].draw(sb);
                if(Math.random()<0.01f && planes[i].getY()<3 && planes[i] instanceof Stealth_plane){
                    spawnStealthBullet(planes[i].getX(), planes[i].getY());
                }
                if(Math.random()<0.005f && planes[i].getY()<3 && planes[i] instanceof War_plane){
                    spawnWarBullet(planes[i].getX(), planes[i].getY());
                }
            }
		}
        health.draw(sb);
    }

    public void update(){
        if(!health.updateNlives(nLives)) notifyObservers();

        for (Fireball f : fireballs) {
            f.update();
        }
        for (Bullet bullet : bullets) {
            bullet.update();
        }

        dragon.update();

	gameContinuing = false;
        for (int i = 0; i < planes.length; i++) {
            if(planes[i] != null){
		planes[i].update();
		gameContinuing = true;
	    }
        }
	if(!gameContinuing) notifyObservers();

        backgroundY += skySpeed;
        if(backgroundY <= -height) backgroundY += height;

        checkAndManageCollisions();

        LinkedList<Explosion> removed = new LinkedList<Explosion>();
        for (Explosion expl : explosions) {
            if(expl.getCurrentAnimationState() > 36) removed.add(expl);
            expl.update();
        }
        explosions.removeAll(removed);


    } 

    /**
     * checks for collisions between objects and manages them
     */
    public void checkAndManageCollisions(){

        for (int j = 0; j < planes.length; j++) {
            if(planes[j] != null){
                if(dragon.collidesWidth(planes[j])){
                    notifyObservers();
                } 
                
                else{
                    LinkedList<Fireball> removed = new LinkedList<Fireball>();
                    for (Fireball f : fireballs) {
                        if(f.getY() > 4){
                            removed.add(f);
                            break;
                        }
                        if(f.collidesWidth(planes[j])){
                            explosions.add(new Explosion(planes[j].getX(), planes[j].getY()));
                            planes[j] = null;
                            removed.add(f);
                            break;
                        } 
                    }
                    fireballs.removeAll(removed);
                } 
            }
        }
        LinkedList<Bullet> removed = new LinkedList<Bullet>();
            for (Bullet b : bullets) {
                if(b.getY() < -2){
                    removed.add(b);
                }
                else if(b.collidesWidth(dragon)){
                    removed.add(b);
                    if(b instanceof Stealth_bullet) nLives--;
                    else nLives = nLives -2;
                }
            }
        bullets.removeAll(removed);
    }

    /**
     * moves the dragon when pressed certain char
     * a-left d-right
     * @param character
     */
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

    /**
     * spawn a fireball at the dragon's current location
     */
    public void spawnFireball(){
        float fx = dragon.getX()+0.1f;
        float fy = dragon.getY()+0.4f;
        boolean create = true;
        Fireball newF = new Fireball(fx, fy);
        for (Fireball f : fireballs) {
            if(f.collidesWidth(newF)) create = false;
        }
        if(create) fireballs.add(newF);
    }

    /**
     * spawns a stealth bullet at the specified location
     * @param x
     * @param y
     */
    public void spawnStealthBullet(float x, float y){
        boolean create = true;
        Bullet newF = new Stealth_bullet(x, y);
        for (Bullet s : bullets) {
            if(newF.collidesWidth(s)) create = false;
        }
        if(create) bullets.add(newF);
    }

    /**
     * spawns a war bullet at the specified location
     * @param x
     * @param y
     */
    public void spawnWarBullet(float x, float y){
        boolean create = true;
        Bullet newF = new War_bullet(x, y);
        for (Bullet s : bullets) {
            if(newF.collidesWidth(s)) create = false;
        }
        if(create) bullets.add(newF);
    }

    public void register(Observer o){
        if(this.observers.size() == 0)
        observers.add(o);
    }
    public void unregister(Observer o){
        observers.remove(0);
    }
    public void notifyObservers(){
        observers.getFirst().updateObserver();
    }
}
