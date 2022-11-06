package Entities;


import Screens.Levels;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;

import estructuras.ListaEnlazada;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Jugador extends Entidad implements InputProcessor{

    private ListaEnlazada<Coleccionable> coleccionables; //Aqui es una estructura de coleccionables
    private Habilidad habilidad; //Aqui es un objeto Habilidad
    private int misiones; //Aqui es una estructura de misiones
    private TextureAtlas atlas;
    private Animation<TextureRegion> animationWalk,animationRest,animationAttack;
    private float stateTime;
    private float CoolDownAttack=0;
    private float CoolDownDash=0;
    private boolean attacking=false;
    private boolean dashing=false;
    private boolean canAttack=true; 
    private boolean canDash=true;
    public boolean moridoBienMorido=false;
   //El Multiplicador nos va a permitir saber por cuanto queremos cambiar ciertos aspectos de nuestro jugador
    private int multiplicador=1;

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
    
    public void animate(Batch batch){
        
        if(this.attacking==true){
            batch.draw(animationAttack.getKeyFrame(stateTime),getSprite().getX(),getSprite().getY());
        }
        if((getVelocidadX()==0 && getVelocidadY()==0) || isCollitedX() || isCollitedY()){
            batch.draw(animationRest.getKeyFrame(stateTime),getSprite().getX(),getSprite().getY());
        }else{
            batch.draw(animationWalk.getKeyFrame(stateTime),getSprite().getX(),getSprite().getY());
        }
    
    }
    
    private void startAnimation(){
        atlas = new TextureAtlas("Images/Player/PPCaminando/PPWalk.atlas");
        Array<TextureAtlas.AtlasRegion>  walk = atlas.findRegions("PPCaminando");
        animationWalk = new Animation(0.15f,walk, Animation.PlayMode.LOOP);
        atlas = new TextureAtlas("Images/Player/PPDescanso/PPRest.atlas");
        Array<TextureAtlas.AtlasRegion>  rest = atlas.findRegions("PPDescanso");
        animationRest = new Animation(0.15f,rest, Animation.PlayMode.LOOP);
        atlas = new TextureAtlas("Images/Player/PPMovAtq/PPAttack.atlas");
        Array<TextureAtlas.AtlasRegion>  attack = atlas.findRegions("PPAttack");
        animationAttack = new Animation(0.15f,attack, Animation.PlayMode.LOOP);
        stateTime=0;
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
    public void addStateTime(float delta){
        stateTime += delta;
    }
    
    //Esta funcion nos deja saber si el jugador esta siendo atacado o no
    public void underAttack(boolean auch,float amount){
        if(auch==true){
            if(this.salud>0){
                this.salud=salud-amount;
            }
            else{
                this.dispose();
                this.moridoBienMorido=true;
            }
        }
        else{
        }
    }
    public boolean isAttacking(){
        return this.attacking;}
    
    public void attack(){

        if(System.currentTimeMillis()>CoolDownAttack){
            this.canAttack=true;
            
        }
        if(this.canAttack==true){
            this.CoolDownAttack=System.currentTimeMillis()+1000;
            this.attacking=true;
            this.canAttack=false;
        }           
        System.out.println((this.CoolDownAttack+1000)-this.CoolDownAttack);
    }
      public void dash(){
          this.multiplicador=2;
          this.dashing=true;
          this.canDash=false;
    }
    
    //HAY QUE CORREGIR LOS CONSTRUCTORES PONIENDO EL TIPO DE DATO CORRECTO
    public Jugador(ListaEnlazada<Coleccionable> coleccionables, int misiones, int velocidad, int estamina, int alcance, int suerte, int velocidadAtaque, int ataque, int salud, Habilidad habilidad) {
        super(estamina, alcance, suerte, velocidadAtaque, ataque, salud, new Sprite());
        this.coleccionables = coleccionables;
        this.habilidad = habilidad;
        this.misiones = misiones;  
        startAnimation();
    }

    public Jugador(ListaEnlazada<Coleccionable> coleccionables, int misiones, Habilidad habilidad) {
        super();
        this.coleccionables = coleccionables;
        this.habilidad = habilidad;
        this.misiones = misiones;
        startAnimation();
    }


    //Arreglar el constructor nulo den jugador
    public Jugador(){
        super();
        this.coleccionables = new ListaEnlazada<Coleccionable>(); //NO HACERLO NULO
        this.habilidad = new Humanas(); //NO HACERLO NULO
        //this.misiones = new Estructura(); //NO HACERLO NULO
        startAnimation();
    }

        @Override
    public boolean keyDown(int keycode) {
        switch(keycode){
            //Shift para dar un Dash que se implementara mas tarde xd
            case Input.Keys.SHIFT_LEFT:
               dash();
                break;
            case Input.Keys.E:
                attack();
                break;
            case Input.Keys.W:
               if (!isCollitedY()){
                    setVelocidadY(getSpeed()*multiplicador);
                }
                break;
            case Input.Keys.A:
                if(!isCollitedX()){
                    setVelocidadX(-getSpeed()*multiplicador);
                }
                sprite.flip(true, false);
                break;
            case Input.Keys.S:
                if(!isCollitedY()){
                    setVelocidadY(-getSpeed()*multiplicador);
                }
                break;
            case Input.Keys.D:
                if(!isCollitedX()){
                    setVelocidadX(getSpeed()*multiplicador);
                }
                sprite.flip(true, false);
                break;
        }        
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch(keycode){
            case Input.Keys.SHIFT_LEFT:
                this.dashing=false;
                this.multiplicador=1;
                break;
            case Input.Keys.E:
                this.attacking=false;
                break;
            case Input.Keys.W:
                setVelocidadY(0);
                break;
            case Input.Keys.S:
                setVelocidadY(0);
                break;
            case Input.Keys.A:
                setVelocidadX(0);
                break;
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

