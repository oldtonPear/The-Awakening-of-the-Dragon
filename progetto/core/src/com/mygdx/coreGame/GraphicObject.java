package com.mygdx.coreGame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * abstract class for graphic objects (not collidable)
 */
public abstract class GraphicObject implements Drawalble{
    protected float x;
    protected float y;
    protected float width;
    protected float height;
    protected float offsetX = 0;
    protected float offsetY = 0;

    //a bunch of getters and setters
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public float getWidth() {
        return width;
    }
    public float getHeight() {
        return height;
    }
    
    public void setX(float x) {
        this.x = x;
    }
    public void setY(float y) {
        this.y = y;
    }
    public void setOffsetX(float offsetX) {
        this.offsetX = offsetX;
    }
    public void setOffsetY(float offsetY) {
        this.offsetY = offsetY;
    }
    public void setWidth(float width) {
        this.width = width;
    }
    public void setHeight(float height) {
        this.height = height;
    }

    public abstract void draw(SpriteBatch sb);
}
