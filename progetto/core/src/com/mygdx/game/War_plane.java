package com.mygdx.game;

import com.mygdx.coreGame.Sprite;

public class War_plane extends Plane{
    
    War_plane(){
        super();
        setSprite(new Sprite(ResourceLoader.getTexture(ResourceEnum.WAR_PLANE)));
        setWidth(0.5f);
        setHeight(0.5f);

        setRadius(0.2f);
        setbarycentre(0.22f, 0.26f);
    }
}
