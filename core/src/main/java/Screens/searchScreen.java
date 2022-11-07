package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import Buscador.Busqueda;
import Entities.Coleccionable;
import estructuras.ListaEnlazada;

public class searchScreen implements Screen{

    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private ImageButton searchButton;
    private TextField fieldSearch;
    private Busqueda busqueda;


    public searchScreen(){
        this.busqueda = new Busqueda();
    }

    public void searchAction(ListaEnlazada coles){
        String input = this.fieldSearch.getText();
        busqueda.buscar(input,coles);
        busqueda.showResults();

    }

    @Override
    public void show() {
        //Creamos la lista con los coleccionables
        ListaEnlazada<Coleccionable> coles = new ListaEnlazada();
        coles.pushBack(new Coleccionable(0,0,"espada"));
        coles.pushBack(new Coleccionable(0,0,"moneda"));
        coles.pushBack(new Coleccionable(0,0,"alada"));
        coles.pushBack(new Coleccionable(0,0,"posion"));
        coles.pushBack(new Coleccionable(0,0,"municion"));


        this.stage = new Stage();
        Gdx.input.setInputProcessor(this.stage);
        this.atlas = new TextureAtlas("ui/button.pack");
        this.skin = new Skin(atlas);

        //Creamos el boton de busqeuda y le ponemos estilo
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("lupaBusqueda");
        style.over = skin.getDrawable("lupaBusquedaHover");

        this.searchButton = new ImageButton(style);
        this.searchButton.setPosition(Gdx.graphics.getWidth() - 2 * this.searchButton.getWidth(), this.searchButton.getHeight());

        this.searchButton.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent e, float x, float y) {
                        searchAction(coles);

                    }
                });

        BitmapFont font = new BitmapFont(Gdx.files.internal("Font/ButtonAtackWhite.fnt"), false);

        //Style para un label para el textfield

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;

        //style para el textfield

        TextField.TextFieldStyle styleField = new TextField.TextFieldStyle();
        styleField.background = skin.getDrawable("fieldSearch");
        styleField.font = font;
        styleField.font.getData().setScale(0.8f);
        styleField.fontColor = Color.WHITE ;

        //Para el cursor en el textfield
        Label oneCharSizeCalibrationThrowAway = new Label("|", labelStyle);
        Pixmap cursorColor = new Pixmap((int) oneCharSizeCalibrationThrowAway.getWidth(),
                (int) oneCharSizeCalibrationThrowAway.getHeight(),
                Pixmap.Format.RGB888);
        cursorColor.setColor(Color.BLACK);
        cursorColor.fill();

        styleField.cursor = new Image(new Texture(cursorColor)).getDrawable();


        //creamos el textfield
        this.fieldSearch = new TextField("", styleField);
        this.fieldSearch.setMessageText("Búsqueda");
        this.fieldSearch.setSize(400, this.searchButton.getHeight());
        this.fieldSearch.setPosition(Gdx.graphics.getWidth() - (2*this.searchButton.getWidth() + fieldSearch.getWidth()), this.searchButton.getHeight());

        this.stage.addActor(this.fieldSearch);
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
