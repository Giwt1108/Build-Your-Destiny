package data;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import estructuras.ListaEnlazada;

public class Jugador extends Entidad{

    private ListaEnlazada<Coleccionable> coleccionables; //Aqui es una estructura de coleccionables
    private Habilidad habilidad; //Aqui es un objeto Habilidad
    private int misiones; //Aqui es una estructura de misiones


    public void caminar(){
        if( Gdx.input.isKeyPressed(Input.Keys.A) && getVelocidadX() >= 0) {
            getEntidad().x -= getVelocidad() * Gdx.graphics.getDeltaTime();
        }
        if( Gdx.input.isKeyPressed(Input.Keys.D) && getEntidad().x <= 800-getSkin().getWidth()) {
            getEntidad().x += getVelocidad()*Gdx.graphics.getDeltaTime();
        }
        if( Gdx.input.isKeyPressed(Input.Keys.S) && getEntidad().y >= 0){
            getEntidad().y -= getVelocidad() *Gdx.graphics.getDeltaTime();
        }
        if( Gdx.input.isKeyPressed(Input.Keys.W) && getEntidad().y <= 480 - getSkin().getHeight()) {
            getEntidad().y += getVelocidad()*Gdx.graphics.getDeltaTime();
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
    public Jugador(ListaEnlazada<Coleccionable> coleccionables,  int misiones, int velocidad, int estamina, int alcance, int suerte, int velocidadAtaque, int ataque, int salud, Habilidad habilidad) {
        super(estamina, alcance, suerte, velocidadAtaque, ataque, salud);
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

