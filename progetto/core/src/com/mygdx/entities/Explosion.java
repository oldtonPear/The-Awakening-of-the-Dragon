package com.mygdx.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.coreGame.AnimatedSprite;
import com.mygdx.coreGame.GraphicObject;
import com.mygdx.coreGame.Sprite;
import com.mygdx.game.GameObject;
import com.mygdx.game.ResourceEnum;
import com.mygdx.game.ResourceLoader;

public class Explosion extends GraphicObject{

    protected AnimatedSprite sprite;

    int currentAnimationState;

    public Explosion(float planeX, float planeY){
        super();
        sprite = new AnimatedSprite(ResourceLoader.getAnimation(ResourceEnum.EXPLOSION));
        this.setX(planeX-width);
		this.setY(planeY-height);
		this.setWidth(0.5f);

        currentAnimationState = 0;
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
    public int getCurrentAnimationState() {
        return currentAnimationState;
    }

    /**
     * draws Explosion
     * @param spriteBatch
     */
    @Override
    public void draw(SpriteBatch sb) {
        sprite.draw(sb);
    }

    /**
     * updates animation
     */
    public void update(){
        sprite.update();
        currentAnimationState++;
    }
}
