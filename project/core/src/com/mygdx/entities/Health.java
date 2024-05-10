package com.mygdx.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.coreGame.GraphicObject;
import com.mygdx.coreGame.Sprite;
import com.mygdx.game.ResourceEnum;
import com.mygdx.game.ResourceLoader;

/**
 * class to manage health
 * @see Level
 */
public class Health extends GraphicObject {

    private Sprite sprite;

    public Health(){
        super();
        sprite = new Sprite(ResourceLoader.getTexture(ResourceEnum.HEALTH_3));
        this.setX(3.2f);
		this.setY(2.7f);
		this.setWidth(0.8f);
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
    }

    /**
     * updates current Sprite
     * @return true if everything is fine
     * @return false if the number of lives is 0
     */
    public boolean updateNlives(int numberOfLives){
        switch (numberOfLives) {
            case 0:
                return false;
            case 1:
                sprite = new Sprite(ResourceLoader.getTexture(ResourceEnum.HEALTH_1));
                break;

            case 2:
                sprite = new Sprite(ResourceLoader.getTexture(ResourceEnum.HEALTH_2));
                break;

            case 3:
                sprite = new Sprite(ResourceLoader.getTexture(ResourceEnum.HEALTH_3));
                break;
        
            default:
                break;
        }
        this.setX(3.2f);
		this.setY(2.7f);
		this.setWidth(0.8f);
        return true;
    }
}
