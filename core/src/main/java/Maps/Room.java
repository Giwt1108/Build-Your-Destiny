/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Maps;

import Entities.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.utils.Array;
import java.util.Iterator;



/**
 *
 * @author Sebastian
 */
public class Room {
    
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private TiledMapTileLayer collisionLayer;
    private Player player;
    
    private final float scaleX;
    private final float scaleY;
    
    private final float width;
    private final float height;
    
    public Room(String fileName, Player player){
        map = new TmxMapLoader().load(fileName);
        renderer = new OrthogonalTiledMapRenderer(map); 
        collisionLayer = getCollitionLayer();
        width = collisionLayer.getWidth();
        height = collisionLayer.getHeight();
        this.player = player;
        this.player.setPosition(6*width, 6*height);
        
        Gdx.input.setInputProcessor(this.player);
        
        scaleX = ((float) collisionLayer.getWidth())/collisionLayer.getTileWidth();
        scaleY = ((float) collisionLayer.getHeight())/collisionLayer.getTileHeight();
        
        initAnimation();
        
        
    }
    
    private void initAnimation(){
        Array<StaticTiledMapTile> frameTiles = new Array<StaticTiledMapTile>(3);
        
        Iterator<TiledMapTile> tiles = map.getTileSets().getTileSet("Room").iterator();
        while(tiles.hasNext()){
            TiledMapTile tile = tiles.next();
            if(tile.getProperties().containsKey("animation") && tile.getProperties().get("animation", String.class).equals("Fire")){
                frameTiles.add((StaticTiledMapTile) tile);
            }
        }
        
        AnimatedTiledMapTile animatedTile = new AnimatedTiledMapTile(1/8f, frameTiles);
        
        for(int x = 0; x<width;x++){
            for(int y = 0; y<height;y++){
                Cell cell = collisionLayer.getCell(x, y);
                if((cell !=null) && (cell.getTile()!=null)&&(cell.getTile().getProperties().containsKey("animation"))&& cell.getTile().getProperties().get("animation", String.class).equals("Fire")){
                    cell.setTile(animatedTile);
                }
            }
        }
    }
    
    public void render(OrthographicCamera camera){
        float oldX, oldY;
        renderer.setView(camera);
        renderer.render();
        
        renderer.getBatch().begin();
        oldX = player.getX();
        oldY = player.getY();
        player.update(Gdx.graphics.getDeltaTime());
        actualicePlayerX(oldX);
        actualicePlayerY(oldY);
        player.draw(renderer.getBatch());
        renderer.getBatch().end();  
    }
    
    public void dispose(){
        map.dispose();
        renderer.dispose();
    }
    
    public OrthogonalTiledMapRenderer getRenderer(){
        return renderer;
    }
    public TiledMapTileLayer getCollitionLayer(){
        return (TiledMapTileLayer) map.getLayers().get(0);
    }
    
    public void checkColitions(){
        
    }
    
    private void actualicePlayerY(float oldY){
        player.setCollitionY(false);
        if(oldY!=player.getY()){
            player.setCollitionY(false);
            if(player.getVelocity().y<0){
                //BottomLeft
                player.setCollitionY(cellIsBlocked(((player.getX())/width), ((player.getY())/height)));
                //BottomMidle
                if(!player.getCollitionY()){
                    player.setCollitionY(cellIsBlocked(((player.getX()+player.getWidth()/2)/width), ((player.getY())/height)));
                }
                //BottomRight
                if(!player.getCollitionY()){                
                    player.setCollitionY(cellIsBlocked(((player.getX()+player.getWidth())/width), (player.getY())/height));
                }

            }else if(player.getVelocity().y>0){
                //topLeft
                player.setCollitionY(cellIsBlocked((player.getX()/width), (player.getY()+player.getHeight())/height));

                //TopMidle
                if(!player.getCollitionY())
                    player.setCollitionY(cellIsBlocked((player.getX()+player.getWidth()/2)/width, (player.getY()+player.getHeight())/height));           
                //TopRight
                if(!player.getCollitionY())
                    player.setCollitionY(cellIsBlocked((player.getX()+player.getWidth())/width, (int) (player.getY()+player.getHeight())/height));
            }
            if(player.getCollitionY()){
                player.setY(oldY);
                player.setVelocityY(0);
                //System.out.println("Blocked y");
            }
        }
    }
    
    private void actualicePlayerX(float oldX){
        player.setCollitionX(false);
        if(oldX!=player.getX()){
            if(player.getVelocity().x<0){
                //topLeft
                player.setCollitionX(cellIsBlocked(player.getX()/width, (player.getY()+player.getHeight())/height));
                //midleLeft
                if(!player.getCollitionX())
                    player.setCollitionX(cellIsBlocked((player.getX()/width),((player.getY()+player.getHeight()/2)/height)));
                //buttomLeft
                if(!player.getCollitionX())
                    player.setCollitionX( cellIsBlocked( (player.getX()/width), ((player.getY())/height)));
            }else if(player.getVelocity().x>0){
                //topRight
                player.setCollitionX(cellIsBlocked( ((player.getX()+player.getWidth())/width),  ((player.getY()+player.getHeight())/height)));
                //midleRight
                if(!player.getCollitionX())
                    player.setCollitionX(cellIsBlocked(((player.getX()+player.getWidth())/width),  ((player.getY()+player.getHeight()/2)/height)));
                //buttomRight
                if(!player.getCollitionX())
                    player.setCollitionX(cellIsBlocked(((player.getX()+player.getWidth())/width), ((player.getY())/height)));

            }
            if(player.getCollitionX()){
                player.setX(oldX);
                player.setVelocityX(0);
                //System.out.println("Blocked x");
            }
        }
        
    }
    
    private boolean cellIsBlocked(float x, float y) {
        //System.out.println(x+", "+y +"//" + scaleX+", "+scaleY);
        TiledMapTileLayer.Cell cell = collisionLayer.getCell((int) (scaleX*x), (int) (scaleY*y));
        return (cell!=null)&&(cell.getTile()!=null)&&(cell.getTile().getProperties().containsKey("blocked"));
    }
    
    public Player getPlayer(){
        return player;
    }
}
