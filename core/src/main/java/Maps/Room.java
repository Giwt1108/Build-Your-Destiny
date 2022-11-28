/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Maps;

import Entities.AtaqueCorto;
import Entities.Coleccionable;
import Entities.Enemies.Enemigo;
import Entities.Enemies.LinkedCroc;
import Entities.Enemies.proyectil;
import Entities.Enemies.QueueGusanin;
import Entities.Enemies.cainBoss;
import Entities.Jugador;
import Screens.Levels;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
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
    private Music musik,musicF;
    private TiledMapTileLayer collisionLayer;
    private Jugador player;
    //Texturas de la interfaz
    private Texture blank= new Texture(Gdx.files.internal("Images/BarraDeVida.png"));
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
    private boolean bosstime=false;
    private boolean cainIsDead=false;
    
    private int amountOfCrocos=0;
    private int CrocsKilled=0;
    private int GusanosKilled=0;
    private cainBoss jefeOne= new cainBoss();
    private LinkedCroc [] Crocos= new LinkedCroc[20];
    private QueueGusanin[] Gusanos= new QueueGusanin[50];
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
        playerBackPack.setTouchable(Touchable.enabled);
        options.setTouchable(Touchable.enabled);
        playerBackPack.setWidth(64);
        playerBackPack.setHeight(64);
        options.setWidth(64);
        options.setHeight(64);

        //Iniciamos primeros Enemigos
        this.Crocos[0]= enemy;
        
        amountOfCrocos++;
        Crocos[0].sprite.setX(player.sprite.getX()+10);
        Crocos[0].sprite.setY(player.sprite.getY()+10);
        jefeOne.sprite.setX(player.sprite.getX()+10);
        jefeOne.sprite.setY(player.sprite.getY()+10);
        int position;
        for(int i=0;i<20;i++){
            this.Gusanos[i]=new QueueGusanin();
            if(i==0){
                position=10;
            }
            else{
                position=10*i;
            }
            Gusanos[i].sprite.setX(player.sprite.getX()+position);
            Gusanos[i].sprite.setY(player.sprite.getY()-position);
        }
        for(int i=1;i<20;i++){
            this.Crocos[i]=new LinkedCroc();
            if(i==1){
                position=10;
            }
            else{
                position=10*i;
            }
            Crocos[i].sprite.setX(player.sprite.getX()+position);
            Crocos[i].sprite.setY(player.sprite.getY()-position);
            amountOfCrocos++;
        }
       
        
        scaleX = ((float) collisionLayer.getWidth())/collisionLayer.getTileWidth();
        scaleY = ((float) collisionLayer.getHeight())/collisionLayer.getTileHeight();

        //Configuramos la musica
        music.setLooping(true);
        music.setVolume(0.1f);
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
        if(!this.pause){
            if(cainIsDead==true){
                renderer.getBatch().setColor(Color.CLEAR);
                for(int i=0;i<amountOfCrocos;i++){
                    if(Crocos[i]!=null){
                        Crocos[i].kill();
                        
                    }
                    
                }
                for(int i=0;i<Gusanos.length;i++){
                    if(Gusanos[i]!=null){
                        Gusanos[i].kill();
                        
                    }
                    
                }
                musik.pause();
                musicF= Gdx.audio.newMusic(Gdx.files.internal("Music/Debussy_Reflets_dans_leau.mp3"));
                musicF.setLooping(full);
                musicF.setVolume(1f);
                musicF.play();
                player.sprite.setX(300000);
                player.sprite.setY(300000);
                camera.rotate((float) 0.01);
                
            }

            if(bosstime==false && CrocsKilled>20 || GusanosKilled>20 && bosstime==false){
                renderer.getBatch().setColor(Color.SCARLET);
                music.pause();
                camera.rotate(10);
                musik= Gdx.audio.newMusic(Gdx.files.internal("Music/turbio.mp3"));
                
                musik.setLooping(full);
                musik.setVolume(0.8f);
                musik.play();
                bosstime=true;
            }
            if( cainIsDead==false && CrocsKilled>20 || cainIsDead==false && GusanosKilled>20 ){
                renderer.getBatch().setColor(Color.SCARLET);
                camera.rotate((float) -0.05);
            }
            renderer.setView(camera);

            renderer.render();
            
           
            renderer.getBatch().begin();
            //Renderizamos a todos los cocodrilos
            
            renderCrocks();
            renderGusanos();
            if(cainIsDead==false && CrocsKilled>20 || cainIsDead==false && GusanosKilled>20){
                
                renderJefe();
                
            }
            
            //Render de interfaz
            if(cainIsDead==false){
                int damageBar=((Gdx.graphics.getWidth()/4)/97)*(int)player.getSalud() ;
                renderer.getBatch().draw(blank, player.sprite.getX()-Gdx.graphics.getWidth()/3-40, player.sprite.getY()+Gdx.graphics.getHeight()/3-30,Gdx.graphics.getWidth()/4-14,80);
                renderer.getBatch().draw(playerLife, player.sprite.getX()-Gdx.graphics.getWidth()/4-35, 5+player.sprite.getY()+Gdx.graphics.getHeight()/3+3,damageBar/2+4,7);
                options.setPosition((float) (player.getSprite().getX()+Gdx.graphics.getWidth()/3.3), (float) (player.getSprite().getY()+Gdx.graphics.getHeight()/3.3));
                options.draw(renderer.getBatch(), 20);
                playerBackPack.setPosition(player.getSprite().getX()+Gdx.graphics.getWidth()/4, (float) (player.getSprite().getY()+Gdx.graphics.getHeight()/3.3));
                playerBackPack.draw(renderer.getBatch(), 20);
            
            
            
            }
            

            float oldX, oldY;
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
            stage.draw();
        }
        else{
            renderer.setView(camera);
            renderer.getBatch().setColor(Color.GRAY);
            renderer.render();
            renderer.getBatch().begin();
           //Render de interfaz
            int damageBar=((Gdx.graphics.getWidth()/4)/97)*(int)player.getSalud() ;
            renderer.getBatch().draw(blank, player.sprite.getX()-Gdx.graphics.getWidth()/3-40, player.sprite.getY()+Gdx.graphics.getHeight()/3-30,Gdx.graphics.getWidth()/4-14,80);
            renderer.getBatch().draw(playerLife, player.sprite.getX()-Gdx.graphics.getWidth()/4-35, 5+player.sprite.getY()+Gdx.graphics.getHeight()/3+3,damageBar/2+4,7);
            options.setPosition((float) (player.getSprite().getX()+Gdx.graphics.getWidth()/3.3), (float) (player.getSprite().getY()+Gdx.graphics.getHeight()/3.3));
            options.draw(renderer.getBatch(), 20);
            playerBackPack.setPosition(player.getSprite().getX()+Gdx.graphics.getWidth()/4, (float) (player.getSprite().getY()+Gdx.graphics.getHeight()/3.3));
            playerBackPack.draw(renderer.getBatch(), 20);
            DrawColeccionables();
            renderer.getBatch().end();
        }
          
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
             if(atk.ataqueCorto(player, Crocos[i])==true){
                 CrocsKilled++;
             };
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
    public void renderGusanos(){
        float oldXE,oldYE;

        for(int i=0;i<Gusanos.length;i++){
             AtaqueCorto atk= new AtaqueCorto(); 
             if(Gusanos[i]!=null){
                 if(atk.ataqueCorto(player, Gusanos[i])==true){
                     GusanosKilled++;
                 }
             }
             
             if(Gusanos[i]!=null && Gusanos[i].isAlive()){
                oldXE = Gusanos[i].getSprite().getX();
                oldYE = Gusanos[i].getSprite().getY();
                Gusanos[i].caminar();
                actualiceEnemyX(oldXE,Gusanos[i]);
                actualiceEnemyY(oldYE,Gusanos[i]);
                Gusanos[i].addStateTime(Gdx.graphics.getDeltaTime());
                Gusanos[i].animate(renderer.getBatch());
                proyectil[] gusProyect= Gusanos[i].getProyectiles();
                for(int j=0;j<=Gusanos[i].getProyectilesCount();j++){
                    if(gusProyect[j]!=null){
                        if(gusProyect[j].getStateTime()<=1){
                            gusProyect[j].sprite.setX(Gusanos[i].sprite.getX());
                            gusProyect[j].sprite.setY(Gusanos[i].sprite.getY());
                    }
                    oldXE = gusProyect[j].getSprite().getX();
                    oldYE = gusProyect[j].getSprite().getY();
                    gusProyect[j].caminar();
                    actualiceEnemyX(oldXE,gusProyect[j]);
                    actualiceEnemyY(oldYE,gusProyect[j]);
                    gusProyect[j].addStateTime(Gdx.graphics.getDeltaTime());
                    gusProyect[j].animate(renderer.getBatch());
                    player.underAttack(gusProyect[j].followPlayer(player.getSprite().getX(), player.getSprite().getY(),true),0.01f);
                }  
            }
            Gusanos[i].playerNear(player.getSprite().getX(), player.getSprite().getY());
            }
        }
    }
    
    public void renderJefe(){
            float oldXE,oldYE;
            AtaqueCorto atk= new AtaqueCorto(); 
            if(atk.ataqueCorto(player, jefeOne)==false){
                oldXE = jefeOne.getSprite().getX();
                oldYE = jefeOne.getSprite().getY();
                jefeOne.caminar();
                actualiceEnemyX(oldXE,jefeOne);
                actualiceEnemyY(oldYE,jefeOne);
                jefeOne.addStateTime(Gdx.graphics.getDeltaTime());
                jefeOne.animate(renderer.getBatch());
                player.underAttack(jefeOne.playerNear(player.sprite.getX(), player.sprite.getY()),0.1f);
                
            }
            else{
                cainIsDead=true;
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
    private void actualiceEnemyY(float oldY,Enemigo enemy){
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
    private void actualiceEnemyX(float oldX, Enemigo enemy){
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
