package Screens;

import Screens.Splash;
import Screens.Levels;
import com.badlogic.gdx.Game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;

import Entities.Ciencias;
import Entities.Coleccionable;
import Entities.Enemigo;
import Entities.Humanas;
import Entities.Jugador;

public class ScreenSImon extends Game {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Stage stage;
    private InputProcessor processorButton;

    //private Humanas habilidadHum; //Esto tampoco deberia estar aqui
    private TextButton button;
    Jugador jugador;
    Enemigo enemigo;
    Coleccionable coleccionable; //Esto debería ser una lista de coleccionables
    int coleccionables; //Nos dice cuantos coleccionables en pantalla hay





    @Override
    public void create () {
        //creamos el satge
        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage); //Aniadimos el listener

        //Ponemos la camara
        camera = new OrthographicCamera();
        camera.setToOrtho(false,800,480);

        //Ponemos spritebatch
        batch = new SpriteBatch();

        //Creamos las habilidades
        Humanas habilidadHum = new Humanas();
        Ciencias habilidadCien = new Ciencias();
        habilidadCien.setVelocidad(100);

        // PONEMOS LA IMAGEN/SKIN DEL JUADOR
        this.jugador = new Jugador();
        this.processorButton = this.stage;
        this.jugador.setHabilidad(habilidadCien);
        this.jugador.setVelocidadX(jugador.getHabilidad().getVelocidad()); //le damos una velocidad inicial arbitraria
        this.jugador.setVelocidadY(jugador.getHabilidad().getVelocidad());
        jugador.setSprite(new Sprite(new Texture(Gdx.files.internal("Images/Character5_face1.png"))));



        //Ponemos el rectangulo para nuestro jugador
        jugador.getSprite().setX(800 / 2 - 64 / 2);   //Aqui lo estamos centrando horizontalmente
        jugador.getSprite().setY(480/2 - 64/2); //Lo dejamos 20 pixeles sobre el borde
        jugador.getSprite().setRegionWidth( 64);
        jugador.getSprite().setRegionHeight(64);

        //this.buttonAtack = jugador.button;

        this.enemigo = new Enemigo();

        this.coleccionable = new Coleccionable();
        this.coleccionable.setVisible(true);

    }

    public void mostrarBoton(TextButton button){
        this.button = button;
        this.stage.addActor(this.button);
    }

    public void perfoAtaqueDis(boolean pressedScreen, float delta){
        if(Gdx.input.isKeyJustPressed(Input.Keys.Q)){
            if(!this.jugador.getHabilidad().getAtaqueDistancia().isActivo()){ //Esto le metio un culdown :v
                TextButton newButton = this.jugador.getHabilidad().getAtaqueDistancia().ataqueDistancia(false,pressedScreen,jugador,true,delta);
                mostrarBoton(newButton);
            }

        }
        else if(this.jugador.getHabilidad().getAtaqueDistancia().isActivo() ){
            boolean pressedButton = this.button.isPressed();
            TextButton newButton=  this.jugador.getHabilidad().getAtaqueDistancia().ataqueDistancia(pressedButton,pressedScreen,jugador,false,delta);
            boolean buttonActive = this.jugador.getHabilidad().getAtaqueDistancia().isButtonActivo();
            this.button.setVisible(buttonActive);
            if(newButton != null){
                this.jugador.getHabilidad().getAtaqueDistancia().setButtonActivo(true);
                mostrarBoton(newButton);
            }


        }
    }


    @Override
    public void render () {
        ScreenUtils.clear(0,0,0,0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(jugador.getSprite().getTexture(),jugador.getSprite().getX(), jugador.getSprite().getY());
        if(coleccionable!= null){
            //batch.draw(coleccionable.getTexture(), Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
            if(coleccionable.isVisible()){
                batch.draw(coleccionable.getTexture(), coleccionable.getRectangle().getX(),coleccionable.getRectangle().getY() );
            }

        }
        batch.end();
        this.stage.act();
        boolean pressedScreen = Gdx.input.isButtonJustPressed(Input.Buttons.LEFT);
        float delta = Gdx.graphics.getDeltaTime();
        perfoAtaqueDis(pressedScreen,delta);
        this.stage.draw();
        this.jugador.caminar();
        if(Gdx.input.isKeyJustPressed(Input.Keys.E)){
            jugador.getHabilidad().getAtaqueCorto().ataqueCorto(jugador, enemigo);
            System.out.println("Atrib enemies: "+ "sal:"+enemigo.getSalud() + " alc:"+enemigo.getAlcance());
        }
        if(coleccionable!=null){
            if(coleccionable.recoger(jugador)){
                this.coleccionable = null;
            };
        }


    }

    @Override
    public void dispose () {
        jugador.dispose();
        enemigo.dispose();
        coleccionable.dispose();
    }
}