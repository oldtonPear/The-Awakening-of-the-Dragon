package com.mygdx.game;

import com.mygdx.coreGame.Sprite;

public class Stealth_plane extends Plane{

    Stealth_plane(){
        super();
        sprite = new Sprite(ResourceLoader.getTexture(ResourceEnum.STEALTH_PLANE));
        setWidth(0.5f);
        setHeight(0.5f);

        setRadius(0.2f);
        setbarycentre(0.22f, 0.26f);
    }
    
}
