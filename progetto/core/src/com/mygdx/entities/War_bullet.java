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
        setWidth(0.2f);

        setRadius(0.1f);
        setBarycenter(0.1f, 0.12f);

        setX(planeX+0.15f);
        setY(planeY);
    }

    public void draw(SpriteBatch sb){
        super.draw(sb);
    }
    /**
     * updates plane
     */
    @Override
    public void update(){
        this.setY(this.getY()-0.012f);
    }
}
