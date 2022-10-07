/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Maps;

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
    
    private float speed = 60*2, gravity = 60*1.8f;
    private TiledMapTileLayer collisionLayer;
    
    public Player(Sprite sprite, TiledMapTileLayer collisionLayer){
        super(sprite);
        this.collisionLayer = collisionLayer;
        velocity.x=0;
        velocity.y=0;
    }
    
    public void draw(Batch spriteBatch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(spriteBatch);
    }
    
    public void update(float delta){

        float tileWidth = collisionLayer.getWidth(),tileHeight = collisionLayer.getHeight();
           
        actualiceX(delta, tileWidth,tileHeight);
        actualiceY(delta, tileWidth,tileHeight); 
        
    }
    
    private void actualiceY(float delta, float width, float height){
        float oldY = getY();
        boolean collitedY = false;  
        
        setY(oldY+velocity.y*delta);
        
        if(oldY!=getY()){
            if(velocity.y<0){
                //BottomLeft
                collitedY = cellIsBlocked(((getX())/width), ((getY()-getHeight()/2)/height));
                //BottomMidle
                if(!collitedY){
                    collitedY = cellIsBlocked(((getX()+getWidth()/2)/width), ((getY()-getHeight()/2)/height));
                }
                //BottomRight
                if(!collitedY){                
                    collitedY = cellIsBlocked(((getX()+getWidth())/width), ((getY()-getHeight()/2)/height));
                }

            }else if(velocity.y>0){
                //topLeft
                collitedY = cellIsBlocked((getX()/width), (getY()+getHeight())/height);

                //TopMidle
                if(!collitedY)
                    collitedY =  cellIsBlocked((getX()+getWidth()/2)/width, (getY()+getHeight())/height);           
                //TopRight
                if(!collitedY)
                    collitedY = cellIsBlocked((getX()+getWidth())/width, (int) (getY()+getHeight())/height);
            }
            if(collitedY){
                setY(oldY);
                velocity.y = 0;
            }
        }
    }
    
    private void actualiceX(float delta, float width, float height){
        float oldX = getX(); 
        boolean collitedX = false;
        setX(getX()+velocity.x*delta);
        if(oldX!=getX()){
            if(velocity.x<0){
                //topLeft
                collitedX = cellIsBlocked(getX()/width, (getY()+getHeight())/height);
                //midleLeft
                if(!collitedX)
                    collitedX = cellIsBlocked((getX()/width),((getY()+getHeight()/2)/height));
                //buttomLeft
                if(!collitedX)
                    collitedX = cellIsBlocked( (getX()/width), ((getY())/height));
            }else if(velocity.x>0){
                //topRight
                collitedX = cellIsBlocked( ((getX()+getWidth())/width),  ((getY()+getHeight())/height));
                //midleRight
                if(!collitedX)
                    collitedX = cellIsBlocked(((getX()+getWidth())/width),  ((getY()+getHeight()/2)/height));
                //buttomRight
                if(!collitedX)
                    collitedX = cellIsBlocked(((getX()+getWidth())/width), ((getY())/height));

            }
            if(collitedX){
                setX(oldX);
                velocity.x = 0;
            }
        }
        
    }
    
    public Vector2 getVelocity() {
        return velocity;
    }

    public float getSpeed() {
        return speed;
    }

    public float getGravity() {
        return gravity;
    }

    public TiledMapTileLayer getCollisionLayer() {
        return collisionLayer;
    }

    private boolean cellIsBlocked(float x, float y) {
        Cell cell = collisionLayer.getCell((int) x, (int) y);
        return (cell!=null)&&(cell.getTile()!=null)&&(cell.getTile().getProperties().containsKey("blocked"));
    }

    @Override
    public boolean keyDown(int keycode) {
        switch(keycode){
            case Keys.W:
                velocity.y = speed;
                break;
            case Keys.A:
                velocity.x = -speed;
                break;
            case Keys.S:
                velocity.y = -speed;
                break;
            case Keys.D:
                velocity.x = speed;
                break;
        }
                
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch(keycode){
            case Keys.W:
            case Keys.S:
                velocity.y= 0;
            case Keys.A:
            case Keys.D:
                velocity.x= 0;
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
    
}
