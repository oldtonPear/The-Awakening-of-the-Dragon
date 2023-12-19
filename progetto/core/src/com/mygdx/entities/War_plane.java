package com.mygdx.entities;

import com.mygdx.coreGame.Sprite;
import com.mygdx.game.ResourceEnum;
import com.mygdx.game.ResourceLoader;

public class War_plane extends Plane{
    
    /**
     * a specific type of plane
     */
    public War_plane(){
        super();
        sprite = new Sprite(ResourceLoader.getTexture(ResourceEnum.WAR_PLANE));
        setWidth(0.5f);
        setHeight(0.5f);

        setRadius(0.2f);
        setBarycenter(0.24f, 0.25f);
    }
}
