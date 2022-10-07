package data;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class Entidad extends Sprite {
    //private int velocidad; //Debería ser 100? deberia ser final?
    private Vector2 velocidad = new Vector2();
    //private Vector2 velocidad;
    private int estamina;
    private int alcance;
    private int suerte;
    private int velocidadAtaque;
    private int ataque;
    private int salud;
    private Sprite sprite;
    //private Estado estado;

    public void dispose(){
        this.sprite.getTexture().dispose();
    }



    //GETTERS AND SETTERS


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

    public float getVelocidadY(){
        return this.velocidad.y;
    }

    public void setVelocidadY(float velocidad){
        this.velocidad.y = velocidad;
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

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
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
        Texture skin= new Texture(Gdx.files.internal("ghost default.jpg")); //Aunque aqui en vez de null deberia ser una skin default
        this.sprite = new Sprite(skin);
    }

    public Entidad(){
        this(0,0,0,0,0,0, new Sprite());
    }


}

