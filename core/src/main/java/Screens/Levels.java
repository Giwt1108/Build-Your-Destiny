/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import Maps.Room;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import Entities.Player;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 * @author Sebastian
 */
public class Levels implements Screen {
    
    private OrthographicCamera camera;
    private Room room;
    private OrthogonalTiledMapRenderer renderer;
    private Sprite sprite = new Sprite(new Texture("Images/Player.png"));
    private Player player;
    

    @Override
    public void show() { 
        camera = new OrthographicCamera(); 
        room = new Room("Maps/Room2.tmx", new Player(sprite));
        
        renderer = room.getRenderer(); 
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        camera.position.set(room.getPlayer().getX()+room.getPlayer().getWidth()/2,room.getPlayer().getY()+room.getPlayer().getHeight()/2,0);
        camera.update();
        
        room.render(camera);
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
    
}
