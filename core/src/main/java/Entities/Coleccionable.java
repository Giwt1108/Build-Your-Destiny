package Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import estructuras.DoubleLinkedList;
import estructuras.DoubleNode;

import estructuras.ListaEnlazada;

public class Coleccionable {
    //crafteo
    private String nombre;
    private Texture texture;
    private Rectangle rectangle;
    private boolean visible;
    private DoubleNode<Coleccionable> node;


    //CONSTRUCTOR
    public Coleccionable(float x, float y, String nombre){
        this(new Texture(Gdx.files.internal("Images/Coleccionables/coin.png")),x,y);
        this.nombre = nombre;
    }

    public Coleccionable(Texture texture , float x, float y){
        this.rectangle = new Rectangle();
        this.texture = texture;
        this.visible = false;
        this.rectangle.setX(x);
        this.rectangle.setY(y);
        //this.rectangle.setX(MathUtils.random(0,Gdx.graphics.getWidth())); //No funciona con los random
        //this.rectangle.setY(MathUtils.random(0,Gdx.graphics.getHeight()));
    }

    ///METODOS
    public void dispose(){
        this.texture.dispose();
    }

    public boolean recoger(Jugador jugador){
        boolean bandera = false;
        if(Intersector.overlaps(this.rectangle, jugador.getSprite().getBoundingRectangle())){
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

    public int tamanoCollecionable(ListaEnlazada coleccionables){
        int length = coleccionables.size();
        return length;
    }
    
    public DoubleNode draw(Batch batch, Jugador jugador){
        //batch.draw(coleccionable.getTexture(), Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        batch.draw(this.getTexture(), this.getRectangle().getX(),this.getRectangle().getY() );
        if(recoger(jugador)){
            return node;
        }else{
            return null;
        }
            
    }

    @Override
    public String toString(){
        String cad = this.nombre;
        return cad;
    }


    //GETTERS AND SETTERS


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

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
    
    public void setNode(DoubleNode<Coleccionable> newNode){
        node = newNode;
    }
}
