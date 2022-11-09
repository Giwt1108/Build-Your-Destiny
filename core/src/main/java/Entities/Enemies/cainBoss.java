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
public class cainBoss extends Enemigo {
    float tired=0;
    public cainBoss(){
        //Estamina, alcance, suerte, velocidadAtaque, ataque, salud, sprite
        super(100,30,100,5,20,100,new Sprite());
        this.atlas=new TextureAtlas("Images/Cain/cain.atlas");
        startAnimation();
    }
    
    public boolean playerNear(float X, float Y){
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
    
    
    public void draw(Batch spriteBatch) {
        super.draw(spriteBatch);
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
        Array<TextureAtlas.AtlasRegion>  walk = this.atlas.findRegions("Cain-Caminando");
        animationWalk = new Animation(0.15f,walk, Animation.PlayMode.LOOP);
        Array<TextureAtlas.AtlasRegion>  rest = atlas.findRegions("Cain-Descanso");
        animationRest = new Animation(0.15f,rest, Animation.PlayMode.LOOP);
        Array<TextureAtlas.AtlasRegion> attack =this.atlas.findRegions("Cain-ataque");
        animationAttack = new Animation(0.05f,attack, Animation.PlayMode.LOOP);
        stateTime=0;
    }
    
    @Override
    public void addStateTime(float delta){
        stateTime += delta;
        if(this.tired==0){
            this.tired=this.stateTime+5;
            this.setSpeed((float) (this.getSpeed()*2));
        }
        else if( this.tired<this.stateTime){
            this.tired=0;
            this.setSpeed(this.getSpeed()/2);
        
        }
      
    }
}
