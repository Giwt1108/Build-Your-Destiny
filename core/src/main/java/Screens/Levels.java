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
import Maps.Player;
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
        room = new Room();
        renderer = room.getRenderer();
        player = new Player(sprite,room.getCollitionLayer());
        player.setPosition(6*player.getCollisionLayer().getTileWidth(), 6*player.getCollisionLayer().getTileHeight());
        
        Gdx.input.setInputProcessor(player);
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        room.render(camera);
        
        renderer.getBatch().begin();
        player.draw(renderer.getBatch());
        renderer.getBatch().end();
    }

    @Override
    public void resize(int i, int i1) {
        camera.viewportWidth = i;
        camera.viewportHeight = i1;
        camera.update();
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
