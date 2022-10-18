/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Maps;

import Entities.AtaqueCorto;
import Entities.Coleccionable;
import Entities.Enemies.LinkedCroc;
import Entities.Enemies.QueueGusanin;
import Entities.Jugador;
import Screens.Levels;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
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
    private Music music= Gdx.audio.newMusic(Gdx.files.internal("Music/GameloopOne.mp3"));
    private TiledMapTileLayer collisionLayer;
    private Jugador player;
    //Texturas de la interfaz
    private Texture blank= new Texture(Gdx.files.internal("Images/blank.png"));
    private Texture playerLife=new Texture(Gdx.files.internal("Images/green.png"));
    private Texture playerBackPackTexture=new Texture(Gdx.files.internal("Images/backpack.png"));
    private Texture optionsIconTexture=new Texture(Gdx.files.internal("Images/options.png"));
    private TextureRegion PBRegion,ORegion;
    private TextureRegionDrawable PBRegionD, ORegionD;
    //Botones
    private Button playerBackPack,options;
    //cooldowns y pausa
    private long CoolBaby= System.currentTimeMillis()+15000;
    private long CoolDownAttack=System.currentTimeMillis()+5000;
    private long CoolDownDash=System.currentTimeMillis()+5000;
    private boolean pause=false;
    private boolean fullScreen=true;
    
    private int amountOfCrocos=0;
    private LinkedCroc [] Crocos= new LinkedCroc[20];
    private QueueGusanin pepe;
    private DoubleLinkedList<Coleccionable> coleccionables;
    
    private final float scaleX;
    private final float scaleY;
    
    private final float width;
    private final float height;
    
    public Room(String fileName, Jugador player, LinkedCroc enemy, DoubleLinkedList<Coleccionable> coleccionables){
        map = new TmxMapLoader().load(fileName);
        renderer = new OrthogonalTiledMapRenderer(map); 
        collisionLayer = getCollisionLayer();
        width = collisionLayer.getWidth();
        height = collisionLayer.getHeight();
        this.player = player;
        
        //Configuramos Botones
        PBRegion= new TextureRegion(playerBackPackTexture);
        ORegion= new TextureRegion(optionsIconTexture);
        PBRegionD= new TextureRegionDrawable(PBRegion);
        ORegionD= new TextureRegionDrawable(ORegion);
        playerBackPack= new ImageButton(PBRegionD);
        options= new ImageButton(ORegionD);
        
        playerBackPack.addListener(new ClickListener() {
         @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.exit();
            }
        });
        
        options.addListener(new ClickListener() {
         @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.exit();
            }
        });
        playerBackPack.setTouchable(Touchable.enabled);
        options.setTouchable(Touchable.enabled);
        playerBackPack.setWidth(64);
        playerBackPack.setHeight(64);
        options.setWidth(64);
        options.setHeight(64);

        //Iniciamos el primer cococdrilo
        this.Crocos[0]= enemy;
        amountOfCrocos++;
        Crocos[0].sprite.setX(player.sprite.getX()+10);
        Crocos[0].sprite.setY(player.sprite.getY()+10);
        scaleX = ((float) collisionLayer.getWidth())/collisionLayer.getTileWidth();
        scaleY = ((float) collisionLayer.getHeight())/collisionLayer.getTileHeight();
        
        // skin del primer gusano
        pepe = new QueueGusanin();
        pepe.setSpeed(pepe.getSpeed()); //le damos una velocidad inicial arbitraria
        pepe.setSprite(new Sprite(new Texture(Gdx.files.internal("Images/Gusano/Gusano-Caminando1.png"))));
        
        //Configuramos la musica
        music.setLooping(true);
        music.setVolume(0.5f);
        music.play();
        initAnimation();
       
        this.coleccionables = coleccionables;
    }
    
    private void initAnimation(){
        Array<StaticTiledMapTile> frameTilesUp = new Array<StaticTiledMapTile>(3);
        Array<StaticTiledMapTile> frameTilesDown = new Array<StaticTiledMapTile>(3);
        Array<StaticTiledMapTile> frameTilesTorchDown = new Array<StaticTiledMapTile>(3);

        
        Iterator<TiledMapTile> tiles = map.getTileSets().getTileSet("Room").iterator();
        while(tiles.hasNext()){
            TiledMapTile tile = tiles.next();
            boolean containsAnimation = tile.getProperties().containsKey("animation");
            String value = tile.getProperties().get("animation", String.class);
            if(containsAnimation && value.equals("FireUp")){
                frameTilesUp.add((StaticTiledMapTile) tile);
            }else if(containsAnimation && value.equals("FireDown")){
                frameTilesDown.add((StaticTiledMapTile) tile);
            }else if(containsAnimation && value.equals("TorchDown")){
                frameTilesTorchDown.add((StaticTiledMapTile) tile);
            }
        }
        
        AnimatedTiledMapTile animatedTileUp = new AnimatedTiledMapTile(1/6f, frameTilesUp);
        AnimatedTiledMapTile animatedTileDown = new AnimatedTiledMapTile(1/6f, frameTilesDown);
        AnimatedTiledMapTile animatedTileTorchDown = new AnimatedTiledMapTile(1/6f, frameTilesTorchDown);
        
        for(int x = 0; x<width;x++){
            for(int y = 0; y<height;y++){
                Cell cell = collisionLayer.getCell(x, y);
                if((cell !=null) && (cell.getTile()!=null)&&(cell.getTile().getProperties().containsKey("animation"))&& cell.getTile().getProperties().get("animation", String.class).equals("FireUp")){
                    cell.setTile(animatedTileUp);
                }else if((cell !=null) && (cell.getTile()!=null)&&(cell.getTile().getProperties().containsKey("animation"))&& cell.getTile().getProperties().get("animation", String.class).equals("FireDown")){
                    cell.setTile(animatedTileDown);
                }else if((cell !=null) && (cell.getTile()!=null)&&(cell.getTile().getProperties().containsKey("animation"))&& cell.getTile().getProperties().get("animation", String.class).equals("TorchDown")){
                    cell.setTile(animatedTileTorchDown);
                }
            }
        }
    }
    
    public void render(OrthographicCamera camera, Stage stage, Levels screen){
        boolean paused = Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE);
        boolean full = Gdx.input.isKeyJustPressed(Input.Keys.F1);
        if(this.fullScreen==true && full==true){
            Gdx.graphics.setWindowedMode(960, 720);
            Gdx.graphics.setResizable(true);
            this.fullScreen=false;
        }
        else if(this.fullScreen==false && full==true){
            Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
            this.fullScreen=true;
        }
        if(this.pause==true && paused==true){
                this.pause=false;
        }
        else if(this.pause==false && paused==true){
            this.pause=true;
        }
        if(this.pause==false){
            float oldX, oldY;
            renderer.getBatch().setColor(Color.WHITE);
            renderer.setView(camera);
            renderer.render();
            renderer.getBatch().begin();
             //Renderizamos a todos los cocodrilos
            renderCrocks();
            //pepe.draw(renderer.getBatch());
           //Render de interfaz
           int damageBar=((Gdx.graphics.getWidth()/4)/100)*(int)player.getSalud() ;
           renderer.getBatch().draw(blank, player.sprite.getX()-Gdx.graphics.getWidth()/3, player.sprite.getY()+Gdx.graphics.getHeight()/3,Gdx.graphics.getWidth()/4,20);
           renderer.getBatch().draw(playerLife, player.sprite.getX()-Gdx.graphics.getWidth()/3, 5+player.sprite.getY()+Gdx.graphics.getHeight()/3,damageBar,10);
           options.setPosition((float) (player.getSprite().getX()+Gdx.graphics.getWidth()/3.3), (float) (player.getSprite().getY()+Gdx.graphics.getHeight()/3.3));
           options.draw(renderer.getBatch(), 20);
           playerBackPack.setPosition(player.getSprite().getX()+Gdx.graphics.getWidth()/4, (float) (player.getSprite().getY()+Gdx.graphics.getHeight()/3.3));
           playerBackPack.draw(renderer.getBatch(), 20);

            oldX = player.getSprite().getX();
            oldY = player.getSprite().getY();
            player.caminar();
            actualicePlayerX(oldX);
            actualicePlayerY(oldY);
            DrawColeccionables();
            player.animate(renderer.getBatch());
            player.addStateTime(Gdx.graphics.getDeltaTime()); 
            renderer.getBatch().end();
            boolean pressedScreen = Gdx.input.isButtonJustPressed(Input.Buttons.LEFT);
            float delta = Gdx.graphics.getDeltaTime();
            //Comprobamos Estados del Juego
            player.perfoAtaqueDis(pressedScreen,delta,screen,stage);
            if(player.moridoBienMorido==true){
                System.out.println("NOOOooooo te moriste");
                Gdx.app.exit();
            }
        }
        else{
            renderer.setView(camera);
            renderer.getBatch().setColor(Color.GRAY);
            renderer.render();
            renderer.getBatch().begin();
           //Render de interfaz
            int damageBar=((Gdx.graphics.getWidth()/4)/100)*(int)player.getSalud() ;
            renderer.getBatch().draw(blank, player.sprite.getX()-Gdx.graphics.getWidth()/3, player.sprite.getY()+Gdx.graphics.getHeight()/3,Gdx.graphics.getWidth()/4,20);
            renderer.getBatch().draw(playerLife, player.sprite.getX()-Gdx.graphics.getWidth()/3, 5+player.sprite.getY()+Gdx.graphics.getHeight()/3,damageBar,10);
            options.setPosition((float) (player.getSprite().getX()+Gdx.graphics.getWidth()/3.3), (float) (player.getSprite().getY()+Gdx.graphics.getHeight()/3.3));
            options.draw(renderer.getBatch(), 20);
            playerBackPack.setPosition(player.getSprite().getX()+Gdx.graphics.getWidth()/4, (float) (player.getSprite().getY()+Gdx.graphics.getHeight()/3.3));
            playerBackPack.draw(renderer.getBatch(), 20);
            DrawColeccionables();
            renderer.getBatch().end();
        }
        
        
        stage.draw();     
    }
    //Se renderizan todos los Cocodrilos y en caso de que se haya terminado el cooldown del Cocodrilo padre se crea un nuevo cocodrilo en el primer if
    public void renderCrocks(){
        float oldXE,oldYE;
        if(Crocos[0].babyExplotion() && this.CoolBaby<System.currentTimeMillis()&& amountOfCrocos<19 && Crocos[0].isAlive() ){
             Crocos[amountOfCrocos]= new LinkedCroc();
             float place= (float) (Math.random()*3+1);
             Crocos[amountOfCrocos].sprite.setX(Crocos[0].sprite.getX()+ place);
             Crocos[amountOfCrocos].sprite.setY(Crocos[0].sprite.getY()+ place);
             this.CoolBaby=System.currentTimeMillis()+15000;
             amountOfCrocos++;
            }
        for(int i=0;i<amountOfCrocos;i++){
            AtaqueCorto atk= new AtaqueCorto(); 
             atk.ataqueCorto(player, Crocos[i]);
             if(Crocos[i].isAlive()){
            oldXE = Crocos[i].getSprite().getX();
            oldYE = Crocos[i].getSprite().getY();
            Crocos[i].caminar();
            actualiceEnemyX(oldXE,Crocos[i]);
            actualiceEnemyY(oldYE,Crocos[i]);
            Crocos[i].addStateTime(Gdx.graphics.getDeltaTime());
            Crocos[i].animate(renderer.getBatch());
            //revisa si el cocodrilo en la posicion i esta atacando el jugador y si si le quita daño
            player.underAttack(Crocos[i].playerNear(player.sprite.getX(), player.sprite.getY()),0.1f);
             }
        }

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
    //Se revisan las colisiones del cocodrilo
    private void actualiceEnemyY(float oldY,LinkedCroc enemy){
        enemy.setCollitedY(false);
        if(oldY!=enemy.getSprite().getY()){
            enemy.setCollitedY(false);
            if(enemy.getVelocidad().y<0){
                //BottomLeft
                enemy.setCollitedY(cellIsBlocked(((enemy.getSprite().getX())/width), ((enemy.getSprite().getY())/height)));
                //BottomMidle
                if(!enemy.isCollitedY()){
                    enemy.setCollitedY(cellIsBlocked(((enemy.getSprite().getX()+enemy.getSprite().getWidth()/2)/width), ((enemy.getSprite().getY())/height)));
                }
                //BottomRight
                if(!enemy.isCollitedY()){                
                    enemy.setCollitedY(cellIsBlocked(((enemy.getSprite().getX()+enemy.getSprite().getWidth())/width), (enemy.getSprite().getY())/height));
                }

            }else if(enemy.getVelocidad().y>0){
                //topLeft
                enemy.setCollitedY(cellIsBlocked((enemy.getSprite().getX()/width), (enemy.getSprite().getY()+enemy.getSprite().getHeight())/height));

                //TopMidle
                if(!enemy.isCollitedY())
                    enemy.setCollitedY(cellIsBlocked((enemy.getSprite().getX()+enemy.getSprite().getWidth()/2)/width, (enemy.getSprite().getY()+enemy.getSprite().getHeight())/height));           
                //TopRight
                if(!enemy.isCollitedY())
                    enemy.setCollitedY(cellIsBlocked((enemy.getSprite().getX()+enemy.getSprite().getWidth())/width, (int) (enemy.getSprite().getY()+enemy.getSprite().getHeight())/height));
            }
            if(enemy.isCollitedY()){
                enemy.getSprite().setY(oldY);
                enemy.setVelocidadY(0);
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
    private void actualiceEnemyX(float oldX, LinkedCroc enemy){
        enemy.setCollitedX(false);
        if(oldX!=enemy.getSprite().getX()){
            if(enemy.getVelocidad().x<0){
                //topLeft
                if(!enemy.isCollitedY()){
                    enemy.setCollitedX(cellIsBlocked(enemy.getSprite().getX()/width, (enemy.getSprite().getY()+enemy.getSprite().getHeight())/height));
                }
                //midleLeft
                if(!enemy.isCollitedX())
                    enemy.setCollitedX(cellIsBlocked((enemy.getSprite().getX()/width),((enemy.getSprite().getY()+enemy.getSprite().getHeight()/2)/height)));
                //buttomLeft
                if(!enemy.isCollitedX() && !enemy.isCollitedY())
                    enemy.setCollitedX( cellIsBlocked( (enemy.getSprite().getX()/width), ((enemy.getSprite().getY())/height)));
            }else if(enemy.getVelocidad().x>0){
                //topRight
                if(!enemy.isCollitedY()){
                    enemy.setCollitedX(cellIsBlocked( ((enemy.getSprite().getX()+enemy.getSprite().getWidth())/width),  ((enemy.getSprite().getY()+enemy.getSprite().getHeight())/height)));
                }
                //midleRight
                if(!enemy.isCollitedX())
                    enemy.setCollitedX(cellIsBlocked(((enemy.getSprite().getX()+enemy.getSprite().getWidth())/width),  ((enemy.getSprite().getY()+enemy.getSprite().getHeight()/2)/height)));
                //buttomRight
                if(!enemy.isCollitedX() && !enemy.isCollitedY())
                    enemy.setCollitedX(cellIsBlocked(((enemy.getSprite().getX()+enemy.getSprite().getWidth())/width), ((enemy.getSprite().getY())/height)));

            }
            if(enemy.isCollitedX()){
                enemy.getSprite().setX(oldX);
                enemy.setVelocidadX(0);
                //System.out.println("Blocked x");
            }
        }
        
    }
    
    private boolean cellIsBlocked(float x, float y) {
        //System.out.println(x+", "+y +"//" + scaleX+", "+scaleY);
        TiledMapTileLayer.Cell cell = collisionLayer.getCell((int) (scaleX*x), (int) (scaleY*y));
        return (cell!=null)&&(cell.getTile()!=null)&&(cell.getTile().getProperties().containsKey("blocked"));
    }
      private TextButton.TextButtonStyle createButtonStyle(BitmapFont bitFont, Skin skin){
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("Button.up");
        textButtonStyle.down = skin.getDrawable("Button.down");
        
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = -1;
        
        textButtonStyle.font = bitFont;
        return textButtonStyle;
    }
    
    public Jugador getPlayer(){
        return player;
    }
    public LinkedCroc [] getCrocs(){
        return this.Crocos ;
    }
}
