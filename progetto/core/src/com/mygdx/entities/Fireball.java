package com.mygdx.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.coreGame.AnimatedSprite;
import com.mygdx.game.GameObject;
import com.mygdx.game.ResourceEnum;
import com.mygdx.game.ResourceLoader;

public class Fireball extends GameObject{
    
    private AnimatedSprite sprite;
    
    /**
     * dragon's default constructor
     * sets automatically x, y, width, radius, barycenter
     */
    public Fireball(float x, float y){
        super();
        sprite = new AnimatedSprite(ResourceLoader.getAnimation(ResourceEnum.FIREBALL));
        this.setX(x);
		this.setY(y);
		this.setWidth(0.8f);

        setRadius(0.15f);
        setBarycenter(0.4f, 0.4f);
    }
    

    @Override
    public void setX(float x) {
        super.setX(x);
        sprite.setX(x);
    }
    @Override
    public void setY(float y) {
        super.setY(y);
        sprite.setY(y);
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
     * draws the dragon
     * @param spriteBatch
     */
    @Override
    public void draw(SpriteBatch sb) {
        sprite.draw(sb);

        //Vector2 pb = getWorldBarycenter();
        //sb.draw(ResourceLoader.getTexture(ResourceEnum.BUBBLE), pb.x - radius, pb.y - radius, radius * 2, radius * 2);
    }

    /**
     * updates the dragon
     */
    @Override
    public void update() {
        sprite.update();
        this.setY(this.getY()+0.01f);
    }
}
