package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.coreGame.GraphicObject;
import com.mygdx.coreGame.Updateable;

public abstract class GameObject extends GraphicObject implements Updateable{

    protected Vector2 velocity;

    protected Vector2 barycentre;

    protected float radius;

    protected Vector2 acceleration;

    public GameObject() {
        super();

        velocity = new Vector2(0,0);
        barycentre = new Vector2(0,0);
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
    public Vector2 getWorldBarycentre(){
        return new Vector2(x + barycentre.x, y + barycentre.y);

    }

    public Vector2 getbarycentre() {
        return new Vector2(barycentre);
    }
    public void setbarycentre(Vector2 barycentre) {
        this.barycentre.x = barycentre.x;
        this.barycentre.y = barycentre.y;
    }
    public void setbarycentre(float x, float y) {
        this.barycentre.x = x;
        this.barycentre.y = y;
    }

    public float getRadius() {
        return radius;
    }
    public void setRadius(float radius) {
        this.radius = radius;
    }

    public Vector2 getacceleration() {
        return new Vector2(acceleration);
    }
    public void setacceleration(Vector2 acceleration) {
        this.acceleration.x = acceleration.x;
        this.acceleration.y = acceleration.y;
    }
    public void setacceleration(float x, float y) {
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
        Vector2 posA = this.getWorldBarycentre();
        Vector2 posB = obj.getWorldBarycentre();
        if(posA.dst(posB) < this.getRadius() + obj.getRadius()) return true;
        return false;
    }

    public abstract void manageCollisionWith(GameObject obj);

    public abstract void update();

    public abstract void draw(SpriteBatch sb);

}