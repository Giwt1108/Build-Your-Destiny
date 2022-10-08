package Entities;


import Screens.Levels;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import estructuras.ListaEnlazada;

public class Jugador extends Entidad implements InputProcessor{

    private ListaEnlazada<Coleccionable> coleccionables; //Aqui es una estructura de coleccionables
    private Habilidad habilidad; //Aqui es un objeto Habilidad
    private int misiones; //Aqui es una estructura de misiones

    public void draw(Batch spriteBatch) {
        super.draw(spriteBatch);
    }


    public void caminar(){

        float currentX = getSprite().getX();
        float currentY = getSprite().getY();
        getSprite().setX(currentX + getVelocidadX() * Gdx.graphics.getDeltaTime());
        getSprite().setY(currentY + getVelocidadY() * Gdx.graphics.getDeltaTime());
        
    }
    
    public void perfoAtaqueDis(boolean pressedScreen, float delta, Levels screen, Stage stage){
        if(Gdx.input.isKeyJustPressed(Input.Keys.Q)){
            if(!getHabilidad().getAtaqueDistancia().isActivo()){ //Esto le metio un culdown :v
                TextButton newButton = getHabilidad().getAtaqueDistancia().ataqueDistancia(false,pressedScreen,this,true,delta);
                mostrarBoton(screen, newButton,stage);
            }

        }
        else if(getHabilidad().getAtaqueDistancia().isActivo() ){
            boolean pressedButton = screen.getButton().isPressed();
            TextButton newButton=  getHabilidad().getAtaqueDistancia().ataqueDistancia(pressedButton,pressedScreen,this,false,delta);
            boolean buttonActive = getHabilidad().getAtaqueDistancia().isButtonActivo();
            screen.getButton().setVisible(buttonActive);
            if(newButton != null){
                getHabilidad().getAtaqueDistancia().setButtonActivo(true);
                mostrarBoton(screen,newButton,stage);
            }


        }
    }
    
    public void mostrarBoton(Levels screen,TextButton button,Stage stage){
        screen.setButton(button);
        stage.addActor(screen.getButton());
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

        @Override
    public boolean keyDown(int keycode) {
        switch(keycode){
            case Input.Keys.W:
                if(!isCollitedY()){
                    setVelocidadY(getSpeed());
                }
                break;
            case Input.Keys.A:
                if(!isCollitedX()){
                    setVelocidadX(-getSpeed());
                }
                break;
            case Input.Keys.S:
                if(!isCollitedY()){
                    setVelocidadY(-getSpeed());
                }
                break;
            case Input.Keys.D:
                if(!isCollitedX()){
                    setVelocidadX(getSpeed());
                }
                break;
        }        
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch(keycode){
            case Input.Keys.W:
            case Input.Keys.S:
                setVelocidadY(0);
                break;
            case Input.Keys.A:
            case Input.Keys.D:
                setVelocidadX(0);
        }        
        return true;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float f, float f1) {
        return false;
    }

}

