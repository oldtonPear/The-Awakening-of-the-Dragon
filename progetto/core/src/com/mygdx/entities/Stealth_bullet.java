package com.mygdx.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.coreGame.Sprite;
import com.mygdx.game.ResourceEnum;
import com.mygdx.game.ResourceLoader;

public class Stealth_bullet extends Bullet{
    
    public Stealth_bullet(float planeX, float planeY) {
        super(planeX, planeY);
        sprite = new Sprite(ResourceLoader.getTexture(ResourceEnum.STEALTH_BULLET));
        setWidth(0.5f);
        setHeight(0.5f);

        setRadius(0.18f);
        setBarycenter(0.18f, 0.26f);

        setX(planeX);
        setY(planeY);
    }

    public void draw(SpriteBatch sb){
        super.draw(sb);
    }
}
