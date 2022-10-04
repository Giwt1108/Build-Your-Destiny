/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Screens;

import Tween.ActorAccessor;
import Tween.SpriteAcessor;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;



/**
 *
 * @author Sebastian
 */
public class MainMenu implements Screen{

    private Stage stage;
    private TextureAtlas atlas;
    private Table table;
    private TextButton buttonExit, buttonPlay;
    private Label heading;
    private Skin skin;
    private BitmapFont black;
    
    private Sprite background;
    private SpriteBatch batch;
    private TweenManager tweenManager;

    private double deltaSize = 0;
    
    @Override
    public void show() {
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        batch = new SpriteBatch();
        tweenManager = new TweenManager();
        
        background = new Sprite(new Texture("Images/Background.png"));
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        
        Gdx.input.setInputProcessor(stage);
        
        atlas = new TextureAtlas("ui/button.atlas");
        skin = new Skin(atlas);
        
        table = new Table(skin);
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        
        black = new BitmapFont(Gdx.files.internal("Font/Black.fnt"),false);
        
        TextButtonStyle textButtonStyle = createButtonStyle(black, skin);
        
        createExitButton(textButtonStyle);
        createPlayButton(textButtonStyle);
               
        heading = new Label("Build your Destiny",new LabelStyle(black, Color.BLACK));
        heading.setFontScale(2);
        
        configTable();
        
        TweenConfigBackground();
        TweenConfigButtons();
  
    }
    
    private void TweenConfigBackground(){
        Tween.registerAccessor(Sprite.class, new SpriteAcessor());
        
        Tween.set(background, SpriteAcessor.ALPHA).target(0).start(tweenManager);
        Tween.to(background, SpriteAcessor.ALPHA, 2).target(1).start(tweenManager);
    }
    
    private void TweenConfigButtons(){
        Tween.registerAccessor(Actor.class, new ActorAccessor());
        
        Tween.set(heading,ActorAccessor.ALPHA).target(0);
        Timeline.createSequence().beginSequence()
                .push(Tween.set(buttonPlay, ActorAccessor.ALPHA).target(0))
                .push(Tween.set(buttonExit, ActorAccessor.ALPHA).target(0))
                .push(Tween.to(heading, ActorAccessor.ALPHA, 1).target(1).delay(1))
                .push(Tween.to(buttonPlay, ActorAccessor.ALPHA, 1).target(1))
                .push(Tween.to(buttonExit, ActorAccessor.ALPHA, 1).target(1)).end().start(tweenManager);   
    }
    
    private TextButtonStyle createButtonStyle(BitmapFont bitFont, Skin skin){
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("Button.up");
        textButtonStyle.down = skin.getDrawable("Button.down");
        
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = -1;
        
        textButtonStyle.font = bitFont;
        return textButtonStyle;
    }
    
    private void configTable(){
        table.add(heading);
        table.getCell(heading).spaceBottom(100);
        table.row();
        table.add(buttonPlay);
        table.getCell(buttonPlay).spaceBottom(20);
        table.row();
        table.add(buttonExit);
        //table.debug();
        stage.addActor(table);
    }
    
    private void createExitButton(TextButtonStyle textButtonStyle){
        buttonExit = new TextButton("Exit",textButtonStyle);
        buttonExit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.exit();
            }
        });
        buttonExit.pad(20);
    }
    
    private void createPlayButton(TextButtonStyle textButtonStyle){
        buttonPlay = new TextButton("Iniciar Juego",textButtonStyle);
        buttonPlay.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                ((Game) Gdx.app.getApplicationListener()).setScreen(new Levels());
            }
        });
        buttonPlay.pad(20);
    }
    
    public void update(){
        double scale =(2+Math.sin(deltaSize)/4);
        heading.setFontScale((float) scale);
        if(deltaSize<2*Math.PI)deltaSize+=0.025;
        else deltaSize = 0;
    }
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        
        update();
        tweenManager.update(delta);
        
        batch.begin();
        background.draw(batch);
        batch.end();
        
        stage.act(delta);
        
        stage.draw();
     
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        table.setClip(true);
        table.setSize(width, height);
    }

    @Override
    public void pause() {
        
    }

    @Override
    public void resume() {
        
    }

    @Override
    public void hide() {
        
    }

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
        background.getTexture().dispose();
        
    }
    
}
