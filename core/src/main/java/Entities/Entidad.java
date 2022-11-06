package Entities;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class Entidad extends Sprite {
    //private int velocidad; //Debería ser 100? deberia ser final?
    protected Vector2 velocidad = new Vector2();
    protected float speed = 300;
    //private Vector2 velocidad;
    protected int estamina;
    protected int alcance;
    protected int suerte;
    protected int velocidadAtaque;
    protected int ataque;
    protected float salud;
    public Sprite sprite;
    public boolean collitedX;
    public boolean collitedY;
    //private Estado estado;

    public void dispose(){
        if(this.sprite!=null){
            this.sprite.getTexture().dispose();
        }
    }



    //GETTERS AND SETTERS


    public boolean isCollitedX() {
        return collitedX;
    }

    public void setCollitedX(boolean collitedX) {
        this.collitedX = collitedX;
    }

    public boolean isCollitedY() {
        return collitedY;
    }

    public void setCollitedY(boolean collitedY) {
        this.collitedY = collitedY;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public float getVelocidadX() {
        return velocidad.x;
    }

    public void setVelocidadX(float velocidad) {
        this.velocidad.x = velocidad;
    }
    
    public Vector2 getVelocidad(){
        return velocidad;
    }

    public float getVelocidadY(){
        return this.velocidad.y;
    }

    public void setVelocidadY(float velocidad){
        this.velocidad.y = velocidad;
    }
    
    public float getSpeed(){
        return speed;
    }
    
    public void setSpeed(float newSpeed){
        speed = newSpeed;
    }

    public int getEstamina() {
        return estamina;
    }

    public void setEstamina(int estamina) {
        this.estamina = estamina;
    }

    public int getAlcance() {
        return alcance;
    }

    public void setAlcance(int alcance) {
        this.alcance = alcance;
    }

    public int getSuerte() {
        return suerte;
    }

    public void setSuerte(int suerte) {
        this.suerte = suerte;
    }

    public int getVelocidadAtaque() {
        return velocidadAtaque;
    }

    public void setVelocidadAtaque(int velocidadAtaque) {
        this.velocidadAtaque = velocidadAtaque;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public float getSalud() {
        return salud;
    }

    public void setSalud(float salud) {
        this.salud = salud;
    }

    public Entidad(int estamina, int alcance, int suerte, int velocidadAtaque, int ataque, int salud, Sprite sprite) {
        //this.velocidad = velocidad;
        super(sprite);
        this.velocidad.x = 0;
        this.velocidad.y= 0;
        this.estamina = estamina;
        this.alcance = alcance;
        this.suerte = suerte;
        this.velocidadAtaque = velocidadAtaque;
        this.ataque = ataque;
        this.salud = salud;
        this.sprite = sprite;
        //Aunque aqui en vez de null deberia ser una skin default

    }

    public Entidad(){
        this(0,0,0,0,0,100,new Sprite(new Texture(Gdx.files.internal("Images/Player/PersonajePrincipal.png"))));
    }


}

