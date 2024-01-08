package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.coreGame.GraphicObject;
import com.mygdx.coreGame.Updateable;

public abstract class GameObject extends GraphicObject implements Updateable{

    protected Vector2 barycenter;

    protected float radius;

    public GameObject() {
        super();

        barycenter = new Vector2(0,0);
        radius = 0;
    }

    /**
     * @return the position of the barycenter relative to the surroundings
     */
    public Vector2 getWorldBarycenter(){
        return new Vector2(x + barycenter.x, y + barycenter.y);
    }

    /**
     * @return the local barycenter relative to the object
     */
    public Vector2 getBarycenter() {
        return new Vector2(barycenter);
    }

    /**
     * sets the local barycenter using a Vector2 type
     * @param barycenter
     */
    public void setBarycenter(Vector2 barycenter) {
        this.barycenter.x = barycenter.x;
        this.barycenter.y = barycenter.y;
    }

    /**
     * sets the local barycenter using two coordinates
     * @param x
     * @param y
     */
    public void setBarycenter(float x, float y) {
        this.barycenter.x = x;
        this.barycenter.y = y;
    }

    /**
     * @return the radius of the GameObject
     */
    public float getRadius() {
        return radius;
    }

    /**
     * sets the radius
     * @param radius
     */
    public void setRadius(float radius) {
        this.radius = radius;
    }

    /**
     * checks if obj collides with "this"
     * @param obj
     * @return true if a collision happens
     * @return false if there is no collisions
     */
    public boolean collidesWidth(GameObject obj){
        Vector2 posA = this.getWorldBarycenter();
        Vector2 posB = obj.getWorldBarycenter();
        if(posA.dst(posB) < this.getRadius() + obj.getRadius()){
            return true;
        } 
        return false;
    }
    
    /**
     * draws the GameObject
     * @see Drawable
     */
    public abstract void draw(SpriteBatch sb);
    
    /**
     * updates the GameObject
     * @see Updateable
     */
    public abstract void update();
}