package Entities;

import estructuras.ListaEnlazada;
import estructuras.PilaEnlazada;

public class CraftTable {

    private ListaEnlazada<Coleccionable> coleccion;
    int size;
    PilaEnlazada<Coleccionable> habilidades;
    private ListaEnlazada<Coleccionable> pociones = new ListaEnlazada<Coleccionable>();

    public ListaEnlazada<Coleccionable> getPociones() {
        return pociones;
    }


    public void setPociones(ListaEnlazada pociones) {
        this.pociones = pociones;
    }

    public void creacionPociones(ListaEnlazada pociones){
        pociones.pushFront("Agua bendita");
        pociones.pushFront("Cuerpo de Cristo");
        pociones.pushFront("Sangre de Cristo");
    }

    public PilaEnlazada crear(int cantidad, PilaEnlazada habilidades){
        Coleccionable pocion;
        if (cantidad>1) {
            if (cantidad == 2) {
                habilidades.pop();
                habilidades.pop();
                pocion = pociones.getAt(0);

            }
            if (cantidad == 3) {
                habilidades.pop();
                habilidades.pop();
                habilidades.pop();
                pocion = pociones.getAt(1);

            }
            if (cantidad == 4) {
                habilidades.pop();
                habilidades.pop();
                habilidades.pop();
                habilidades.pop();
                pocion = pociones.getAt(2);
            }
        }else{
            //Mostrar el cuadrito de resultado en gris.
        }
        return null;
    }

}
