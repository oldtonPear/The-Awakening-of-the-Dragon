package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Plane extends GameObject{

    Sprite sprite;
    Plane(){
        super();
        sprite = new Sprite(new Texture("stealth_plane.png"));
        setWidth(3);
        setOffsetX(-1.5f);
		setOffsetY(-0.55f);

        
        setRadius(0.4f);
        setbarycentre(0.1f, 0.4f);
    }


    public void update(){
    }
    

    @Override
    public void manageCollisionWith(GameObject obj) {
        if(obj instanceof Dragon){
            System.out.println("COLLISIONE!");
        }
    }
    @Override
    public void setX(float x) {
        super.setX(x);
        sprite.setX(x);
    }
    @Override
    public void setY(float y) {
        super.setY(y);
        sprite.setX(x);
    }

    @Override
    public void draw(SpriteBatch sb) {
        sprite.draw(sb);

        //Vector2 pb = getWorldBarycentre();
        //b.draw(ResourceLoader.getTexture(ResourceEnum.BUBBLE), pb.x - radius, pb.y - radius, radius * 2, radius * 2);
    }
}
