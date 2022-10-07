package data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

import estructuras.ListaEnlazada;

public class Coleccionable {
    //crafteo
    private Texture texture;
    private Rectangle rectangle;
    private boolean visible;


    //CONSTRUCTOR
    public Coleccionable(){
        this(new Texture(Gdx.files.internal("coin.png")));
    }

    public Coleccionable(Texture texture ){
        this.rectangle = new Rectangle();
        this.texture = texture;
        this.visible = false;
        this.rectangle.setX(20);
        this.rectangle.setY(20);
        //this.rectangle.setX(MathUtils.random(0,Gdx.graphics.getWidth())); //No funciona con los random
        //this.rectangle.setY(MathUtils.random(0,Gdx.graphics.getHeight()));
    }

    ///METODOS
    public void dispose(){
        this.texture.dispose();;
    }

    public boolean recoger(Jugador jugador){
        boolean bandera = false;
        if(Intersector.overlaps(this.rectangle, jugador.getEntidad())){
            ListaEnlazada<Coleccionable> colecc = jugador.getColeccionables();
            colecc.pushBack(this);
            jugador.setColeccionables(colecc);
            System.out.println("# colecc: " +jugador.getColeccionables().size());
            this.visible = false;
            bandera = true;
        }
        return bandera;
        //System.out.println("Se ejecuta");
    }


    //GETTERS AND SETTERS


    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
