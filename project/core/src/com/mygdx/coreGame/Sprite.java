package com.mygdx.coreGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;


/**
 * class to manage a normal Sprite
 */
public class Sprite extends GraphicObject{
    
    private Texture frame;

    /**
     * constructor for a Sprite given a texture
     * @param img
     */
    public Sprite(Texture img){
        frame = img;
        x=0;
        y=0;
        width = 1;
        height = frame.getHeight()/frame.getWidth();
    }

    /**
     * sets sprite's width
     * sets also height to maintain proportion
     * @param width
     */
    @Override
    public void setWidth(float width) {
        super.setWidth(width);
        super.setHeight(width * frame.getHeight() / frame.getWidth());
    }

    /**
     * sets sprite's height
     * sets also width to maintain proportion
     * @param height
     */
    @Override
    public void setHeight(float height) {
        super.setHeight(height);
        super.setWidth(height * frame.getHeight() / frame.getWidth());
    }
    
    /**
     * draws the sprite
     * @see Drawable
     */
    public void draw(SpriteBatch sb){
        sb.draw(frame, x, y, width, height);
    }

}
