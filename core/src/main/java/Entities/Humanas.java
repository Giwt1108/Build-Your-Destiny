package data;

import estructuras.ListaEnlazada;

public class Humanas extends Habilidad{

   public void llenarArengas(){
        ListaEnlazada<String> frases = new ListaEnlazada();
        frases.pushBack("Viva la libertad");
        frases.pushBack("Viva la justicia");
        frases.pushBack("Con y para el pueblo");
        frases.pushBack("Hasta la victoria");
        frases.pushBack("Colombia unida");
        this.getAtaqueDistancia().setFrases(frases);
    }



    //GETTERS AND SETTERS


    //CONSTRUCTOR
    public Humanas(){
        super();
        llenarArengas();
        this.getAtaqueCorto().setEstado( new Funado() ); //Decimos que el estado que genera el aatque corto de humanas es funar
    }


}
