package Entities;

import Entities.Enemies.Enemigo;
import com.badlogic.gdx.math.Vector2;

public class Funado extends Estado{

    //Hace el performance de funado, que es bajarle la vida
    @Override
    public void performance(Jugador jugador, Enemigo enemigo) {
        int efecto = this.getEfecto();
        float velocidadX = enemigo.getVelocidadX();
        float velocidadY = enemigo.getVelocidadY();
        velocidadX -= efecto;
        velocidadY -= efecto;
        enemigo.setVelocidadX(velocidadX);
        enemigo.setVelocidadY(velocidadY);
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
