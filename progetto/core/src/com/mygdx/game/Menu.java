package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.coreGame.Parameters;

public class Menu extends Screen{

    private float backgroundY;

    private float width;
    private float height;
    
    private Texture menuTexture;


    
    Menu(String name, float height){
        super();
        this.menuTexture = ResourceLoader.getTexture(ResourceEnum.MENU);
        this.backgroundY = 0;

        this.height = height;
        this.width = height * Parameters.getAspectRatio();
    }

    @Override
    public void draw(SpriteBatch sb) {
        sb.draw(menuTexture, 0, backgroundY, width, height);
    }

    @Override
    public void update() {
        
    }
    
}

