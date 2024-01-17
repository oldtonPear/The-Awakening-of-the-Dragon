package com.mygdx.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.coreGame.GraphicObject;
import com.mygdx.coreGame.Sprite;
import com.mygdx.game.ResourceEnum;
import com.mygdx.game.ResourceLoader;

/**
 * class to manage the frame around score
 * @see Number
 * @see Level
 */
public class ScoreFrame extends GraphicObject{

    private Sprite sprite;

    public ScoreFrame(){
        super();
        sprite = new Sprite(ResourceLoader.getTexture(ResourceEnum.SCORE_FRAME));
        this.setX(0.01f);
		this.setY(2.54f);
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

}
