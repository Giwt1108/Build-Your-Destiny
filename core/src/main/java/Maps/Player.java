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
    
    private final float speed = 60*2.5f;
    private TiledMapTileLayer collisionLayer;
    private boolean collitedX,collitedY;
    
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
        collitedY = false;  
        
        setY(getY()+velocity.y*delta);
        
        if(oldY!=getY()){
            if(velocity.y<0){
                //BottomLeft
                collitedY = cellIsBlocked(((getX())/width), ((getY())/height));
                //BottomMidle
                if(!collitedY){
                    collitedY = cellIsBlocked(((getX()+getWidth()/2)/width), ((getY())/height));
                }
                //BottomRight
                if(!collitedY){                
                    collitedY = cellIsBlocked(((getX()+getWidth())/width), (getY())/height);
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
                //System.out.println("Blocked y");
            }
        }
    }
    
    private void actualiceX(float delta, float width, float height){
        float oldX = getX(); 
        collitedX = false;
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
                //System.out.println("Blocked x");
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

    public TiledMapTileLayer getCollisionLayer() {
        return collisionLayer;
    }

    private boolean cellIsBlocked(float x, float y) {
        System.out.println((int) x+", "+ (int) y+"||"+ x+", "+ y/3.2);
        Cell cell = collisionLayer.getCell((int) (x/1.6), (int) (y/1.6));
        return (cell!=null)&&(cell.getTile()!=null)&&(cell.getTile().getProperties().containsKey("blocked"));
    }

    @Override
    public boolean keyDown(int keycode) {
        switch(keycode){
            case Keys.W:
                if(!collitedY){
                    velocity.y = speed;
                }
                break;
            case Keys.A:
                if(!collitedX){
                    velocity.x = -speed;
                }
                break;
            case Keys.S:
                if(!collitedY){
                    velocity.y = -speed;
                }
                break;
            case Keys.D:
                if(!collitedX){
                    velocity.x = speed;
                }
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
                break;
            case Keys.A:
            case Keys.D:
                velocity.x= 0;
                break;
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
