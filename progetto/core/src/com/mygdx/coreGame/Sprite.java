package com.mygdx.coreGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Sprite extends GraphicObject{
    
    private Texture frame;

    public Sprite(Texture img){
        frame = img;
        x=0;
        y=0;
        width = 1;
        height = frame.getHeight()/frame.getWidth();
    }

    public void setWidth(float width) {
        super.setWidth(width);
        super.setHeight(width * frame.getHeight() / frame.getWidth());
    }
    public void setHeight(float height) {
        super.setHeight(height);
        super.setWidth(height * frame.getHeight() / frame.getWidth());
    }
    
    public void draw(SpriteBatch sb){
        sb.draw(frame, x, y, width, height);
    }

}
