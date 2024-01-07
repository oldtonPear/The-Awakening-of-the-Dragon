package com.mygdx.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.coreGame.Sprite;
import com.mygdx.game.ResourceEnum;
import com.mygdx.game.ResourceLoader;

public class Stealth_bullet extends Bullet{
    
    public Stealth_bullet(float planeX, float planeY) {
        super(planeX, planeY);
        sprite = new Sprite(ResourceLoader.getTexture(ResourceEnum.STEALTH_BULLET));
        setWidth(0.45f);

        setRadius(0.15f);
        setBarycenter(0.22f, 0.2f);

        setX(planeX);
        setY(planeY);
    }

    public void draw(SpriteBatch sb){
        super.draw(sb);
    }
}
