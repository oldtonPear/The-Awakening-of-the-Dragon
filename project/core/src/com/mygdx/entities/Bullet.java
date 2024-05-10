package com.mygdx.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.coreGame.Sprite;
import com.mygdx.game.GameObject;

/**
 * class to manage Bullets spawnd by planes
 * abstract in order to be specialized
 * @see War_bullet
 * @see Stealth_bullet
 * @see Plane
 * @see Level
 */
public abstract class Bullet extends GameObject{

    protected Sprite sprite;

    public Bullet(float planeX, float planeY){
        super();
    }

    @Override
    public void setX(float x) {
        super.setX(x);
        sprite.setX(x);
        
    }
    @Override
    public void setY(float y) {
        sprite.setY(y);
        super.setY(y);
        
    }
    @Override
    public void setWidth(float width) {
        super.setWidth(width);
        sprite.setWidth(width);
    }
    @Override
    public void setHeight(float height) {
        super.setHeight(height);
        sprite.setHeight(height);
    }

    
    @Override
    public void draw(SpriteBatch sb) {
        sprite.draw(sb);

        //Vector2 pb = getWorldBarycenter();
        //sb.draw(ResourceLoader.getTexture(ResourceEnum.BUBBLE), pb.x - radius, pb.y - radius, radius * 2, radius * 2);
    }

    
    @Override
    public void update(){
        this.setY(this.getY()-0.015f);
    }
    
}