package com.mygdx.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.coreGame.AnimatedSprite;
import com.mygdx.game.GameObject;
import com.mygdx.game.ResourceEnum;
import com.mygdx.game.ResourceLoader;

/**
 * class to manage the dragon
 * @see Level
 */
public class Dragon extends GameObject{
    
    private AnimatedSprite sprite;
    
    public enum State{
        HOLD,
        MOVING_LEFT,
        MOVING_RIGHT
    }

    private State currentState;

    
    public Dragon(){
        super();
        sprite = new AnimatedSprite(ResourceLoader.getAnimation(ResourceEnum.DRAGON));
        this.setX(1.4f);
		this.setY(0);
		this.setWidth(1f);

        setRadius(0.4f);
        setBarycenter(0.5f, 0.4f);

        currentState = State.HOLD;
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

    public State getCurrentState() {
        return currentState;
    }
    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    
    @Override
    public void draw(SpriteBatch sb) {
        sprite.draw(sb);

        //Vector2 pb = getWorldBarycenter();
        //sb.draw(ResourceLoader.getTexture(ResourceEnum.BUBBLE), pb.x - radius, pb.y - radius, radius * 2, radius * 2);
    }

    
    @Override
    public void update() {
        sprite.update();

        switch (currentState) {
            case HOLD:
                
                break;
            case MOVING_LEFT:
                if(this.getX() > 0f)
                this.setX(this.getX() - 0.02f);
                break;
            case MOVING_RIGHT:
                if(this.getX() < 4-this.getWidth())
                this.setX(this.getX() + 0.02f);
                break;
        
            default:
                break;
        }
        
    }
}
