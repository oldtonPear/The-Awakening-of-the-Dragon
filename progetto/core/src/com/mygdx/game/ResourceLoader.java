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
                mapTexture.put(index, new Texture("progetto/assets/stealth_plane.png"));
            break;
            case WAR_PLANE:
                mapTexture.put(index, new Texture("progetto/assets/war_plane.png"));
            break;
            case SKY:
                mapTexture.put(index, new Texture("progetto/assets/sky.png"));
            break;
            case BUBBLE:
                mapTexture.put(index, new Texture("progetto/assets/bubble.png"));
            break;
            case MENU:
                mapTexture.put(index, new Texture("progetto/assets/menu.png"));
            break;
            case STEALTH_BULLET:
                mapTexture.put(index, new Texture("progetto/assets/stealth_bullet.png"));
            break;
            case WAR_BULLET:
                mapTexture.put(index, new Texture("progetto/assets/war_bullet.png"));
            break;
            case HEALTH_1:
                mapTexture.put(index, new Texture("progetto/assets/Health1.png"));
            break;
            case HEALTH_2:
                mapTexture.put(index, new Texture("progetto/assets/Health2.png"));
            break;
            case HEALTH_3:
                mapTexture.put(index, new Texture("progetto/assets/Health3.png"));
            break;
            case FALLING_HEART:
                mapTexture.put(index, new Texture("progetto/assets/falling_heart.png"));
            break;
            case CHEST:
                mapTexture.put(index, new Texture("progetto/assets/chest.png"));
            break;
            case SCORE_FRAME:
                mapTexture.put(index, new Texture("progetto/assets/score_frame.png"));
            break;
            case ZERO:
                mapTexture.put(index, new Texture("progetto/assets/zero.png"));
            break;
            case ONE:
                mapTexture.put(index, new Texture("progetto/assets/one.png"));
            break;
            case TWO:
                mapTexture.put(index, new Texture("progetto/assets/two.png"));
            break;
            case THREE:
                mapTexture.put(index, new Texture("progetto/assets/three.png"));
            break;
            case FOUR:
                mapTexture.put(index, new Texture("progetto/assets/four.png"));
            break;
            case FIVE:
                mapTexture.put(index, new Texture("progetto/assets/five.png"));
            break;
            case SIX:
                mapTexture.put(index, new Texture("progetto/assets/six.png"));
            break;
            case SEVEN:
                mapTexture.put(index, new Texture("progetto/assets/seven.png"));
            break;
            case EIGHT:
                mapTexture.put(index, new Texture("progetto/assets/eight.png"));
            break;
            case NINE:
                mapTexture.put(index, new Texture("progetto/assets/nine.png"));
            break;
            case YOU_LOSE:
                mapTexture.put(index, new Texture("progetto/assets/you_lose.png"));
            break;
            case YOU_WIN:
                mapTexture.put(index, new Texture("progetto/assets/you_win.png"));
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
            case FIREBALL:
                res = new Texture[36];
                for (int i = 0; i < res.length; i++) {
                    res[i] = new Texture("fireball_" + i/6 + ".png");
                    mapAnimation.put(index, res);
                }
            break;
            case EXPLOSION:
                res = new Texture[36];
                for (int i = 0; i < res.length; i++) {
                    res[i] = new Texture("explosion_" + i/9 + ".png");
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
            case MUSIC_LEVEL:
                res = Gdx.audio.newSound(Gdx.files.internal("progetto/sounds/music_level.mp3"));
                mapSound.put(index, res);
            break;
            case EXPLOSION_SOUND:
                res = Gdx.audio.newSound(Gdx.files.internal("progetto/sounds/explosion.mp3"));
                mapSound.put(index, res);
            break;
            case FIREBALL_SOUND:
                res = Gdx.audio.newSound(Gdx.files.internal("progetto/sounds/fireball.mp3"));
                mapSound.put(index, res);
            break;
            case PROJECTILE_SOUND:
                res = Gdx.audio.newSound(Gdx.files.internal("progetto/sounds/projectile_sound.mp3"));
                mapSound.put(index, res);
            break;
            case CHEST_SOUND:
                res = Gdx.audio.newSound(Gdx.files.internal("progetto/sounds/chest.mp3"));
                mapSound.put(index, res);
            break;
            case MENU_MUSIC:
                res = Gdx.audio.newSound(Gdx.files.internal("progetto/sounds/menu_music.mp3"));
                mapSound.put(index, res);
            break;
            default: res = null;
            }
        }
        else{
            res = mapSound.get(index);
        }
        return res;
    }
}
