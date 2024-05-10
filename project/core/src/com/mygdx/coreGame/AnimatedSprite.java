package com.mygdx.coreGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Class to manage AnimatedSprites
 * @oldtonPear
 * @see GraphicObject
 */
public class AnimatedSprite extends GraphicObject implements Updateable{

    private Texture[] frames;
    private int currentFrame;

    /**
     * default constructor
     */
    public AnimatedSprite(){
        this.frames = null;
        this.currentFrame = -1;
    }

    /**
     * constructor given the array of textures
     * @param frames
     */
    public AnimatedSprite(Texture[] frames){
        setAnimation(frames);
    }

    /**
     * sets the array of texture as the one passed
     * @param frames
     */
    public void setAnimation(Texture[] frames){
        this.frames = frames;
        this.currentFrame = -1;
    }

    /**
     * sets sprite's width
     * sets also height to maintain proportion
     * @param width
     */
    @Override
    public void setWidth(float width) {
        super.setWidth(width);
        if(frames != null) super.height = width * frames[0].getHeight() / frames[0].getWidth();
    }

    /**
     * sets sprite's height
     * sets also width to maintain proportion
     * @param height
     */
    @Override
    public void setHeight(float height) {
        super.setHeight(height);
        if(frames != null) super.width = height * frames[0].getHeight() / frames[0].getWidth();
    }

    /**
     * draws the animatedSprite
     * @see Drawable
     */
    public void draw(SpriteBatch sb){
        sb.draw(frames[currentFrame], x +offsetX, y + offsetY, width, height);
    }

    /**
     * updates the animation
     * @see Updateable
     */
    public void update(){
        currentFrame++;
        if(currentFrame==frames.length) currentFrame = 0;
    }
    
}
