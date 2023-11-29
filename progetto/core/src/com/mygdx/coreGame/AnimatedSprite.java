package com.mygdx.coreGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AnimatedSprite extends GraphicObject {

    private Texture[] frames;
    private int currentFrame;


    public AnimatedSprite(){
        this.frames = null;
        this.currentFrame = -1;
    }
    public AnimatedSprite(Texture[] frames){
        setAnimation(frames);
    }
    public void setAnimation(Texture[] frames){
        this.frames = frames;
        this.currentFrame = -1;
    }

    public void setWidth(float width) {
        super.setWidth(width);
        if(frames != null) super.height = width * frames[0].getHeight() / frames[0].getWidth();
    }
    public void setHeight(float height) {
        super.setHeight(height);
        if(frames != null) super.width = height * frames[0].getHeight() / frames[0].getWidth();
    }

    
    public void draw(SpriteBatch sb){
        sb.draw(frames[currentFrame], x +offsetX, y + offsetY, width, height);
    }
    public void update(){
        currentFrame++;
        if(currentFrame==frames.length) currentFrame = 0;
    }
    
}
