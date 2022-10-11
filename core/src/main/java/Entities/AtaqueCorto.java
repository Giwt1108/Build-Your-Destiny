package Entities;

import Entities.Enemies.Enemigo;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import estructuras.ListaEnlazada;

public class AtaqueCorto extends Ataque{


    //Me dice si acerte el golpe o lo falle
    public boolean acertar(Jugador jugador, Enemigo enemigo){
        return true;
    }



    @Override
    public void ataqueCorto(Jugador jugador, Enemigo enemigo) {
        if(acertar(jugador, enemigo)){
            //SI acierta el golpe le baja vida al enemigo y le da un estado
            int salud = enemigo.getSalud();
            salud -= 1;
            enemigo.setSalud(salud);
            Estado estado = getEstado(); //Tomamos el estado que este ataque genera
            enemigo.setEstado(estado); //le damos este estado al enemigo
            estado.performance(jugador, enemigo); //ejecutamos lo que hace el estado
        }
    }

    @Override
    public void setFrases(ListaEnlazada<String> frases) {

    }

    @Override
    public void performanceButton(boolean pressedButton, boolean pressedScreen, Jugador jugador, float delta) {

    }

    @Override
    public TextButton ataqueDistancia(boolean pressedButton, boolean pressedScreen, Jugador jugador, boolean primero, float delta) {
        return null;
    }


}
