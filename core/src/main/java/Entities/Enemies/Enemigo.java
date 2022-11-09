package Entities.Enemies;

import Entities.Entidad;
import Entities.Estado;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Enemigo extends Entidad{
     TextureAtlas atlas;
    protected Animation<TextureRegion> animationWalk,animationRest,animationAttack;
    protected boolean isAttacking=true;
    protected boolean canAttack=true;
    protected boolean alive=true;
    protected float momentOfAttack;
    protected float stateTime=0;
    protected float stateAttack=0;
    protected Estado estado;

    //CONSTRUCTORES
    public Enemigo(int estamina, int alcance, int suerte, int velocidadAtaque, int ataque, int salud, Sprite sprite){
        //Estamina, alcance, suerte, velocidadAtaque, ataque, salud, sprite
        super(estamina,alcance,suerte,velocidadAtaque,ataque,salud ,new Sprite());
        this.estado = null;
    }
    //Eliminar Enemigo
     public void kill(){
        this.alive=false;
    }
     

     //Comportamientos generales del enemigo, de ser necesario pueden sobreescribirse
     
     //Perseguir al jugador si el enemigo lo necesita 
     public void followPlayer(float x,float y){
        if(this.sprite.getX()+10<x && this.sprite.getY()+10<y && !collitedY && !collitedX){
            this.setVelocidadY(this.getSpeed()-50);
            this.setVelocidadX(this.getSpeed()-50);
        }
        else if(this.sprite.getX()-10>x && this.sprite.getY()+10<y && !collitedY && !collitedX){
             this.setVelocidadY(this.getSpeed()-50);
             this.setVelocidadX(-this.getSpeed()+50);
        }
        else if(this.sprite.getX()-10>x && this.sprite.getY()-10>y && !collitedY && !collitedX){
            this.setVelocidadY(-this.getSpeed()+50);
            this.setVelocidadX(-this.getSpeed()+50);
        }
        else if(this.getX()+10<x && this.sprite.getY()-10>y && !collitedY && !collitedX){
             this.setVelocidadY(-this.getSpeed()+50);
             this.setVelocidadX(this.getSpeed()-50);
        }
        else if(this.sprite.getX()+5<=x && !collitedY && !collitedX){
            this.setVelocidadY(0);
           this.setVelocidadX(this.getSpeed()-50);
        }
        else if(this.sprite.getX()-5>=x &&  !collitedY && !collitedX){
            this.setVelocidadY(0);
            this.setVelocidadX(-this.getSpeed()+50);
          }
        else if(this.sprite.getY()+5<=y  && !collitedY && !collitedX){
            this.setVelocidadY(this.getSpeed()-50);
            this.setVelocidadX(0);
        }
        else if( this.sprite.getY()-5>=y && !collitedY && !collitedX){
            this.setVelocidadY(-this.getSpeed()+50);
            this.setVelocidadX(0);
        }
        else{
            this.setVelocidadY(0);
            this.setVelocidadX(0);
        }
    }
     public void caminar(){

        float currentX = getSprite().getX();
        float currentY = getSprite().getY();
        getSprite().setX(currentX + getVelocidadX() * Gdx.graphics.getDeltaTime());
        getSprite().setY(currentY + getVelocidadY() * Gdx.graphics.getDeltaTime());
        
    }
     
     public void addStateTime(float delta){
        stateTime += delta;
    }
    
     public void resetStateTime(){
        stateTime=0;
    }

    //GETTERS & SETTERS
    public boolean isAlive(){
        return this.alive;
    }
    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
