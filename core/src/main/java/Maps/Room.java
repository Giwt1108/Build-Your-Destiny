/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Maps;

import Entities.Coleccionable;
import Entities.Jugador;
import Screens.Levels;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import estructuras.DoubleLinkedList;
import estructuras.DoubleNode;
import java.util.Iterator;



/**
 *
 * @author Sebastian
 */
public class Room {
    
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private TiledMapTileLayer collisionLayer;
    private Jugador player;
    private DoubleLinkedList<Coleccionable> coleccionables;
    
    private final float scaleX;
    private final float scaleY;
    
    private final float width;
    private final float height;
    
    public Room(String fileName, Jugador player, DoubleLinkedList<Coleccionable> coleccionables){
        map = new TmxMapLoader().load(fileName);
        renderer = new OrthogonalTiledMapRenderer(map); 
        collisionLayer = getCollisionLayer();
        width = collisionLayer.getWidth();
        height = collisionLayer.getHeight();
        this.player = player;
        
        scaleX = ((float) collisionLayer.getWidth())/collisionLayer.getTileWidth();
        scaleY = ((float) collisionLayer.getHeight())/collisionLayer.getTileHeight();
        
        initAnimation();
        
        this.coleccionables = coleccionables;
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
    
<<<<<<< HEAD
    public void render(OrthographicCamera camera){
=======
    public void render(OrthographicCamera camera, Stage stage, Levels screen){
        float oldX, oldY;
>>>>>>> Unir
        renderer.setView(camera);
        renderer.render();
        
        renderer.getBatch().begin();
<<<<<<< HEAD
        
        player.update(Gdx.graphics.getDeltaTime());
        actualicePlayerX(player.getOldX());
        actualicePlayerY(player.getOldY());
        player.draw(renderer.getBatch());
        renderer.getBatch().end();  
=======
        oldX = player.getSprite().getX();
        oldY = player.getSprite().getY();
        player.caminar();
        actualicePlayerX(oldX);
        actualicePlayerY(oldY);
        DrawColeccionables();
        renderer.getBatch().draw(player.getSprite().getTexture(),player.getSprite().getX(), player.getSprite().getY());
        renderer.getBatch().end();
        boolean pressedScreen = Gdx.input.isButtonJustPressed(Input.Buttons.LEFT);
        float delta = Gdx.graphics.getDeltaTime();
        player.perfoAtaqueDis(pressedScreen,delta,screen,stage);
        stage.draw();
        
>>>>>>> Unir
    }
    
    public void dispose(){
        map.dispose();
        renderer.dispose();
    }
    
    public OrthogonalTiledMapRenderer getRenderer(){
        return renderer;
    }
    public TiledMapTileLayer getCollisionLayer(){
        return (TiledMapTileLayer) map.getLayers().get(0);
    }
    
    public void checkColitions(){
        
    }
    
    public void DrawColeccionables(){
        DoubleNode<Coleccionable> current = coleccionables.getHead();
        DoubleNode<Coleccionable> node = null;
        while(current!=null){
            if(current.getData() != null){
                node = current.getData().draw(renderer.getBatch(), player);
                if(node!= null){
                    coleccionables.popNode(node);
                }
            }
            current = current.getNext();
        }
    }
    
    private void actualicePlayerY(float oldY){
        player.setCollitedY(false);
        if(oldY!=player.getSprite().getY()){
            player.setCollitedY(false);
            if(player.getVelocidad().y<0){
                //BottomLeft
                player.setCollitedY(cellIsBlocked(((player.getSprite().getX())/width), ((player.getSprite().getY())/height)));
                //BottomMidle
                if(!player.isCollitedY()){
                    player.setCollitedY(cellIsBlocked(((player.getSprite().getX()+player.getSprite().getWidth()/2)/width), ((player.getSprite().getY())/height)));
                }
                //BottomRight
                if(!player.isCollitedY()){                
                    player.setCollitedY(cellIsBlocked(((player.getSprite().getX()+player.getSprite().getWidth())/width), (player.getSprite().getY())/height));
                }

            }else if(player.getVelocidad().y>0){
                //topLeft
                player.setCollitedY(cellIsBlocked((player.getSprite().getX()/width), (player.getSprite().getY()+player.getSprite().getHeight())/height));

                //TopMidle
                if(!player.isCollitedY())
                    player.setCollitedY(cellIsBlocked((player.getSprite().getX()+player.getSprite().getWidth()/2)/width, (player.getSprite().getY()+player.getSprite().getHeight())/height));           
                //TopRight
                if(!player.isCollitedY())
                    player.setCollitedY(cellIsBlocked((player.getSprite().getX()+player.getSprite().getWidth())/width, (int) (player.getSprite().getY()+player.getSprite().getHeight())/height));
            }
            if(player.isCollitedY()){
                player.getSprite().setY(oldY);
                player.setVelocidadY(0);
                //System.out.println("Blocked y");
            }
        }
    }
    
    private void actualicePlayerX(float oldX){
        player.setCollitedX(false);
        if(oldX!=player.getSprite().getX()){
            if(player.getVelocidad().x<0){
                //topLeft
                if(!player.isCollitedY()){
                    player.setCollitedX(cellIsBlocked(player.getSprite().getX()/width, (player.getSprite().getY()+player.getSprite().getHeight())/height));
                }
                //midleLeft
                if(!player.isCollitedX())
                    player.setCollitedX(cellIsBlocked((player.getSprite().getX()/width),((player.getSprite().getY()+player.getSprite().getHeight()/2)/height)));
                //buttomLeft
                if(!player.isCollitedX() && !player.isCollitedY())
                    player.setCollitedX( cellIsBlocked( (player.getSprite().getX()/width), ((player.getSprite().getY())/height)));
            }else if(player.getVelocidad().x>0){
                //topRight
                if(!player.isCollitedY()){
                    player.setCollitedX(cellIsBlocked( ((player.getSprite().getX()+player.getSprite().getWidth())/width),  ((player.getSprite().getY()+player.getSprite().getHeight())/height)));
                }
                //midleRight
                if(!player.isCollitedX())
                    player.setCollitedX(cellIsBlocked(((player.getSprite().getX()+player.getSprite().getWidth())/width),  ((player.getSprite().getY()+player.getSprite().getHeight()/2)/height)));
                //buttomRight
                if(!player.isCollitedX() && !player.isCollitedY())
                    player.setCollitedX(cellIsBlocked(((player.getSprite().getX()+player.getSprite().getWidth())/width), ((player.getSprite().getY())/height)));

            }
            if(player.isCollitedX()){
                player.getSprite().setX(oldX);
                player.setVelocidadX(0);
                //System.out.println("Blocked x");
            }
        }
        
    }
    
    private boolean cellIsBlocked(float x, float y) {
        //System.out.println(x+", "+y +"//" + scaleX+", "+scaleY);
        TiledMapTileLayer.Cell cell = collisionLayer.getCell((int) (scaleX*x), (int) (scaleY*y));
        return (cell!=null)&&(cell.getTile()!=null)&&(cell.getTile().getProperties().containsKey("blocked"));
    }
    
    public Jugador getPlayer(){
        return player;
    }
}
