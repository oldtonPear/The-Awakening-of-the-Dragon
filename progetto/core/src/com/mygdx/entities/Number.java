package com.mygdx.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.coreGame.GraphicObject;
import com.mygdx.coreGame.Sprite;
import com.mygdx.game.ResourceEnum;
import com.mygdx.game.ResourceLoader;

public class Number extends GraphicObject{
    
    private Sprite sprite;

    private boolean decimal;

    public Number(int n, boolean decimal){
        super();
        switch (n) {
            case 0:
                sprite = new Sprite(ResourceLoader.getTexture(ResourceEnum.ZERO));
                break;
            case 1:
                sprite = new Sprite(ResourceLoader.getTexture(ResourceEnum.ONE));
                break;
            case 2:
                sprite = new Sprite(ResourceLoader.getTexture(ResourceEnum.TWO));
                break;
            case 3:
                sprite = new Sprite(ResourceLoader.getTexture(ResourceEnum.THREE));
                break;
            case 4:
                sprite = new Sprite(ResourceLoader.getTexture(ResourceEnum.FOUR));
                break;
            case 5:
                sprite = new Sprite(ResourceLoader.getTexture(ResourceEnum.FIVE));
                break;
            case 6:
                sprite = new Sprite(ResourceLoader.getTexture(ResourceEnum.SIX));
                break;
            case 7:
                sprite = new Sprite(ResourceLoader.getTexture(ResourceEnum.SEVEN));
                break;
            case 8:
                sprite = new Sprite(ResourceLoader.getTexture(ResourceEnum.EIGHT));
                break;
            case 9:
                sprite = new Sprite(ResourceLoader.getTexture(ResourceEnum.NINE));
                break;


        
            default:
                break;
        }
        this.decimal = decimal;
        if(decimal) this.setX(-0.015f);
        else this.setX(0.18f);
		this.setY(2.62f);
		this.setWidth(0.3f);
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
