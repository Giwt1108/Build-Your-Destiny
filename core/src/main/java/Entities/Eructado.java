package data;

public class Eructado extends Estado{

    @Override
    public void performance(Jugador jugador, Enemigo enemigo) {
        int efecto = this.getEfecto();
        int alcance= enemigo.getAlcance();
        alcance-= efecto;
        enemigo.setAlcance(alcance);
    }

    public Eructado(){
        super();
        setEfecto(10);
    }

    public Eructado(int efecto){
        super();
        setEfecto(efecto);
    }
}
