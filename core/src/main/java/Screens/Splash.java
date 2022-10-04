/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Screens;

import Tween.SpriteAcessor;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.TweenCallback;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author Sebastian
 */
public class Splash implements Screen{

    private Sprite splashSprite;
    private SpriteBatch batch;
    private TweenManager tweenManager;
    
    @Override
    public void show() {
        batch = new SpriteBatch();
        
        tweenManager = new TweenManager();
        Tween.registerAccessor(Sprite.class,new SpriteAcessor());
        
        Texture splashTexture = new Texture("Images/TeamIcon.png");
        splashSprite = new Sprite(splashTexture);
        splashSprite.setSize(Gdx.graphics.getHeight(), Gdx.graphics.getHeight());
        splashSprite.setCenter(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        
        //Hace un Fade In a la imagen splashSprite, le cambia el alpha bien perron
        Tween.set(splashSprite, SpriteAcessor.ALPHA).target(0).start(tweenManager);
        Tween.to(splashSprite, SpriteAcessor.ALPHA, 2).target(1).delay(2).setCallback(new TweenCallback(){
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu());
            }
        }).start(tweenManager);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        tweenManager.update(delta);
        
        batch.begin();
        
        splashSprite.draw(batch);
        
        batch.end();
    }

    @Override
    public void resize(int i, int i1) {
        
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
        batch.dispose();
        splashSprite.getTexture().dispose();
    }
    
}
