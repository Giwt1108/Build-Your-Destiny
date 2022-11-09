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
    private float CoolDownAttack=0;
    private Proyectil [] proyectiles= new Proyectil[20];
    private int proyecCounter=0;
    public QueueGusanin() {
        //Estamina, alcance, suerte, velocidadAtaque, ataque, salud, sprite
        super(100,150,100,5,10,30,new Sprite());
        this.atlas=new TextureAtlas("Images/Gusano/QueueGus.atlas");
        startAnimation();
    }
    public void draw(Batch spriteBatch) {
        super.draw(spriteBatch);
    }
    
     public boolean canGetDamage(){ 
         return true;
    }
    public boolean playerNear(float X,float Y){
        float shootX=X;
        float shootY=Y;
        if(X<0){X=X*(-1);}
        if(Y<0){Y=Y*(-1);}
        double distance;
        double x=(double)X-this.sprite.getX();
        double y=(double)Y-this.sprite.getY();
        distance=(x*x)+(y*y);
        distance=Math.sqrt(distance);
        if(distance<this.alcance){
            if(isCollitedY()!=true ){
                this.setVelocidadY((float) (2*distance));
                this.setVelocidadX(0);
            }
            else{
                this.setVelocidadY(0);
                this.setVelocidadX(0);
            }
            this.isAttacking=false;
            return true;
        }
        else if(distance<this.alcance+30){
            this.setVelocidadY(0);
            this.setVelocidadX(0);
            if(this.CoolDownAttack<this.stateTime){
                this.isAttacking=true;
                this.proyectiles[this.proyecCounter]= new Proyectil(shootX,shootY);
                this.CoolDownAttack=this.stateTime+5;
            }
            
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
    public Proyectil[] getProyectiles(){
        return this.proyectiles;
    }
    public int getProyectilesCount(){
        return this.proyecCounter;
    }
    @Override
    public void addStateTime(float delta){
        stateTime += delta;
        if(this.CoolDownAttack-1.5<stateTime){
            this.isAttacking=false;
        }
      
    }
    
}
