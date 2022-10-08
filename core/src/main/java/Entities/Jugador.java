package Entities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import estructuras.ListaEnlazada;

public class Jugador extends Entidad {

    private ListaEnlazada<Coleccionable> coleccionables; //Aqui es una estructura de coleccionables
    private Habilidad habilidad; //Aqui es un objeto Habilidad
    private int misiones; //Aqui es una estructura de misiones

    public void draw(Batch spriteBatch) {
        super.draw(spriteBatch);
    }


    public void caminar(){

        float currentX = getSprite().getX();
        float currentY = getSprite().getY();
        if( Gdx.input.isKeyPressed(Input.Keys.A) && currentX >= 0) {
            System.out.println("Entro A");
            System.out.println("VelX: " +this.getVelocidadX());
            getSprite().setX(currentX - getVelocidadX() * Gdx.graphics.getDeltaTime());
        }
        if( Gdx.input.isKeyPressed(Input.Keys.D) && currentX <= 800-getSprite().getWidth()) {
            getSprite().setX(currentX + getVelocidadX() * Gdx.graphics.getDeltaTime());
            System.out.println("Entro D");
        }
        if( Gdx.input.isKeyPressed(Input.Keys.S) && currentY >= 0){
            getSprite().setY(currentY - getVelocidadY() * Gdx.graphics.getDeltaTime());
            System.out.println("Entro S");
        }
        if( Gdx.input.isKeyPressed(Input.Keys.W) && currentY <= 480 - getSprite().getHeight()) {
            getSprite().setY(currentY + getVelocidadY() * Gdx.graphics.getDeltaTime());
            System.out.println("Entro W");
        }

    }



    //HAY QUE CORREGIR TODOS LOS GETTER Y SETTER PONIENDO EL TIPO DE DATO CORRECTO


    public ListaEnlazada<Coleccionable> getColeccionables() {
        return coleccionables;
    }

    public void setColeccionables(ListaEnlazada<Coleccionable> coleccionables) {
        this.coleccionables = coleccionables;
    }

    public Habilidad getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(Habilidad habilidad) {
        this.habilidad = habilidad;
    }

    public int getMisiones() {
        return misiones;
    }

    public void setMisiones(int misiones) {
        this.misiones = misiones;
    }


    //HAY QUE CORREGIR LOS CONSTRUCTORES PONIENDO EL TIPO DE DATO CORRECTO
    public Jugador(ListaEnlazada<Coleccionable> coleccionables, int misiones, int velocidad, int estamina, int alcance, int suerte, int velocidadAtaque, int ataque, int salud, Habilidad habilidad) {
        super(estamina, alcance, suerte, velocidadAtaque, ataque, salud, new Sprite());
        this.coleccionables = coleccionables;
        this.habilidad = habilidad;
        this.misiones = misiones;
    }

    public Jugador(ListaEnlazada<Coleccionable> coleccionables, int misiones, Habilidad habilidad) {
        super();
        this.coleccionables = coleccionables;
        this.habilidad = habilidad;
        this.misiones = misiones;
    }


    //Arreglar el constructor nulo den jugador
    public Jugador(){
        super();
        this.coleccionables = new ListaEnlazada<Coleccionable>(); //NO HACERLO NULO
        this.habilidad = new Humanas(); //NO HACERLO NULO
        //this.misiones = new Estructura(); //NO HACERLO NULO
    }

}

