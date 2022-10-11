package Entities.Enemies;

import Entities.Entidad;
import Entities.Estado;

public class Enemigo extends Entidad{
    private Estado estado;

    //CONSTRUCTORES
    public Enemigo(){
        this.estado = null;
    }

    //GETTERS & SETTERS

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
