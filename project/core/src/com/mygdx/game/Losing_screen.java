package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.coreGame.Parameters;

/**
 * screen visualized when you lose
 */
public class Losing_screen extends Screen{

    private float backgroundY;

    private float width;
    private float height;
    
    private Texture texture;

    Losing_screen(String name, float height){
        super();
        this.texture = ResourceLoader.getTexture(ResourceEnum.YOU_LOSE);
        this.backgroundY = 0;

        this.height = height;
        this.width = height * Parameters.getAspectRatio();
    }

    @Override
    public void draw(SpriteBatch sb) {
        sb.draw(texture, 0, backgroundY, width, height);
    }

    @Override
    public void update() {
        
    }
    
}
