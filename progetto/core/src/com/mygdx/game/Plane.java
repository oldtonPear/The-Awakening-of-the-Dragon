package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.coreGame.Sprite;

public class Plane extends GameObject{

    Sprite sprite;

    Plane(){
        super();
        sprite = new Sprite(ResourceLoader.getTexture(ResourceEnum.STEALTH_PLANE));
		setWidth(0.5f);
        setHeight(0.5f);
    }

    @Override
    public void setX(float x) {
        super.setX(x);
        sprite.setX(x);
        
    }
    @Override
    public void setY(float y) {
        sprite.setY(y);
        super.setY(y);
        
    }
    @Override
    public void setWidth(float width) {
        super.setWidth(width);
        sprite.setWidth(width);
    }
    @Override
    public void setHeight(float height) {
        super.setHeight(height);
        sprite.setHeight(height);
    }

    @Override
    public void draw(SpriteBatch sb) {
        sprite.draw(sb);

        //Vector2 pb = getWorldBarycentre();
        //b.draw(ResourceLoader.getTexture(ResourceEnum.BUBBLE), pb.x - radius, pb.y - radius, radius * 2, radius * 2);
    }
    public void update(){
        this.setY(this.getY()-0.005f);
    }
    
    @Override
    public void manageCollisionWith(GameObject obj) {
        if(obj instanceof Dragon){
            System.out.println("COLLISIONE!");
        }
    }
}
