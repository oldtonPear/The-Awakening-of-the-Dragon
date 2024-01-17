package com.mygdx.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.coreGame.Sprite;
import com.mygdx.game.GameObject;
import com.mygdx.game.ResourceEnum;
import com.mygdx.game.ResourceLoader;

/**
 * class to manage Chests for the super attack
 * @see Level
 */
public class Chest extends GameObject{

    private Sprite sprite;
    private boolean picked;

    public Chest(float planeX, float planeY){
        super();
        sprite = new Sprite(ResourceLoader.getTexture(ResourceEnum.CHEST));
        setWidth(0.25f);

        setRadius(0.12f);
        setBarycenter(0.12f, 0.14f);

        setX(planeX+0.13f);
        setY(planeY);
        picked = false;
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

    public boolean isPicked() {
        return picked;
    }
    public void setPicked(boolean picked) {
        this.picked = picked;
        if(picked){
            this.setX(3.6f);
		    this.setY(2.4f);
        }
    }

    
    @Override
    public void draw(SpriteBatch sb) {
        sprite.draw(sb);

        //Vector2 pb = getWorldBarycenter();
        //sb.draw(ResourceLoader.getTexture(ResourceEnum.BUBBLE), pb.x - radius, pb.y - radius, radius * 2, radius * 2);
    }

    
    @Override
    public void update(){
        if(!isPicked()) this.setY(this.getY()-0.005f);
    }
    
}