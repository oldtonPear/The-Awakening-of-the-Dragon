package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.coreGame.AnimatedSprite;

public class Dragon extends GameObject{
    
    AnimatedSprite sprite;
    Dragon(){
        super();
        sprite = new AnimatedSprite(ResourceLoader.getAnimation(ResourceEnum.DRAGON));
        this.setX(0f);
		this.setY(0);
		this.setWidth(1f);
    }
    @Override
    public void manageCollisionWith(GameObject obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'manageCollisionWith'");
    }

    @Override
    public void update() {
        sprite.update();
        
    }

    @Override
    public void draw(SpriteBatch sb) {
        sprite.draw(sb);

        //Vector2 pb = getWorldBarycentre();
        //b.draw(ResourceLoader.getTexture(ResourceEnum.BUBBLE), pb.x - radius, pb.y - radius, radius * 2, radius * 2);
    }

    @Override
    public void setX(float x) {
        super.setX(x);
        sprite.setX(x);
    }
    @Override
    public void setY(float y) {
        super.setY(y);
        sprite.setY(y);
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
    
}
