package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.coreGame.Drawalble;

public class Level implements Drawalble{
    private String name;
    private float length;

    private float skySpeed;
    private float mgSpeed;
    private float fgSpeed;
    
    private float bx;
    private float mx;
    private float fx;

    private float width;
    private float height;
    
    private Texture skyPicture;
    private Texture bgPicture;
    private Texture mgPicture;
    private Texture fgPicture;
    private Texture gPicture;
    
    //private Knight knight;

    private GameObject[] objects;

    /*Level(String name, float height){
        super();
        this.name = name;
        this.length = 0;

        this.fgSpeed = -0.02f;
        this.mgSpeed = fgSpeed * 0.5f;
        this.skySpeed = fgSpeed * 0.25f;

        this.fx = 0;
        this.mx = 0;
        this.bx = 0;

        this.height = height;
        this.width = height * Parameters.getAspectRatio();

        this.skyPicture = ResourceLoader.getTexture(ResourceEnum.SKY_LEVEL);
        this.bgPicture = ResourceLoader.getTexture(ResourceEnum.BG_LEVEL);
        this.mgPicture = ResourceLoader.getTexture(ResourceEnum.MG_LEVEL);
        this.fgPicture = ResourceLoader.getTexture(ResourceEnum.FG_LEVEL);
        this.gPicture = ResourceLoader.getTexture(ResourceEnum.G_LEVEL);

        this.knight = null;
        this.objects = new GameObject[100];

        for (int i = 0; i < 10; i++) {
            this.objects[i] = new Coin();
            this.objects[i].setX(4 + (float)Math.random()*3 * i);
            this.objects[i].setY(0.5f + (float)Math.random() * i);
            this.objects[i].setVelocity(this.fgSpeed, 0);
        }
        
    }

    public float getSpeed() {
        return fgSpeed;
    }
    public void setSpeed(float speed) {
        this.fgSpeed = speed;
        this.mgSpeed = fgSpeed * 0.5f;
        this.skySpeed = fgSpeed * 0.25f;

    }
    public String getName() {
        return name;
    }
    public Knight getKnight() {
        return knight;
    }
    public void setKnight(Knight knight) {
        this.knight = knight;
    }

    @Override
    public void draw(SpriteBatch sb) {
        sb.draw(skyPicture, 0, 0, width, height);

        sb.draw(bgPicture, bx, 0, width, height);
        sb.draw(bgPicture, bx + width, 0, width, height);

        sb.draw(mgPicture, mx, 0, width, height);
        sb.draw(mgPicture, mx + width, 0, width, height);

        sb.draw(fgPicture, fx, 0, width, height);
        sb.draw(fgPicture, fx + width, 0, width, height);

        if(!knight.isWalking()){
            for (GameObject gameObject : objects) {
                if(gameObject != null) gameObject.draw(sb);
            }
        }

        
        knight.draw(sb);

        sb.draw(gPicture, fx, 0, width, height);
        sb.draw(gPicture, fx + width, 0, width, height);

    }

    public void update(){
        knight.update();

        if(!knight.isWalking()){

            for (int i = 0; i<objects.length; i++) {
                GameObject gameObject = objects[i];
                if(gameObject != null){
                    gameObject.update();
                    if(knight.collidesWidth(gameObject)){
                        knight.manageCollisionWith(gameObject);
                        gameObject.manageCollisionWith(knight);
                        objects[i] = null;
                    } 
                } 
            }

            fx += fgSpeed;
            if(fx <= -width) fx += width;

            mx += mgSpeed;
            if(mx <= -width) mx += width;

            bx += skySpeed;
            if(bx <= -width) bx += width;
        } 
    }*/
    @Override
    public void draw(SpriteBatch sb) {
        // TODO Auto-generated method stub
        
    }
}
