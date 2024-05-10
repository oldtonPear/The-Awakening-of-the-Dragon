package com.mygdx.game;

import java.util.HashMap;
import java.util.LinkedList;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.coreGame.Parameters;
import com.mygdx.entities.Bullet;
import com.mygdx.entities.Chest;
import com.mygdx.entities.Dragon;
import com.mygdx.entities.Explosion;
import com.mygdx.entities.Falling_heart;
import com.mygdx.entities.Fireball;
import com.mygdx.entities.Health;
import com.mygdx.entities.Number;
import com.mygdx.entities.Plane;
import com.mygdx.entities.ScoreFrame;
import com.mygdx.entities.Stealth_bullet;
import com.mygdx.entities.Stealth_plane;
import com.mygdx.entities.War_bullet;
import com.mygdx.entities.War_plane;
import com.mygdx.entities.Dragon.State;


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

    private Chest chest;
    private Dragon dragon;
    private Health health;
    private ScoreFrame scoreFrame;

	private Plane[] planes;
    private Falling_heart[] fallingHearts;

    private LinkedList<Fireball> fireballs;
    private LinkedList<Bullet> bullets;
    private LinkedList<Explosion> explosions;

    private int nLives;

    private boolean superAttackIsReady;
    private int score;
    private int nWins;

    private Number currentLevelNumber;

    private HashMap<Integer, LinkedList<Number>> drawedScore;

    private LinkedList<Observer> observers = new LinkedList<>();

    Level(String name, float height, int nWins){
        super();

        nLives = 3;

        this.skySpeed = -0.005f;

        this.backgroundY = 0;

        this.height = height;
        this.width = height * Parameters.getAspectRatio();

        this.skyPicture = ResourceLoader.getTexture(ResourceEnum.SKY);
        this.nWins = nWins;


        score = 0;

        planes = new Plane[20 + 10*(nWins)];
        for (int i = 0; i < planes.length; i++) {
			if(i%2 == 0){   //generates planes, one every two is Stealth type, the other is War type
                planes[i] = new Stealth_plane();
                spawnPlane(planes[i]);
            } 
            else{
                planes[i] = new War_plane();
                spawnPlane(planes[i]);
            } 
		}
		

		dragon = new Dragon();
        fireballs = new LinkedList<>();
        explosions = new LinkedList<>();
        bullets = new LinkedList<>();

        health = new Health();
        scoreFrame = new ScoreFrame();

        //initialises falling Hearts
        fallingHearts = new Falling_heart[2];
        for (int i = 0; i < fallingHearts.length; i++) {
            fallingHearts[i] = new Falling_heart();
            fallingHearts[i].setY((float)(3.5+Math.random()*50));
			fallingHearts[i].setX((float)(Math.random()*3));
        }
        superAttackIsReady = false;

        

        //filling the HashMap with numbers 1->planes.length
        drawedScore = new HashMap<>();
        for (int i = 0; i < planes.length; i++) {
            drawedScore.put(i, new LinkedList<Number>());   //inserting numbers into the HashMap
            drawedScore.get(i).add(new Number(i/10, true)); //fills decimals
            drawedScore.get(i).add(new Number(i%10, false)); //fills units
        }

        currentLevelNumber = new Number(nWins, false);
        currentLevelNumber.setX(0.5f);
    }

    /**
     * draws everything
     */
    @Override
    public void draw(SpriteBatch sb) {
        sb.draw(skyPicture, 0, backgroundY, width, height);
        sb.draw(skyPicture, 0, backgroundY + height, width, height);

        for (Fireball f : fireballs) {
            f.draw(sb);
        }

        for (Bullet bullet : bullets) {
            bullet.draw(sb);
        }
        for (Explosion expl : explosions) {
            expl.draw(sb);
        }

        for (int i = 0; i < fallingHearts.length; i++) {
            if(fallingHearts[i] != null) fallingHearts[i].draw(sb);
        }

        if(chest != null) chest.draw(sb);
        
		for (int i = 0; i < planes.length; i++) {
            if(planes[i] != null){
			    planes[i].draw(sb);
                
                //spawns a bullet when a certain condition is met
                if(Math.random()<0.01f && planes[i].getY()<3 && planes[i].getY()>1 && planes[i] instanceof Stealth_plane){
                    spawnStealthBullet(planes[i].getX(), planes[i].getY());
                }
                if(Math.random()<0.005f && planes[i].getY()<3 && planes[i].getY()>1 && planes[i] instanceof War_plane){
                    spawnWarBullet(planes[i].getX(), planes[i].getY());
                }
                //spawns a new chest if it is null and you meet certain conditions
                if(Math.random()<0.0005 && planes[i].getY()<3 && planes[i].getY() > 1 && chest == null){
                    chest = new Chest(planes[i].getX(), planes[i].getY());
                }
            }
		}

        health.draw(sb);

        dragon.draw(sb);

        scoreFrame.draw(sb);

        if(score >= planes.length) notifyObservers();
        else for (Number n : drawedScore.get(score)) {
            n.draw(sb);
        }
        currentLevelNumber.draw(sb);
    }

    /**
     * updates everything
     */
    public void update(){
        if(!health.updateNlives(nLives)) notifyObservers();

        for (Fireball f : fireballs) {
            f.update();
        }
        for (Bullet bullet : bullets) {
            bullet.update();
        }

        if(chest != null){
            chest.update();
            if(chest.getY()<-2) chest = null;
        } 

        dragon.update();

        for (int i = 0; i < planes.length; i++) {
            if(planes[i] != null){
                planes[i].update();
                if(planes[i].getY() < -0.5) spawnPlane(planes[i]);
            }
        }
        if(score >= planes.length) notifyObservers();

        for (int i = 0; i < fallingHearts.length; i++) {
            if(fallingHearts[i] != null) fallingHearts[i].update();
        }

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

        //checks for collisions o fallingHearts
        for (int i = 0; i < fallingHearts.length; i++) {
            if(fallingHearts[i] != null){
                if(fallingHearts[i].collidesWidth(dragon) && nLives < 3){
                    nLives++;
                    fallingHearts[i] = null;
                    Sound hS = ResourceLoader.getSound(ResourceEnum.CHEST_SOUND);
                    hS.play();
                }
            }
        }

        //checks for collisions on chest
        if(chest != null){
            if(chest.collidesWidth(dragon)){
                chest.setPicked(true);
                superAttackIsReady = true;
                Sound cS = ResourceLoader.getSound(ResourceEnum.CHEST_SOUND);
                cS.play();
            } 
        }

        // checks for collisions on planes
        for (int j = 0; j < planes.length; j++) {
            if(planes[j] != null){
                if(dragon.collidesWidth(planes[j])){
                    notifyObservers();
                } 
                
                else{
                    LinkedList<Fireball> removed = new LinkedList<Fireball>();
                    for (Fireball f : fireballs) {
                        if(f.getY() > 3f){
                            removed.add(f);
                            break;
                        }
                        if(f.collidesWidth(planes[j])){
                            explosions.add(new Explosion(planes[j].getX(), planes[j].getY()));
                            Sound exp = ResourceLoader.getSound(ResourceEnum.EXPLOSION_SOUND);
                            exp.play();
                            score++;
                            planes[j] = null;
                            removed.add(f);
                            break;
                        } 
                    }
                    fireballs.removeAll(removed);
                } 
            }
        }

        //checks for collisions on every bullet
        LinkedList<Bullet> removed = new LinkedList<Bullet>();
            for (Bullet b : bullets) {
                if(b.getY() < -2){
                    removed.add(b);
                }
                else if(b.collidesWidth(dragon)){
                    removed.add(b);
                    Sound pS = ResourceLoader.getSound(ResourceEnum.PROJECTILE_SOUND);
                    pS.play();
                    if(b instanceof Stealth_bullet) nLives--;
                    else{
                        if(nLives >= 2) nLives = nLives -2;
                        else nLives--;
                    } 
                }
            }
        bullets.removeAll(removed);
    }

    /**
     * moves the dragon when pressed certain char
     * a-left d-right
     * @param character
     */
    public void moveDragon(int character){
        if(character == 29){
			dragon.setCurrentState(State.MOVING_LEFT);
		}
		if(character == 32){
			dragon.setCurrentState(State.MOVING_RIGHT);
		}
        if(character == 0){
            dragon.setCurrentState(State.HOLD);
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
        newF.setRadius(0.9f);
        newF.setBarycenter(0.0f, 0f);
        for (Fireball f : fireballs) {
            if(f.collidesWidth(newF)) create = false;
        }
        newF.setBarycenter(0.8f, 0f);
        for (Fireball f : fireballs) {
            if(f.collidesWidth(newF)) create = false;
        }
        if(create){
            newF.setRadius(0.1f);
            newF.setBarycenter(0.4f, 0.45f);
            Sound fs = ResourceLoader.getSound(ResourceEnum.FIREBALL_SOUND);
            fs.play();
            fireballs.add(newF);
            
        } 
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

    /**
     * sets the coordinates of the given Plane
     * @param p
     */
    public void spawnPlane(Plane p){
        
        p.setX((float)(Math.random()*3));
        p.setY((float)(4+Math.random()*(20+10f*nWins-score)));
        boolean insert = true;
        while(insert){
            for (int i = 0; i < planes.length; i++) {
                if(planes[i] != null && planes[i] != p){
                    if(p.collidesWidth(planes[i])){
                        insert = false;
                        break;
                        
                    }
                }
            }
            if(insert) break;
            if(!insert){
                p.setY((float)(4+Math.random()*(20+10f*nWins-score)));
                insert = true;
            } 
        }
    }

    /**
     * shoots the Super Attack
     */
    public void executeSuperAttack(){
        if(superAttackIsReady){
            superAttackIsReady = false;
            chest = null;

        bullets.clear();  

        for(int i = 0; i < 40; i++){
            fireballs.add(new Fireball(0.1f*i - 0.35f, dragon.getY()+0.4f));
        }
        Sound fs = ResourceLoader.getSound(ResourceEnum.FIREBALL_SOUND);
        fs.play();
        }
    }

    public int nPlanes() {
        return planes.length;
    }

    public int getScore() {
        return score;
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