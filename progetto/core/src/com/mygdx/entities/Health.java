package com.mygdx.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.coreGame.Sprite;
import com.mygdx.game.GameObject;
import com.mygdx.game.ResourceEnum;
import com.mygdx.game.ResourceLoader;

public class Health extends GameObject {

    protected Sprite sprite;

    public Health(){
        super();
        sprite = new Sprite(ResourceLoader.getTexture(ResourceEnum.HEALTH_3));
        this.setX(3.2f);
		this.setY(2.7f);
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
     * updates lives
     */
    @Override
    public void update(){
        
    }

    /**
     * automatically calls update
     * updates current Sprite
     * @return true if everything is fine
     * @return false if the number of lives is 0
     */
    public boolean updateNlives(int numberOfLives){
        update();
        switch (numberOfLives) {
            case 0:
                return false;
            case 1:
                sprite = new Sprite(ResourceLoader.getTexture(ResourceEnum.HEALTH_1));
                this.setX(3.2f);
		        this.setY(2.7f);
		        this.setWidth(0.8f);
                setRadius(0.15f);
                setBarycenter(0.4f, 0.4f);
                break;

            case 2:
                sprite = new Sprite(ResourceLoader.getTexture(ResourceEnum.HEALTH_2));
                this.setX(3.2f);
		        this.setY(2.7f);
		        this.setWidth(0.8f);
                setRadius(0.15f);
                setBarycenter(0.4f, 0.4f);
                break;

            case 3:
                sprite = new Sprite(ResourceLoader.getTexture(ResourceEnum.HEALTH_3));
                this.setX(3.2f);
		        this.setY(2.7f);
		        this.setWidth(0.8f);
                setRadius(0.15f);
                setBarycenter(0.4f, 0.4f);
                break;
        
            default:
                break;
        }
        return true;
    }
}
