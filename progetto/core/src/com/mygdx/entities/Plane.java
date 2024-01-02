package com.mygdx.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.coreGame.Sprite;
import com.mygdx.game.GameObject;
import com.mygdx.game.ResourceEnum;
import com.mygdx.game.ResourceLoader;

public abstract class Plane extends GameObject{

    protected Sprite sprite;

    Plane(){
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

    /**
     * draws plane
     * @param spriteBatch
     */
    @Override
    public void draw(SpriteBatch sb) {
        sprite.draw(sb);

        Vector2 pb = getWorldBarycenter();
        //sb.draw(ResourceLoader.getTexture(ResourceEnum.BUBBLE), pb.x - radius, pb.y - radius, radius * 2, radius * 2);
    }

    /**
     * updates plane
     */
    @Override
    public void update(){
        if(this.getY()<-2)this.setY((float)(3.5+Math.random()*30));
        else this.setY(this.getY()-0.01f);
    }
}
