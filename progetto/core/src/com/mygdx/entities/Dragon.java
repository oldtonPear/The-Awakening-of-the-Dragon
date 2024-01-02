package com.mygdx.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.coreGame.AnimatedSprite;
import com.mygdx.game.GameObject;
import com.mygdx.game.ResourceEnum;
import com.mygdx.game.ResourceLoader;

public class Dragon extends GameObject{
    
    private AnimatedSprite sprite;
    
    /**
     * dragon's default constructor
     * sets automatically x, y, width, radius, barycenter
     */
    public Dragon(){
        super();
        sprite = new AnimatedSprite(ResourceLoader.getAnimation(ResourceEnum.DRAGON));
        this.setX(0f);
		this.setY(0);
		this.setWidth(1f);

        setRadius(0.4f);
        setBarycenter(0.5f, 0.4f);
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

        Vector2 pb = getWorldBarycenter();
        //sb.draw(ResourceLoader.getTexture(ResourceEnum.BUBBLE), pb.x - radius, pb.y - radius, radius * 2, radius * 2);
    }

    /**
     * updates the dragon
     */
    @Override
    public void update() {
        sprite.update();
    }
}
