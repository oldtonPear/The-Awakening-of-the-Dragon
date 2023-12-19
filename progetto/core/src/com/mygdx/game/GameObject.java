package com.mygdx.game;

import java.util.LinkedList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.coreGame.GraphicObject;
import com.mygdx.coreGame.Updateable;
import com.mygdx.entities.Dragon;

public abstract class GameObject extends GraphicObject implements Updateable, Observed{

    protected Vector2 velocity;

    protected Vector2 barycenter;

    protected float radius;

    protected Vector2 acceleration;

    private LinkedList<Observer> observers = new LinkedList<>();

    public GameObject() {
        super();

        velocity = new Vector2(0,0);
        barycenter = new Vector2(0,0);
        acceleration = new Vector2(0,0);
        
        radius = 0;
    }

    public Vector2 getVelocity() {
        return new Vector2(velocity);
    }
    public void setVelocity(Vector2 velocity) {
        this.velocity.x = velocity.x;
        this.velocity.y = velocity.y;
    }
    public void setVelocity(float x, float y) {
        this.velocity.x = x;
        this.velocity.y = y;
    }

    /**
     * La posizione del baricentro nelle coordinate del mondo
     * @return
     */
    public Vector2 getWorldBarycenter(){
        return new Vector2(x + barycenter.x, y + barycenter.y);

    }

    public Vector2 getBarycenter() {
        return new Vector2(barycenter);
    }
    public void setBarycenter(Vector2 barycenter) {
        this.barycenter.x = barycenter.x;
        this.barycenter.y = barycenter.y;
    }
    public void setBarycenter(float x, float y) {
        this.barycenter.x = x;
        this.barycenter.y = y;
    }

    public float getRadius() {
        return radius;
    }
    public void setRadius(float radius) {
        this.radius = radius;
    }

    public Vector2 getAccelretion() {
        return new Vector2(acceleration);
    }
    public void setAcceleration(Vector2 acceleration) {
        this.acceleration.x = acceleration.x;
        this.acceleration.y = acceleration.y;
    }
    public void setAcceleration(float x, float y) {
        this.acceleration.x = x;
        this.acceleration.y = y;
    }

    protected void updatePhysic(){
        velocity.x += acceleration.x;
        velocity.y += acceleration.y;
        setX(x + velocity.x);
        setY(y + velocity.y);
    }

    public boolean collidesWidth(GameObject obj){
        Vector2 posA = this.getBarycenter();
        Vector2 posB = obj.getBarycenter();
        if(posA.dst(posB) < this.getRadius() + obj.getRadius()) return true;
        return false;
    }

    public void register(Observer o){
        observers.add(o);
    }
    public void unregister(Observer o){
        observers.remove(0);
    }

    public void notifyObservers(String s){
        if(this instanceof Dragon){
            observers.getFirst().updateObserver(this.getClass().getSimpleName());
        }
    }

    public abstract void manageCollisionWith(GameObject obj);

    public abstract void update();

    public abstract void draw(SpriteBatch sb);

}