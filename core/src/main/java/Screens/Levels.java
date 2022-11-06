/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Screens;

import Entities.Ciencias;
import Entities.Coleccionable;
import Entities.Enemies.LinkedCroc;
import Entities.Humanas;
import Entities.Jugador;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import Maps.Room;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import estructuras.DoubleLinkedList;
import estructuras.DoubleNode;

import Maps.TmxGestor;

/**
 *
 * @author Sebastian
 */
public class Levels implements Screen {
    
    private OrthographicCamera camera;
    private Room room;
    private OrthogonalTiledMapRenderer renderer;
    private Jugador player;
    private LinkedCroc enemy;
    private boolean buttonState = false;
    
    private Stage stage;
    private TextButton button;
    private DoubleLinkedList<Coleccionable>  coleccionables;
    protected InputMultiplexer inputMultiplexer = new InputMultiplexer();
    
    private TmxGestor mapGestor;
    
    @Override
    public void show() { 
        
        mapGestor = new TmxGestor(1,5,4);
        mapGestor.writeTmx();
        
        camera = new OrthographicCamera(); 

        stage = new Stage();
        
        init();
        
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(player);
        Gdx.input.setInputProcessor(inputMultiplexer);
        
        room = new Room("Maps/level1.tmx", player, enemy,coleccionables);

        renderer = room.getRenderer();
        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());

    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        camera.position.set(room.getPlayer().getSprite().getX()+room.getPlayer().getSprite().getWidth()/2,room.getPlayer().getSprite().getY()+room.getPlayer().getSprite().getHeight()/2,0);
        camera.update();
        
        room.render(camera,stage,this);
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width/1.3f;
        camera.viewportHeight = height/1.3f;
    }

    @Override
    public void pause() {
        
    }

    @Override
    public void resume() {
        
    }

    @Override
    public void hide() {
        room.dispose();
    }

    @Override
    public void dispose() {
        room.dispose();
    }

    public boolean isButtonState() {
        return buttonState;
    }

    public void setButtonState(boolean buttonState) {
        this.buttonState = buttonState;
    }
    
    public void init(){
        initPlayer();
        initEnemy();
        initColeccionables(10);
    }
    
    public void initPlayer(){
        //Creamos las habilidades
        Humanas habilidadHum = new Humanas();
        Ciencias habilidadCien = new Ciencias();
        habilidadCien.setVelocidad(100);
        
        // PONEMOS LA IMAGEN/SKIN DEL JUGADOR
        player = new Jugador();
        
        player.setHabilidad(habilidadCien);
        player.setSpeed(player.getSpeed()); //le damos una velocidad inicial arbitraria
        player.setSprite(new Sprite(new Texture(Gdx.files.internal("Images/Player/PersonajePrincipal.png"))));

        //Ponemos el rectangulo para nuestro player
        float x,y;
        
        x = mapGestor.getInitPoint()[1]*32*32+512;
        y = (5-mapGestor.getInitPoint()[0])*32*32+512;
        
        player.setX(x);   //Aqui lo estamos centrando horizontalmente
        player.setY(y); //Lo dejamos 20 pixeles sobre el borde
        player.setRegionWidth( 32);
        player.setRegionHeight(32);
        player.getSprite().setX(x);   //Aqui lo estamos centrando horizontalmente
        player.getSprite().setY(y); //Lo dejamos 20 pixeles sobre el borde
        player.getSprite().setRegionWidth( 32);
        player.getSprite().setRegionHeight(32);
    }
    public void initEnemy(){
        // skin del primer croco
        enemy = new LinkedCroc();
        enemy.setSpeed(enemy.getSpeed()); //le damos una velocidad inicial arbitraria
        enemy.setSprite(new Sprite(new Texture(Gdx.files.internal("Images/Cocodrile/enemiCocodrile.png"))));
        
        //Rectangulo del primer croco
        enemy.setX(1000 / 2 - 128 / 2);   //Aqui lo estamos centrando horizontalmente
        enemy.sprite.setX(1000 / 2 - 128 / 2);   //Aqui lo estamos centrando horizontalmente
        enemy.setY(680/2 - 128/2); //Lo dejamos 20 pixeles sobre el borde
        enemy.sprite.setY(680/2 - 128/2); //Lo dejamos 20 pixeles sobre el borde
        enemy.getSprite().setRegionWidth( 64);
        enemy.getSprite().setRegionHeight(64);
    }
    
    public void initColeccionables(int num){
        coleccionables = new DoubleLinkedList<Coleccionable>();
        for(int i =0;i<num;i++){
            DoubleNode<Coleccionable> node = new DoubleNode<Coleccionable>(new Coleccionable(30+i*50,30+i*50));
            node.getData().setNode(node);
            node.getData().setVisible(true);
            coleccionables.pushBackNode(node);
        }
    }
    
    public TextButton getButton(){
        return this.button;
    }
    
    public void setButton(TextButton newButton){
        this.button = newButton;
    }
    
}
