package com.mygdx.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.coreGame.Sprite;
import com.mygdx.game.ResourceEnum;
import com.mygdx.game.ResourceLoader;

public class War_bullet extends Bullet{
    
    public War_bullet(float planeX, float planeY) {
        super(planeX, planeY);
        sprite = new Sprite(ResourceLoader.getTexture(ResourceEnum.WAR_BULLET));
        setWidth(0.28f);

        setRadius(0.1f);
        setBarycenter(0.25f, 0.3f);

        setX(planeX);
        setY(planeY);
    }

    public void draw(SpriteBatch sb){
        super.draw(sb);
    }
}
