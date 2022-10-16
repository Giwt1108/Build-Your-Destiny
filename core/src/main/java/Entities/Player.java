/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Sebastian
 */
public class Player extends Sprite implements InputProcessor{
    private Vector2 velocity = new Vector2();
    
    private final float speed = 60*5f;
    private TiledMapTileLayer collisionLayer;
    private boolean collitedX,collitedY;
    
    private float oldX;
    private float oldY;
    
    public Player(Sprite sprite){
        super(sprite);
        
        velocity.x=0;
        velocity.y=0;
        
        collitedX = false;
        collitedY = false;
        
    }
    
    @Override
    public void draw(Batch spriteBatch) {
        super.draw(spriteBatch);
    }
    
    public void update(float delta){
        oldX = getX();
        oldY = getY();
        setX(getX()+velocity.x*delta);
        setY(getY()+velocity.y*delta);
  
    }
    
    
    public Vector2 getVelocity() {
        return velocity;
    }
    
    public void setVelocityX(float velX){
        velocity.x = velX;
    }
    
    public void setVelocityY(float velY){
        velocity.x = velY;
    }
    
    public float getSpeed() {
        return speed;
    }

    public TiledMapTileLayer getCollisionLayer() {
        return collisionLayer;
    }
    
    public void setCollitionX(boolean collision){
        collitedX = collision;
    }
    
    public void setCollitionY(boolean collision){
        collitedY = collision;
    }
    
    public boolean getCollitionX(){
        return collitedX;
    }
    
    public boolean getCollitionY(){
        return collitedY;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch(keycode){
            case Keys.W:
                if(!collitedY){
                    velocity.y = speed;
                }
            //    break;
            case Keys.A:
                if(!collitedX){
                    velocity.x = -speed;
                }
                
            case Keys.S:
                if(!collitedY){
                    velocity.y = -speed;
                }
                
            case Keys.D:
                if(!collitedX){
                    velocity.x = speed;
                }
                
        }
                
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch(keycode){
            case Keys.W:
            
            case Keys.S:
     
            case Keys.A:

            case Keys.D:
 
        }
        return true;
    }

    @Override
    public boolean keyTyped(char c) {
        return true;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return true;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return true;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return true;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return true;
    }

    @Override
    public boolean scrolled(float f, float f1) {
        return true;
    }
    
    public float getOldX(){
        return oldX;
    }
    
    public float getOldY(){
        return oldY;
    }
    
}
