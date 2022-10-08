package Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import org.w3c.dom.Text;

import estructuras.ListaEnlazada;

public class ButtonAtack {
    //private Stage stage;
    //Lista con las frases de los botones
    //final private ListaEnlazada<String> arengas = new ListaEnlazada();
    //final private ListaEnlazada<String> ego = new ListaEnlazada();
    //Cosas necesarias para crear el boton
    final private TextureAtlas atlas = new TextureAtlas("ui/button.pack");;
    private Skin skin = new Skin(atlas);;

    final private BitmapFont white = new BitmapFont(Gdx.files.internal("Font/ButtonAtackWhite.fnt"), false);
    final private BitmapFont black = new BitmapFont(Gdx.files.internal("Font/ButtonAtackBlack.fnt"), false);

    private TextButtonStyle textButtonStyle;
    private TextButton button;
    private String label;
    private boolean presionado;

    public ButtonAtack(String label) {
        //Creamos el stage
        //stage = new Stage();

        //Ponemos estilo al boton
        this.textButtonStyle = new TextButtonStyle();
        this.textButtonStyle.up = skin.getDrawable("button.Beauty");
        this.textButtonStyle.font = this.white;

        //creamos el boton
        this.label = label;
        this.button =  new TextButton(label,textButtonStyle);
        this.button.padBottom(30);
        this.button.padRight(10);
        this.button.padLeft(10);

        //Llenamos las listas
        //llenarArengas();
        //llenarEgo();

        this.presionado = false;


    }


    public void setButton(TextButton button){
        this.button = button;
    }

    public TextButton getButton(){
        return this.button;
    }

    public void setLabel(String label){
        this.label = label;
    }

    public String getLabel(){
        return this.label;
    }



}
