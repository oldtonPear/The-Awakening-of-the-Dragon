package com.mygdx.game;

import java.util.LinkedList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.coreGame.GraphicObject;
import com.mygdx.coreGame.Updateable;
import com.mygdx.entities.Dragon;

public abstract class GameObject extends GraphicObject implements Updateable, Observed{

    protected Vector2 barycenter;

    protected float radius;

    private LinkedList<Observer> observers = new LinkedList<>();

    public GameObject() {
        super();

        barycenter = new Vector2(0,0);
        radius = 0;
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

    public boolean collidesWidth(GameObject obj){
        Vector2 posA = this.getWorldBarycenter();
        Vector2 posB = obj.getWorldBarycenter();
        if(posA.dst(posB) < this.getRadius() + obj.getRadius()){
            return true;
        } 
        return false;
    }

    public void register(Observer o){
        if(this.observers.size() == 0)
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

    public abstract void update();

    public abstract void draw(SpriteBatch sb);

}