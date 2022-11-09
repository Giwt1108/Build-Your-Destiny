/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities.Enemies;

import Entities.Entidad;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

public class Proyectil extends Enemigo{
    float XObjetivo,YObjetivo;
    boolean alcanzado;
    public Proyectil(float X,float Y) {
        //Estamina, alcance, suerte, velocidadAtaque, ataque, salud, sprite
        super(100,200,100,5,10,30,new Sprite());
        this.XObjetivo=X;
        this.YObjetivo=Y;
        this.stateTime=0;
         this.atlas=new TextureAtlas("Images/Gusano/BolaSeda.atlas");
        startAnimation();
    }

    public boolean followPlayer(double x, double y,boolean nothing){
        super.followPlayer(XObjetivo,YObjetivo);
        if(this.getSprite().getX()>=XObjetivo-20  && this.getSprite().getX()<=XObjetivo+20){
            if(this.getSprite().getY()>=YObjetivo-20  && this.getSprite().getY()<=YObjetivo+20){
                this.alcanzado=true;
            }  
        }
        if(this.getSprite().getX()>=x-20  && this.getSprite().getX()<=x+20){
            if(this.getSprite().getY()>=y-20  && this.getSprite().getY()<=y+20){
                return true;
            }  
        }
        return false;
    }
    public float getStateTime(){
        return this.stateTime;
    }
    public void draw(Batch spriteBatch) {
        super.draw(spriteBatch);
    }
    public void animate(Batch batch){
        if(this.alcanzado==true){
            batch.draw(animationRest.getKeyFrame(stateTime),getSprite().getX(),getSprite().getY());
        }
        else{
            batch.draw(animationAttack.getKeyFrame(stateTime),getSprite().getX(),getSprite().getY());
        }
    }

    private void startAnimation() {
        Array<TextureAtlas.AtlasRegion>  Move = this.atlas.findRegions("BolaSeda");
        animationAttack = new Animation(0.15f,Move, Animation.PlayMode.LOOP);
        Array<TextureAtlas.AtlasRegion>  split = this.atlas.findRegions("BolaSedaSplit");
        animationRest = new Animation(0.15f,split, Animation.PlayMode.LOOP);
        stateTime=0;
    }
    
}
