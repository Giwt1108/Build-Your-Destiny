package data;

public class Funado extends Estado{

    //Hace el performance de funado, que es bajarle la vida
    @Override
    public void performance(Jugador jugador, Enemigo enemigo) {
        int efecto = this.getEfecto();
        int velocidad = enemigo.getVelocidad();
        velocidad -= efecto;
        enemigo.setVelocidad(velocidad);
    }

    public Funado(int efecto){
        super();
        this.setEfecto(efecto);
    }

    public Funado(){
        super();
        this.setEfecto(10);
    }
}
