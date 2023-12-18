package com.mygdx.game;

import java.util.EnumMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class ResourceLoader {
    private static EnumMap<ResourceEnum, Texture> mapTexture = new EnumMap<>(ResourceEnum.class);

    private static EnumMap<ResourceEnum, Texture[]> mapAnimation = new EnumMap<ResourceEnum, Texture[]>(ResourceEnum.class);

    private static EnumMap<ResourceEnum, Sound> mapSound = new EnumMap<ResourceEnum, Sound>(ResourceEnum.class);
    
    
    public static Texture getTexture(ResourceEnum index) {
        if(!mapTexture.containsKey(index)){
            switch (index) {
            case STEALTH_PLANE:
                mapTexture.put(index, new Texture("stealth_plane.png"));
            break;
            case WAR_PLANE:
                mapTexture.put(index, new Texture("war_plane.png"));
            break;
            case SKY:
                mapTexture.put(index, new Texture("sky.png"));
            break;
            case BUBBLE:
                mapTexture.put(index, new Texture("bubble.png"));
            break;
            case MENU:
                mapTexture.put(index, new Texture("menu.png"));
            break;
            
            default: return null;
            }
        }  
        return mapTexture.get(index);
    }
    public static Texture[] getAnimation(ResourceEnum index){
        Texture[] res = null;
        if(!mapAnimation.containsKey(index)){
            switch (index) {
            case DRAGON:
                res = new Texture[36];
                for (int i = 0; i < res.length; i++) {
                    res[i] = new Texture("dragon_" + (i / 12) + ".png");
                    mapAnimation.put(index, res);
                }
            break;
            default: res = null;
            }
        }  
        else{
            res = mapAnimation.get(index);
        }
        return res;
    }

    public static Sound getSound(ResourceEnum index){
        Sound res = null;
        if(!mapSound.containsKey(index)){
            switch (index) {
            /*case AUDIO_COIN:
                res = Gdx.audio.newSound(Gdx.files.internal("audio/coin.wav"));
                mapSound.put(index, res);
            break;*/
            default: res = null;
            }
        }
        else{
            res = mapSound.get(index);
        }
        return res;
    }
}
