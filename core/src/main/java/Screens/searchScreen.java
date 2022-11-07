package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class searchScreen implements Screen{

    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private ImageButton searchButton;




    @Override
    public void show() {
        this.stage = new Stage();
        Gdx.input.setInputProcessor(this.stage);
        this.atlas = new TextureAtlas("ui/button.pack");
        this.skin = new Skin(atlas);

        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("lupaBusqueda");
        style.over = skin.getDrawable("lupaBusquedaHover");

        this.searchButton = new ImageButton(style);

        this.stage.addActor(this.searchButton);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.stage.act(delta);
        this.stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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

    }
}
