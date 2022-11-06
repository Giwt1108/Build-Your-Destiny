/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities.Enemies;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

/**
 *
 * @author david
 */
public class QueueGusanin extends Enemigo{

    public QueueGusanin() {
        super(100,30,100,5,10,30,new Sprite());
        this.atlas=new TextureAtlas("Images/Gusano/QueueGus.atlas");

        startAnimation();
    }
    public void draw(Batch spriteBatch) {
        super.draw(spriteBatch);
    }
    public boolean playerNear(float X,float Y){
        if(X<0){X=X*(-1);}
        if(Y<0){Y=Y*(-1);}
        double distance;
        double x=(double)X-this.sprite.getX();
        double y=(double)Y-this.sprite.getY();
        distance=(x*x)+(y*y);
        distance=Math.sqrt(distance);
        if(distance<this.alcance){
            this.setVelocidadX(0);
            this.setVelocidadY(0);
            this.isAttacking=true;
            return true;
        }
        else if( distance<300){
            followPlayer(X,Y);
            this.isAttacking=false;
            return false;
            }
        else{
            this.setVelocidadX(0);
            this.setVelocidadY(0);
            this.isAttacking=false;
            return false;
        }
    }
    public void animate(Batch batch){
        if(this.isAttacking==true){
            batch.draw(animationAttack.getKeyFrame(stateTime),getSprite().getX(),getSprite().getY());
            this.setVelocidadX(0);
            this.setVelocidadX(0);
        }
        else if((getVelocidadX()==0 && getVelocidadY()==0) || isCollitedX() || isCollitedY()){
            this.setVelocidadX(speed-30);
            this.setVelocidadY(speed-30);
            batch.draw(animationRest.getKeyFrame(stateTime),getSprite().getX(),getSprite().getY());
        }else{
            this.setVelocidadX(speed-30);
            this.setVelocidadY(speed-30);
            batch.draw(animationWalk.getKeyFrame(stateTime),getSprite().getX(),getSprite().getY());
        }
    }
    private void startAnimation(){
        Array<TextureAtlas.AtlasRegion>  walk = this.atlas.findRegions("GusWalk");
        animationWalk = new Animation(0.15f,walk, Animation.PlayMode.LOOP);
        Array<TextureAtlas.AtlasRegion>  rest = atlas.findRegions("GusRest");
        animationRest = new Animation(0.15f,rest, Animation.PlayMode.LOOP);
        Array<TextureAtlas.AtlasRegion> attack =this.atlas.findRegions("GusAtq");
        animationAttack = new Animation(0.15f,attack, Animation.PlayMode.LOOP);
        stateTime=0;
    }
    
}